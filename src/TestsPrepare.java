import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class TestsPrepare {


	static FileOutputStream fileOutputStream;
	
	static int [] execHours = 
			new int[]{	8,
					   12 };
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		fileOutputStream = new FileOutputStream("/home/vmt/"+args[7]+".txt", false);
		
		int numberOfRuns = Integer.parseInt(args[0]);
		
		int numberOfKeys = Integer.parseInt(args[1]);
		int numberOfRTS  = Integer.parseInt(args[2]);
		
		int firstExecStartDate = Integer.parseInt(args[3]);
		String execTime = args[4];
		
		int numberOfStartTimes = Integer.parseInt(args[5]);
		
		String name = args[6];
		
		///////////////////////////////////////////////////
		
		Properties prop = new Properties();
		InputStream is2 = new FileInputStream("/home/vmt/files_map.txt");
		prop.load(is2);
		
		for(int i = 0; i<numberOfRuns; i++) {
			
			Random rand = new Random();
			
			String commandArgConfig = "";
			String commandArgsTime = "";
			List<String> commandArgsSourcesList = new ArrayList<>();
			
			/* Set command args time */
			//commandArgsTime += firstExecStartDate + " " + firstExecStartHour + " " + execTime + " ";
			
			/* Set command args sources list */
			List<Object> keysList = new ArrayList<>(prop.keySet());
			
			if(numberOfKeys > 0) {
				String mongoArgs = "1 mongosource ";
				
				for(int j=0; j<numberOfKeys; j++) {
					int keyNumber = rand.nextInt(keysList.size());
					
					String keyName = (String) keysList.get(keyNumber);
					mongoArgs += keyName + " ";
					
					keysList.remove(keyNumber);
				}
				
				commandArgsSourcesList.add(mongoArgs);
			}
			if(numberOfRTS > 0) {
				int whichRTS = rand.nextInt(2);
				
				if(numberOfRTS == 1 && whichRTS == 0 || numberOfRTS == 2) {
					String rtsArgs = "2 livesource_1 ";
					commandArgsSourcesList.add(rtsArgs);
				}
				if(numberOfRTS == 1 && whichRTS == 1 || numberOfRTS == 2) {
					String rtsArgs = "2 livesource_2 ";
					commandArgsSourcesList.add(rtsArgs);
				}
			}
			
			int execStartDate = firstExecStartDate;
			int execStartHour;
			int execHoursElement = 0;
			
			for(int j=0; j<numberOfStartTimes; j++) {
				
				execStartHour = execHours[execHoursElement];
				
				commandArgsTime = execStartDate + " " + execStartHour + " " + execTime + " ";
			
				/* EXECUTION of all */
				commandArgConfig = name + " ";
				saveToFile(commandArgConfig, commandArgsTime, commandArgsSourcesList);
				
				execHoursElement++;
				if(execHoursElement == execHours.length) {
					execHoursElement = 0;
					execStartDate++;
				}
			}
			
		}
		
	}
	
	private static void saveToFile(String str1, String str2, List<String> strs) throws IOException, InterruptedException {
		String commandFull = str1 + str2;
		for(String str : strs) {
			commandFull += str;
		}
		commandFull += "\n";
		
		fileOutputStream.write(commandFull.getBytes());
	}
	


}
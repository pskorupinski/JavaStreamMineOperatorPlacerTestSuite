import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class TestsRunner {

	public static final String commandBasis = "/usr/lib/jvm/java-7-oracle/bin/java -Dfile.encoding=UTF-8 " +
			"-classpath /local/mt1/workspace/JavaStreamMineOperatorPlacer/bin:" +
			"/local/mysql-connector-java-5.1.22/mysql-connector-java-5.1.22-bin.jar:/local/netty-3.6.5.Final/jar/netty-3.6.5.Final.jar:" +
			"/local/apache-log4j-1.2.17/log4j-1.2.17.jar:/local/streammine/streammine3G-managerbridge.jar:" +
			"/opt/eclipse/plugins/org.junit_4.10.0.v4_10_0_v20120426-0900/junit.jar:" +
			"/opt/eclipse/plugins/org.hamcrest.core_1.1.0.v20090501071000.jar:" +
			"/local/commons-collections-3.2.1/commons-collections-3.2.1-javadoc.jar:" +
			"/local/commons-lang3-3.1/commons-lang3-3.1-javadoc.jar:/local/commons-lang3-3.1/commons-lang3-3.1.jar:" +
			"/local/commons-collections-3.2.1/commons-collections-3.2.1.jar:/local/hadoop-1.0.3/lib/commons-lang-2.4.jar:" +
			"/local/commons-codec-1.7/commons-codec-1.7.jar:/local/commons-logging-1.1.1/commons-logging-1.1.1.jar:" +
			"/local/commons-math3-3.1.1/commons-math3-3.1.1.jar:/local/protobuf-2.4.1/java/protobuf-java-2.4.1.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/antlr-2.7.7.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/dom4j-1.6.1.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/hibernate-commons-annotations-4.0.1.Final.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/hibernate-core-4.1.8.Final.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/hibernate-jpa-2.0-api-1.0.1.Final.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/javassist-3.15.0-GA.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/jboss-logging-3.1.0.GA.jar:" +
			"/local/hibernate-release-4.1.8.Final/lib/required/jboss-transaction-api_1.1_spec-1.0.0.Final.jar:" +
			"/local/mongodb/mongo-2.10.1.jar org.microcloud.manager.StreamMineOperatorPlacerMultiRunApp ";	


	static BufferedReader br;	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		br = new BufferedReader(new FileReader("/home/vmt/" + args[0] + ".txt"));
		
		String line;
		
		while((line = br.readLine()) != null) {
			execute(line);
		}
		
		br.close();
	}
	
	private static void execute(String command) throws IOException, InterruptedException {
		String commandFull = commandBasis + command;
		
		System.out.println(commandFull);
		
		Runtime rt = Runtime.getRuntime();
		
                //Process pr = rt.exec("cmd /c dir");
        Process pr = rt.exec(commandFull);
        
        System.out.println("Running");
 
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                String line=null;
 
                while((line=input.readLine()) != null) {
                    System.out.print(".");
                }
 
                int exitVal = pr.waitFor();
                System.out.println("Finished working with value "+exitVal);
                
		
	}

}

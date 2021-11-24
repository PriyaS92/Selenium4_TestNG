package selenium4.latest;

import java.io.FileInputStream;
import java.util.Properties;

public class ProjectUtility {

	Properties prop;
	String value;
	
	public String retrieveProperty(String key){
		try{
			prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir")+"//application.properties"));
			value = prop.getProperty(key);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
}

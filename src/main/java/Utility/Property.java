package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
   public Properties property;
    FileInputStream file;

    {
        try {
            property=new Properties();
            file = new FileInputStream("/Volumes/Data/Automation/Diff_Frameworks/PageObjectModal/RestAssuredProject/src/main/resources/Config.properties");
            property.load(file);
            //System.out.println(property.getProperty("baseURL"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No Config File found : " +e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: "+ e.getMessage());
        }
    }

}

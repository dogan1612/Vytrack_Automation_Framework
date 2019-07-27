package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {
        try {
            String path = "configuration.properties";               // path to our .properties file  PAY ATTENTION to path
            FileInputStream input = new FileInputStream(path);      // we create object of input stream to access file
            configFile = new Properties();                          // initialize configFile
            configFile.load(input);                                 //load properties file

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return configFile.getProperty(key);
    }
}

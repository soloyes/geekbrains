package client;

import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    private static Properties properties = new Properties();

    static {
        try{
            properties.load(ClientController.class.getResourceAsStream("view/Properties.properties"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static int getInt(String property, int defaultValue){
        try{
            return Integer.parseInt(properties.getProperty(property, String.valueOf(defaultValue)));
        }
        catch (NumberFormatException e){
            return defaultValue;
        }

    }
}

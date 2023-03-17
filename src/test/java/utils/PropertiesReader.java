package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;
    private static FileInputStream fileIn;

    //Tạo đường dẫn đến file .properties mặc định
    private static String propertiesFilePathRoot = "src/test/resources/environment/";
    //private static String fileName = "src/test/resources/dev.properties";
    public PropertiesReader(String fileName) throws IOException {
        properties = new Properties();

        //Create object of class FileInputStream
        fileIn = new FileInputStream(propertiesFilePathRoot + fileName + ".properties");
        Log.info(propertiesFilePathRoot + fileName + ".properties");
        //Load properties file
        properties.load(fileIn);
    }

    //Xây dựng hàm Get Value từ Key của file properties đã setup bên trên
    public static String getPropValue(String KeyProp) {
        String value = null;
        //get values from properties file
        value = properties.getProperty(KeyProp);
//        System.out.println(value);
        Log.info(value);
        return value;

    }

    //Xây dựng hàm Set Value với Key tương ứng vào trong file properties đã setup bên trên
//    public static void setPropValue(String KeyProp, String Value) throws IOException {
//            //Khởi tạo giá trị cho đối tượng của class FileOutputStream
//            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot +fileName);
//            //Load properties file hiện tại và thực hiện mapping value với key tương ứng
//            properties.setProperty(KeyProp, Value);
//            //Lưu key và value vào properties file
//            properties.store(fileOut, "Set new value in properties file");
//            System.out.println("Set new value in file properties success.");
//    }

}

package helpers;

import java.io.File;

public class SystemHelpers {

    public static String getCurrentDirectory(){
        String path = System.getProperty("user.dir") + File.separator;
        return path;
    }
}

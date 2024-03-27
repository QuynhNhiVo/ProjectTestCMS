package constants;

import helpers.PropertiesHelpers;

public class ConfigData {
    public static String URL = "https://cms.anhtester.com/login";
    public static String EMAIL = "admin@example.com";
    public static String PASSWORD = "123456";
    public static String CATEGORY_NAME = "new";
    public static String PRODUCT_NAME = "1 The New Product";
    public static String colorArray[] = {"red", "blue", "yellow"};
    public static String META_TITLE = "Title of meta";
    public static String META_DESCRIPTION = "Description of meta";
    public static String EXCEL_FILE_DATA = PropertiesHelpers.getValue("EXCEL_FILE");
    public static String EXCEL_FILE_CATE = PropertiesHelpers.getValue("EXCEL_CATE");
}

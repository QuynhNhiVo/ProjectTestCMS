package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelper;
import helpers.SystemHelper;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderFactory {

    @DataProvider(name="data_add_category")
    public Object[][] dataCategory(){
        ExcelHelper excelHelper = new ExcelHelper();
        LogUtils.info("Open file excel: " + SystemHelper.getCurrentDirectory()+ConfigData.EXCEL_FILE_CATE);
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDirectory()+ConfigData.EXCEL_FILE_CATE, "Category", 1, 2);
        return data;
    }

}

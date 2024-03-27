package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderFactory {

    @DataProvider(name="data_add_category")
    public Object[][] dataCategory(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDirectory()+ConfigData.EXCEL_FILE_CATE);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDirectory()+ConfigData.EXCEL_FILE_CATE, "Category", 1, 2);
        return data;
    }

}

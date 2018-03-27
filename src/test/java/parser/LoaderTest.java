package parser;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.List;

public class LoaderTest extends TestCase {

    public void testLoad() throws FileNotFoundException {
        Table table = Loader.load("src/main/resources/data.csv");
        List<RowBean> rows = table.getRows();

        RowBean rowBean = rows.get(0);

        assertEquals("Fujitsu Siemens", rowBean.getVendor());
    }
}
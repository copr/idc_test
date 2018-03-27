package parser;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExportTest extends TestCase {
    private Table table;

    public ExportTest() throws FileNotFoundException {
        this.table = Loader.load("src/main/resources/data.csv");
    }

    public void testExport() throws IOException {
        table.export(new HtmlConvertStrategy(), "test.html");
    }
}

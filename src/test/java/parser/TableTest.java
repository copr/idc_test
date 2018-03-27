package parser;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TableTest extends TestCase {
    private Table table;

    public TableTest() throws FileNotFoundException {
        this.table = Loader.load("src/main/resources/data.csv");

    }

    public void testWhichRows() {
        List<Integer> actual = this.table.whichRowsContainVendor("Dell");

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(8);
        expected.add(10);
        expected.add(21);

        assertEquals(expected, actual);
    }

    public void testSortByUnits1() {
        this.table.sortByUnits();
        List<RowBean> rows = this.table.getRows();
        double smallestUnits = rows.get(0).getUnits();

        for (int i = 1; i < rows.size(); i++) {
            assertTrue(smallestUnits < rows.get(i).getUnits());
        }
    }

    public void testSortByUnits2() {
        this.table.sortByUnits();
        List<RowBean> rows = this.table.getRows();
        double smallestUnits = rows.get(0).getUnits();

        for (int i = 1; i < rows.size(); i++) {
            assertFalse(smallestUnits > rows.get(i).getUnits());
        }
    }


    public void testSortByVendor1() {
        this.table.sortByVendor();
        List<RowBean> rows = this.table.getRows();
        String smallestVendor = rows.get(0).getVendor();

        assertEquals("Acer", smallestVendor);
    }

    public void testSold() {
        String testVendor = "Dell";
        String testQuarter = "2010 Q3";

        List<RowBean> rows = this.table.getRows();

        double totalSum = rows.stream()
                .filter(row -> testQuarter.equals(row.getTimescale()))
                .map(RowBean::getUnits)
                .mapToDouble(Double::doubleValue)
                .sum();

        double vendorSum = rows.stream()
                .filter(row -> testVendor.equals(row.getVendor()))
                .filter(row -> testQuarter.equals(row.getTimescale()))
                .map(RowBean::getUnits)
                .mapToDouble(Double::doubleValue)
                .sum();

        SoldAggregate actual = table.sold(testVendor, testQuarter);

        assertEquals(testVendor, actual.getVendor());
        assertEquals(testQuarter, actual.getQuarter());
        assertEquals(vendorSum, actual.getSumForGivenVendor(), 0.00001);
        assertEquals(vendorSum/totalSum, actual.getShareForGivenVendor(), 0.00001);
    }

}
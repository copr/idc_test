package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class contains all the information loaded from csv and allows the user to
 * sort, export and do simple aggregations on the data
 */
public class Table {
    private List<RowBean> rows;

    public Table(List<RowBean> rows) {
        this.rows = rows;
    }

    /**
     * Sorts rows by units in ascending order
     */
    public void sortByUnits() {
        this.rows.sort((b1, b2) -> {
            double units1 = b1.getUnits();
            double units2 = b2.getUnits();
            if (units1 > units2) {
                return 1;
            }
            // since it's double there's no sense in
            // checking for equality
            return -1;
        });
    }

    /**
     * Sorts rows by vendor alphabetically.
     */
    public void sortByVendor() {
        this.rows.sort((b1, b2) -> {
            String vendor1 = b1.getVendor().toLowerCase();
            String vendor2 = b2.getVendor().toLowerCase();
            return vendor1.compareTo(vendor2);
        });
    }

    /**
     * Sorts the list using provided comparator
     */
    public void sort(Comparator<RowBean> comparator) {
        this.rows.sort(comparator);
    }

    /**
     * Converts the table using given ConvertStrategy and then writes it to a file.
     * @param convertStrategy - specifies how to convert the table
     * @param fileName - specifies where we want to export the table
     * @throws IOException - if the write failes
     */
    public void export(ConvertStrategy convertStrategy, String fileName) throws IOException {
        String convertedList = convertStrategy.convert(this.rows);
        Path file = Paths.get(fileName);
        Files.write(file, convertedList.getBytes());
    }

    /**
     * Finds how much given vendor sold in a given quarter. In both absolute
     * and relative terms.
     * @return
     */
    public SoldAggregate sold(String vendor, String quarter) {
        double total = rows.stream()
                .filter(row -> quarter.equals(row.getTimescale()))
                .map(RowBean::getUnits)
                .mapToDouble(Double::doubleValue)
                .sum();

        double sumForGivenVendor = rows.stream()
                .filter(row -> quarter.equals(row.getTimescale()))
                .filter(row -> vendor.equals(row.getVendor()))
                .map(RowBean::getUnits)
                .mapToDouble(Double::doubleValue)
                .sum();

        return new SoldAggregate(vendor, quarter, sumForGivenVendor, sumForGivenVendor/total);
    }

    /**
     * @param vendor which vendor are we interested in
     * @return list of row indexes, each corresponding row contains information about the vendor
     */
    public List<Integer> whichRowsContainVendor(String vendor) {
        return IntStream.range(0, rows.size())
                .filter(i -> vendor.equals(rows.get(i).getVendor()))
                .boxed()
                .collect(Collectors.toList());
    }

    public List<RowBean> getRows() {
        return rows;
    }
}

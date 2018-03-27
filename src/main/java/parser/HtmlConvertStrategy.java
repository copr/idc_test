package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HtmlConvertStrategy implements ConvertStrategy {
    /**
     * Converts list of RowBeans to html table. Table is described in test.pdf file.
     * It sums all units sold for each vendor and then for every vendor it calculates
     * it's share of total units sold. These two values are then shown for each vendor
     * in the table.
     * @param rowBeanList list to be converted to html
     * @return string that contains html table, ready to be written to a file.
     */
    @Override
    public String convert(List<RowBean> rowBeanList) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table><tr><th>Vendor</th><th>Units</th><th>Share</th></tr>");

        List<String> vendors = rowBeanList.stream()
                .map(RowBean::getVendor)
                .distinct()
                .collect(Collectors.toList());

        List<Double> vendorUnits = new ArrayList<>();
        List<Double> vendorShares = new ArrayList<>();

        for (String vendor : vendors) {
            double vendorSum = rowBeanList.stream()
                    .filter(row -> vendor.equals(row.getVendor()))
                    .mapToDouble(RowBean::getUnits)
                    .sum();
            vendorUnits.add(vendorSum);
        }

        double total = vendorUnits.stream().mapToDouble(Double::doubleValue).sum();

        for (Double vendorUnit : vendorUnits) {
            vendorShares.add(vendorUnit/total);
        }

        for (int i = 0; i < vendors.size(); i++) {
            String vendor = vendors.get(i);
            double vendorUnit = vendorUnits.get(i);
            double vendorShare = vendorShares.get(i);

            builder.append(
                    String.format("<tr><td>%s</td><td>%f</td><td>%.2f%%</td></tr>", vendor, vendorUnit, vendorShare*100));
        }

        builder.append(String.format("<tr><td>Total</td><td>%f</td><td>100%%</td></tr></table>", total));

        return builder.toString();
    }
}

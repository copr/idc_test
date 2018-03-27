package parser;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents one row in a data file.
 */
public class RowBean {
    @CsvBindByName(column = "Country", required = true)
    private String country;

    @CsvBindByName(column = "Timescale", required = true)
    private String timescale;

    @CsvBindByName(column = "Vendor", required = true)
    private String vendor;

    @CsvBindByName(column = "Units", required = true)
    private double units;

    public RowBean() {}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimescale() {
        return timescale;
    }

    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }
}

package parser;

/**
 * This is a class that contains information about how much a vendor sold in a given quarter.
 */
public class SoldAggregate {
    private String vendor;
    private String quarter;
    private double sumForGivenVendor;
    private double shareForGivenVendor;

    public SoldAggregate(String vendor, String quarter, double sumForGivenVendor, double shareForGivenVendor) {
        this.vendor = vendor;
        this.quarter = quarter;
        this.sumForGivenVendor = sumForGivenVendor;
        this.shareForGivenVendor = shareForGivenVendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public double getSumForGivenVendor() {
        return sumForGivenVendor;
    }

    public void setSumForGivenVendor(double sumForGivenVendor) {
        this.sumForGivenVendor = sumForGivenVendor;
    }

    public double getShareForGivenVendor() {
        return shareForGivenVendor;
    }

    public void setShareForGivenVendor(double shareForGivenVendor) {
        this.shareForGivenVendor = shareForGivenVendor;
    }
}

package parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Loader {
    /**
     * Loads csv, each csv line is converted to RowBean, stored in a list and a new table object is created
     * from the list.
     * @param fileName name of the csv file to load
     * @return a new Table object
     * @throws FileNotFoundException - when it can't find a file based on the provided filename
     */
    public static Table load(String fileName) throws FileNotFoundException {
        CsvToBean<RowBean> csvToBean = new CsvToBeanBuilder<RowBean>(new FileReader(fileName))
                .withType(RowBean.class)
                .build();

        return new Table(csvToBean.parse());
    }
}

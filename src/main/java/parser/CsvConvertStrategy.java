package parser;

import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.util.List;

public class CsvConvertStrategy implements ConvertStrategy {
    /**
     * This is supposed to be a csv converted from list of RowBeans. It could
     * work in following way, first we create header in a stringbuilder and then we
     * just iterate through the list and add values from each of the beans to the stringbuilder.
     * Alternatively we could just use opencsv.
     * @param rowBeanList
     * @return
     */
    @Override
    public String convert(List<RowBean> rowBeanList) {
        throw new RuntimeException("Not implemented");
    }
}

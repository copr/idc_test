package parser;

import java.util.List;

public class ExcelConvertStrategy implements ConvertStrategy {
    /**
     * Honestly I would just use CsvConvertStrategy, excel can read csv just fine
     * so there's no need to create a specific converter for that.
     * @param rowBeanList
     * @return
     */
    @Override
    public String convert(List<RowBean> rowBeanList) {
        return null;
    }
}

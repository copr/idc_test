package parser;

import java.util.List;

public interface ConvertStrategy {
    String convert(List<RowBean> rowBeanList);
}

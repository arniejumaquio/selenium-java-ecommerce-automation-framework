package io.github.arniejumaquio.framework.utils;

import java.util.List;

public final class DataProviderUtils {

    private DataProviderUtils() {
    }

    public static Object[][] toDataProviderArray(List<?> data) {
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }
}

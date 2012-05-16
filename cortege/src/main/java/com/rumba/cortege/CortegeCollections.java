/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.util.Collections;
import java.util.List;

/**
 * User: kiselyov
 * Date: 12.04.12
 */
public final class CortegeCollections {

    static <T extends Cortege, Vi> void fill(int num, Vi value, List<T> rows, List<Object>[] columns) {
        for (T row : rows) {
            row.setValue(num, value);
        }
        List<Object> column = columns[num - 1];
        Collections.fill(column, value);
    }
}

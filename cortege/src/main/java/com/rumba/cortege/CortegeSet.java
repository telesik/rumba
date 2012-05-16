/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.util.Set;

/**
 * CortegeSet
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public interface CortegeSet<T extends Cortege> extends CortegeCollection<T>, Set<T> {
}

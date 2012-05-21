/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege.examples;

import com.rumba.cortege.*;

/**
 * Test
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public class ExampleCollections {

    public static void main(String[] args) {
        // Создание экземпляра CortegeHashSet
        CortegeSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSetLS = Corteges.newCortegeHashSet(2);
        for (long i = 0; i < 5; i++) {
            Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeChain.create(2);
            cortegeLS.setValue(i).setValue("" + i);
            cortegeHashSetLS.add(cortegeLS);
        }
        for (Cortege cortege : cortegeHashSetLS) {
            System.out.println(cortege);
        }

        cortegeHashSetLS.add(CortegeChain.<Long, Cortege<String, Cortege.End>>create(2));

        Cortege<Long, Cortege<String, Cortege.End>> cortegeIS = CortegeChain.create(2);

        System.out.println(cortegeHashSetLS.contains(cortegeIS));
        cortegeIS.setValue(null).setValue("3");
        System.out.println(cortegeIS);
        System.out.println(cortegeHashSetLS.contains(cortegeIS));

        System.out.println(cortegeHashSetLS.contains(1, 3L));

        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS1 = CortegeChain.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS2 = CortegeChain.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS3 = CortegeChain.create(3);
        CortegeChain<String, CortegeChain<Long, CortegeChain<String, Cortege.End>>> cortegeSLS = CortegeChain.create(3);

        cortegeLLS1.setValue(1L);
        cortegeLLS1.nextElement().setValue(11L);
        cortegeLLS1.nextElement().nextElement().setValue("AAA");
        cortegeLLS2.setValue(2L);
        cortegeLLS2.nextElement().nextElement().setValue("BBB");
        cortegeLLS3.setValue(3L);
        cortegeLLS3.nextElement().setValue(33L);
        cortegeLLS3.nextElement().nextElement().setValue("AAA");

        CortegeHashSet<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> cortegeSetLLS = Corteges.newCortegeHashSet(cortegeLLS1.getDeep());

        System.out.println(cortegeSetLLS.contains(cortegeLLS1));
        cortegeSetLLS.add(cortegeLLS1);
        cortegeSetLLS.add(cortegeLLS2);
        cortegeSetLLS.add(cortegeLLS3);
        System.out.println(cortegeSetLLS.contains(cortegeLLS1));

        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : cortegeSetLLS) {
            System.out.println(cortege);
        }

        System.out.println(cortegeSetLLS.contains(3, "AAA"));

        cortegeSetLLS.fill(1, 5L);
        cortegeSetLLS.fill(2, 8L);
        cortegeSetLLS.fill(3, "XXX");

        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : cortegeSetLLS) {
            System.out.println(cortege);
        }

        CortegeLinkedList<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> cortegeListLLS = Corteges.newCortegeLinkedList(cortegeLLS1.getDeep());
        for (long i = 0; i < 10; i++) {
            Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS = CortegeChain.create(3);
            cortegeLLS.setValue(i).setValue(i * 10).setValue("" + i);
            cortegeListLLS.add(cortegeLLS);
        }
        // find any first
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> any5 = cortegeListLLS.findAny(1, 5L);
        System.out.println("any5 = " + any5);

        // extract all rows with first element equal 2L
        CortegeList<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> extractAll5 = cortegeListLLS.extract(1, 2L);

        System.out.println("extract all rows with first element equal 2L");
        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : extractAll5) {
            System.out.println(cortege);
        }

        System.out.println("extract all rows that are filtered by predicate");
        CortegeList<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> extractedByPredicate =
                cortegeListLLS.extract(new Corteges.Predicate() {
                    @Override
                    public boolean apply(Cortege cortege) {
                        long value = ((Long) (cortege.getValue())).longValue();
                        return value > 5 && value < 9;
                    }
                });
        System.out.println("extract all rows with first element grater then 5L");
        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : extractedByPredicate) {
            System.out.println(cortege);
        }
    }
}
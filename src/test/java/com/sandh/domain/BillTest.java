package com.sandh.domain;

import org.junit.Assert;
import org.junit.Test;

public class BillTest {

    public static final String KALAMARY = "kalamary";
    public static final int ITEMPRICE = -5;
    public static final int NUM_ITEMS = 2;


    @Test
    public void testBillAddItem() {
        Item item = new Item();
        item.setPrice(ITEMPRICE);

        Item item2 = new Item();
        item2.setPrice(ITEMPRICE);

        Bill bill = new Bill();

        bill.addItem(item);
        bill.addItem(item2);

        Assert.assertTrue("Bil should have " + NUM_ITEMS, NUM_ITEMS == bill.getNumberItems());

    }
}

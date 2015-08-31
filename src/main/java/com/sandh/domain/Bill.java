package com.sandh.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 10/08/15.
 */
public class Bill {
    private List<Item> items  = new ArrayList<Item>();
    final static Logger log = LoggerFactory.getLogger(Bill.class);

    public void addItem(Item item)
    {
        items.add(item);
    }

    public Item[] getItems()
    {
        return items.toArray(new Item[items.size()]);
    }

    public int getNumberItems()
    {
        return items.size();
    }

    public UUID addItem(String name, double price) {
        Item item = new Item(name, price);
        addItem(item);
        return item.getId();
    }

    public boolean deleteItem(Item item)
    {
        return items.remove(item);
    }
}

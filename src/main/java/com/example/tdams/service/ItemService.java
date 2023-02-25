package com.example.tdams.service;

import com.example.tdams.model.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    Item findItemById(Long iid);
    List<Item> showAllItems();
    Item deleteItemById(Long iid);
    Item updateItem(Long iid, Item newItem);
}

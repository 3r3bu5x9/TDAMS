package com.example.tdams.controller;

import com.example.tdams.model.Item;
import com.example.tdams.model.Vendor;
import com.example.tdams.service.ItemService;
import com.example.tdams.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/item")
@RestController
public class ItemController {
    ItemService itemService;
    VendorService vendorService;

    public ItemController(ItemService itemService, VendorService vendorService) {
        this.itemService = itemService;
        this.vendorService = vendorService;
    }
    @GetMapping("/all")
    public List<Item> showAllItems(){
        return itemService.showAllItems();
    }
    @GetMapping("/all/{vendor_id}")
    public List<Item> showAllVendorItems(@PathVariable Long vendor_id){
        return vendorService.findVendorById(vendor_id).getItems();
    }
    @PostMapping("/add/vendor/{vendor_id}")
    public Item populateItems(@PathVariable Long vendor_id, @RequestBody Item item){
        Vendor vendor = vendorService.findVendorById(vendor_id);
        item.assignVendor(vendor);
        vendor.populateItems(item);
        return itemService.addItem(item);
    }
    @PostMapping("/update/{item_id}")
    public Item updateItem(@PathVariable Long item_id, @RequestBody Item newItem){
        return itemService.updateItem(item_id, newItem);
    }
    @GetMapping("/delete/{item_id}")
    public Item deleteItem(@PathVariable Long item_id){
        return itemService.deleteItemById(item_id);
    }
}

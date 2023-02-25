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
    @PostMapping("/add")
    public Item addItem(Item item){
        return itemService.addItem(item);
    }
    @PutMapping("/{item_id}/vendor/{vendor_id}")
    public Vendor populateItems(@PathVariable Long item_id, @PathVariable Long vendor_id){
        Item item = itemService.findItemById(item_id);
        Vendor vendor = vendorService.findVendorById(vendor_id);
        item.assignVendor(vendor);
        vendor.populateItems(item);
        return vendorService.addVendor(vendor);
    }
}

package com.example.tdams.controller;

import com.example.tdams.model.Item;
import com.example.tdams.model.TiffinDetail;
import com.example.tdams.service.ItemService;
import com.example.tdams.service.TiffinDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tiffindetail")
@RestController
public class TiffinDetailController {
    TiffinDetailService tiffinDetailService;
    ItemService itemService;

    public TiffinDetailController(TiffinDetailService tiffinDetailService, ItemService itemService) {
        this.tiffinDetailService = tiffinDetailService;
        this.itemService = itemService;
    }
    @GetMapping("/all")
    public List<TiffinDetail> showAllTiffinDetail(){
        return tiffinDetailService.showAllTiffinDetail();
    }
    @PostMapping("/add")
    public TiffinDetail addTiffinDetail(TiffinDetail tiffinDetail){
        return tiffinDetailService.addTiffinDetail(tiffinDetail);
    }
    @GetMapping("/{tiffin_detail_id}")
    public TiffinDetail findTiffinDetailById(@PathVariable Long tiffin_detail_id){
        return tiffinDetailService.findTiffinDetailById(tiffin_detail_id);
    }
    @PostMapping("/{tiffin_detail_id}")
    public Long updateQuantity(@PathVariable Long tiffin_detail_id, @RequestBody Long qty){
        return tiffinDetailService.updateQuantity(tiffin_detail_id, qty);
    }
    @PutMapping("/{tiffin_detail_id}/item/{item_id}")
    public TiffinDetail assignItem(@PathVariable Long tiffin_detail_id, @PathVariable Long item_id){
        TiffinDetail tiffinDetail = tiffinDetailService.findTiffinDetailById(tiffin_detail_id);
        Item item = itemService.findItemById(item_id);
        tiffinDetail.assignItem(item);
        tiffinDetail.setQty(0L);
        item.assignToTiffinDetail(tiffinDetail);
        return tiffinDetailService.addTiffinDetail(tiffinDetail);
    }
}

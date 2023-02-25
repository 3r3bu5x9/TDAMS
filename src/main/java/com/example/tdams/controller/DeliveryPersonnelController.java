package com.example.tdams.controller;

import com.example.tdams.model.DeliveryPersonnel;
import com.example.tdams.model.UserC;
import com.example.tdams.service.DeliveryPersonnelService;
import com.example.tdams.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/deliveryp")
@RestController
public class DeliveryPersonnelController {
    DeliveryPersonnelService deliveryPersonnelService;
    UserService userService;
    public DeliveryPersonnelController(DeliveryPersonnelService deliveryPersonnelService, UserService userService) {
        this.deliveryPersonnelService = deliveryPersonnelService;
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<DeliveryPersonnel> showAllDeliveryPersonnel(){
        return deliveryPersonnelService.showAllDeliveryPersonnel();
    }
    @PostMapping("/add")
    public DeliveryPersonnel addDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel){
        return deliveryPersonnelService.addDeliveryPersonnel(deliveryPersonnel);
    }
    @PostMapping("/intime/del_id")
    public DeliveryPersonnel setInTime(@PathVariable Long del_id,@RequestBody Date intime)
    {
        return deliveryPersonnelService.setInTime(del_id,intime);
    }
    @PostMapping("/outtime/del_id")
    public DeliveryPersonnel setOutTime(@PathVariable Long del_id,@RequestBody Date outtime)
    {
        return deliveryPersonnelService.setInTime(del_id,outtime);
    }
    @PutMapping("/{del_id}/user/{user_id}")
    public DeliveryPersonnel toUser(@PathVariable Long del_id, @PathVariable Long user_id){
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelService.findDeliveryPersonnelById(del_id);
        UserC userC = userService.findUserById(user_id);
        deliveryPersonnel.toUser(userC);
        userC.toDeliveryPersonnel(deliveryPersonnel);
        return deliveryPersonnelService.addDeliveryPersonnel(deliveryPersonnel);
    }
}

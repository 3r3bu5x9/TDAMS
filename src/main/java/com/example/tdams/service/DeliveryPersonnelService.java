package com.example.tdams.service;

import com.example.tdams.model.DeliveryPersonnel;

import java.util.Date;
import java.util.List;

public interface DeliveryPersonnelService {
    DeliveryPersonnel addDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel);
    List<DeliveryPersonnel> showAllDeliveryPersonnel();
    DeliveryPersonnel findDeliveryPersonnelById(Long dpid);
    DeliveryPersonnel setInTime(Long dpid, Date inTime);
    DeliveryPersonnel setOutTime(Long dpid, Date outTime);
}

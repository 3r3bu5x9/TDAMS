package com.example.tdams.service;

import com.example.tdams.model.DeliveryPersonnel;
import com.example.tdams.repository.DeliveryPersonnelRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryPersonnelServiceImpl implements DeliveryPersonnelService{
    DeliveryPersonnelRepository deliveryPersonnelRepository;

    public DeliveryPersonnelServiceImpl(DeliveryPersonnelRepository deliveryPersonnelRepository) {
        this.deliveryPersonnelRepository = deliveryPersonnelRepository;
    }

    @Override
    public DeliveryPersonnel addDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel) {
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    @Override
    public List<DeliveryPersonnel> showAllDeliveryPersonnel() {
        return deliveryPersonnelRepository.findAll();
    }

    @Override
    public DeliveryPersonnel findDeliveryPersonnelById(Long dpid) {
        return deliveryPersonnelRepository.findById(dpid).get();
    }

    @Override
    public DeliveryPersonnel setInTime(Long dpid, Date inTime) {
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepository.findById(dpid).get();
        deliveryPersonnel.setInTime(inTime);
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    @Override
    public DeliveryPersonnel setOutTime(Long dpid, Date outTime) {
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepository.findById(dpid).get();
        deliveryPersonnel.setInTime(outTime);
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }
}

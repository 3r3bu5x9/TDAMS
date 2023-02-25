package com.example.tdams.service;

import com.example.tdams.model.Vendor;
import com.example.tdams.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{
    VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> showAllVendor() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor findVendorById(Long vid) {
        return vendorRepository.findById(vid).get();
    }

    @Override
    public Double getBalance(Long vid) {
        return vendorRepository.findById(vid).get().getBalance();
    }

    @Override
    public Vendor deleteVendor(Long vid) {
        Vendor vendor = vendorRepository.findById(vid).get();
        vendorRepository.deleteById(vid);
        return vendor;
    }
}

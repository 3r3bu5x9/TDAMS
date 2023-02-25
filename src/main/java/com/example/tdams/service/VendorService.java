package com.example.tdams.service;

import com.example.tdams.model.Vendor;

import java.util.List;

public interface VendorService {
    Vendor addVendor(Vendor vendor);
    List<Vendor> showAllVendor();
    Vendor findVendorById(Long vid);
    Double getBalance(Long vid);
    Vendor deleteVendor(Long vid);
}

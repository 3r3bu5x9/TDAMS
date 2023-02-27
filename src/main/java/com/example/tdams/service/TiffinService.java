package com.example.tdams.service;

import com.example.tdams.model.Tiffin;

import java.util.List;

public interface TiffinService {
    List<Tiffin> showAllTiffins();
    Tiffin addTiffin(Tiffin tiffin);
    Tiffin findTiffinById(Long tid);
    Tiffin deleteTiffin(Long tid);
}


package com.example.tdams.service;

import com.example.tdams.model.Tiffin;
import com.example.tdams.repository.TiffinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiffinServiceImpl implements TiffinService{
    TiffinRepository tiffinRepository;

    public TiffinServiceImpl(TiffinRepository tiffinRepository) {
        this.tiffinRepository = tiffinRepository;
    }

    @Override
    public List<Tiffin> showAllTiffins() {
        return tiffinRepository.findAll();
    }

    @Override
    public Tiffin addTiffin(Tiffin tiffin) {
        return tiffinRepository.save(tiffin);
    }

    @Override
    public Tiffin findTiffinById(Long tid) {
        return tiffinRepository.findById(tid).get();
    }
}

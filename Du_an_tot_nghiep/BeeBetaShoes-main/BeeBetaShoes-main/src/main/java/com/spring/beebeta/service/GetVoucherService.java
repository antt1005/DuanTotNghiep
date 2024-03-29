package com.spring.beebeta.service;

import com.spring.beebeta.entity.Voucher;
import com.spring.beebeta.repository.GetVoucherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVoucherService {
    @Autowired
    private GetVoucherRepo repo ;

    public List<Voucher> getAll(){
        return repo.getAll();
    }
}

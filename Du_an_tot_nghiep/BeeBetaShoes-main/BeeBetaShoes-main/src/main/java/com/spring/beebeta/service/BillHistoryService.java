package com.spring.beebeta.service;

import com.spring.beebeta.entity.Bill;
import com.spring.beebeta.entity.BillDetail;
import com.spring.beebeta.entity.BillHistory;
import com.spring.beebeta.repository.BillHistoryRepository;
import com.spring.beebeta.request.BillHistoryRequest;
import com.spring.beebeta.response.BillHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillHistoryService {
    @Autowired
    private BillHistoryRepository repository;

    public BillHistory add(BillHistoryRequest request){
        BillHistory billHistory = new BillHistory();
        billHistory.setCreateDate(new Date());
        billHistory.setStatus(request.getStatus());
        billHistory.setNote(request.getNote());
        billHistory.setCreateBy(request.getCreateBy());
        billHistory.setBill(Bill.builder().Id(request.getIdBill()).build());
        return repository.save(billHistory);
    }
    public List<BillHistoryResponse> getAllByBill(String code){
        return repository.getAllByBill(code);
    }
    public List<BillHistoryResponse> getAllByBill1(String code){
        return repository.getAllByBill1(code);
    }
    public void deleteBillDetailByCode(String code){
        List<BillHistory> list = repository.getAllByBillCode(code);
        for (BillHistory billHistory: list
        ) {
            repository.delete(billHistory);

        }
    }
}

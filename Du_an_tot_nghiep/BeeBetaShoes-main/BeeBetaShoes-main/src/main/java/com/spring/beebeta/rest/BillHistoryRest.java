package com.spring.beebeta.rest;

import com.spring.beebeta.request.BillHistoryRequest;
import com.spring.beebeta.service.BillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/billhistory")
public class BillHistoryRest {
    @Autowired
    BillHistoryService billHistoryService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BillHistoryRequest request){
        return ResponseEntity.ok(billHistoryService.add(request));
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getallbybill(@PathVariable("code") String code){
        return ResponseEntity.ok(billHistoryService.getAllByBill(code));
    }
    @GetMapping("/get/{code}")
    public ResponseEntity<?> getallbybill1(@PathVariable("code") String code){
        return ResponseEntity.ok(billHistoryService.getAllByBill1(code));
    }
    @DeleteMapping("/deletebillhistory/{code}")
    public ResponseEntity<?> deletebillhistory(@PathVariable("code") String code){
        billHistoryService.deleteBillDetailByCode(code);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

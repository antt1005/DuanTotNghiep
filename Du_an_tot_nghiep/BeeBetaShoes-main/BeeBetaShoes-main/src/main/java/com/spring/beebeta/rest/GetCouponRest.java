package com.spring.beebeta.rest;

import com.spring.beebeta.service.GetCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/getcoupon")
public class GetCouponRest {
    @Autowired
    GetCouponService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getByCustomer(id));
    }

}

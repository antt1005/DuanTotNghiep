package com.spring.beebeta.rest;

import com.spring.beebeta.request.DanhGiaRequest;
import com.spring.beebeta.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rating")
public class RatingRest {
    @Autowired
    RatingService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DanhGiaRequest request){
        return ResponseEntity.ok(service.add(request));
    }
}

package com.spring.beebeta.rest;

import com.spring.beebeta.request.RatingImageRequest;
import com.spring.beebeta.service.RatingImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ratingimage")
public class RatingImageRest {
    @Autowired
    RatingImageService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RatingImageRequest request){
        return ResponseEntity.ok(service.add(request));
    }
}

package com.spring.beebeta.service;

import com.spring.beebeta.entity.Rating;
import com.spring.beebeta.entity.RatingImage;
import com.spring.beebeta.repository.RatingImageRepository;
import com.spring.beebeta.request.RatingImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingImageService {
    @Autowired
     RatingImageRepository repository;

    public RatingImage add(RatingImageRequest request){
        RatingImage ratingImage = new RatingImage();
        ratingImage.setUrl(request.getUrl());
        ratingImage.setRating(Rating.builder().Id(request.getIdRating()).build());
        return repository.save(ratingImage);
    }
}

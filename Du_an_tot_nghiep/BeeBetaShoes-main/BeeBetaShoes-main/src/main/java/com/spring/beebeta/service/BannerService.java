package com.spring.beebeta.service;

import com.spring.beebeta.entity.Background;
import com.spring.beebeta.repository.BannerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerService {
    @Autowired
    BannerRepository repository;
    @Cacheable(value = "bannerCache", key = "'getAll'")
    public List<Background> getAll(){
        return repository.findAll();
    }
    @Transactional
    @CacheEvict(value = "bannerCache", key = "'getAll'", allEntries = true)
    public Background add(Background background){
        Background b = new Background();
        b.setContent(background.getContent());
        b.setUrl(background.getUrl());
        b.setCreateDate(new Date());
        b.setStatus(0);
        return repository.save(b);
    }
    @Transactional
    @CacheEvict(value = "bannerCache", key = "'getAll'", allEntries = true)
    public Background update(Integer id,Background background){
        Background b = repository.getById(id);
        b.setContent(background.getContent());
        if(background.getUrl() != null){
            b.setUrl(background.getUrl());
        }

        b.setUpdateDate(new Date());
        b.setStatus(0);
        return repository.save(b);
    }
    @Cacheable(value = "bannerCache", key = "#id")
    public Background get(Integer id){
        Background b = repository.getById(id);

        return b;
    }
    @Transactional
    @CacheEvict(value = "bannerCache", key = "'getAll'", allEntries = true)
    public void delete(Integer id){
        Background b = repository.getById(id);
        repository.delete(b);
    }

}

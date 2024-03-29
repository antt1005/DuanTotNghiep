package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Heelcushion;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.HeelcushionRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.HeelcushionRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HeelcushionService {
    @Autowired
    HeelcushionRepository repository;
    @Cacheable(value = "heelcushionCache", key = "'getAll'")
    public List<Heelcushion> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "heelcushionCache", key = "#name")
    public List<Heelcushion> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "heelcushionCache", key = "#request.name")
    @CacheEvict(value = "heelcushionCache", key = "'getAll'", allEntries = true)
    public Heelcushion add(HeelcushionRequest request){
        Heelcushion heelcushion = new Heelcushion();
        heelcushion.setDescription(request.getDescription());
        heelcushion.setName(request.getName());
        heelcushion.setCreateDate(new Date());
        heelcushion.setStatus(0);
        return repository.save(heelcushion);
    }
    @Transactional
    @CachePut(value = "heelcushionCache", key = "#request.name")
    @CacheEvict(value = "heelcushionCache", key = "'getAll'", allEntries = true)
    public Heelcushion update(Integer Id,HeelcushionRequest request){
        Heelcushion heelcushion = repository.getById(Id);
        heelcushion.setDescription(request.getDescription());
        heelcushion.setName(request.getName());
        heelcushion.setUpdateDate(new Date());
        return repository.save(heelcushion);
    }
    @Transactional
    @CacheEvict(value = "heelcushionCache", key = "'getAll'", allEntries = true)
    public Heelcushion delete(Integer Id){
        Heelcushion heelcushion = repository.getById(Id);
        heelcushion.setStatus(1);
        return repository.save(heelcushion);
    }
    @Cacheable(value = "heelcushionCache", key = "#Id")
    public Heelcushion getById(Integer Id){
        Heelcushion heelcushion = repository.getById(Id);
        return heelcushion;
    }
}

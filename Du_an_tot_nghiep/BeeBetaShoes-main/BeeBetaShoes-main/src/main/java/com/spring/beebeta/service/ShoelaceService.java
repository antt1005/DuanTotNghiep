package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Shoelace;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.ShoelaceRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.ShoelaceRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoelaceService {
    @Autowired
    ShoelaceRepository repository;
    @Cacheable(value = "shoelaceCache", key = "'getAll'")
    public List<Shoelace> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "shoelaceCache", key = "#name")
    public List<Shoelace> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "shoelaceCache", key = "#request.name")
    @CacheEvict(value = "shoelaceCache", key = "'getAll'", allEntries = true)
    public Shoelace add(ShoelaceRequest request){
        Shoelace shoelace = new Shoelace();
        shoelace.setDescription(request.getDescription());
        shoelace.setName(request.getName());
        shoelace.setCreateDate(new Date());
        shoelace.setStatus(0);
        return repository.save(shoelace);
    }
    @Transactional
    @CachePut(value = "shoelaceCache", key = "#request.name")
    @CacheEvict(value = "shoelaceCache", key = "'getAll'", allEntries = true)
    public Shoelace update(Integer Id,ShoelaceRequest request){
        Shoelace shoelace = repository.getById(Id);
        shoelace.setDescription(request.getDescription());
        shoelace.setName(request.getName());
        shoelace.setUpdateDate(new Date());
        return repository.save(shoelace);
    }
    @Transactional
    @CacheEvict(value = "shoelaceCache", key = "'getAll'", allEntries = true)
    public Shoelace delete(Integer Id){
        Shoelace shoelace = repository.getById(Id);
        shoelace.setStatus(1);
        return repository.save(shoelace);
    }
    @Cacheable(value = "shoelaceCache", key = "#Id")
    public Shoelace getById(Integer Id){
        Shoelace shoelace = repository.getById(Id);
        return shoelace;
    }
}

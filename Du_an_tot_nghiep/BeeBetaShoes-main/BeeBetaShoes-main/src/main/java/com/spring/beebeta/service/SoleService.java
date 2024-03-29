package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Sole;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.SoleRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.SoleRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SoleService {
    @Autowired
    SoleRepository repository;
    @Cacheable(value = "soleCache", key = "'getAll'")
    public List<Sole> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "soleCache", key = "#name")
    public List<Sole> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "soleCache", key = "#request.name")
    @CacheEvict(value = "soleCache", key = "'getAll'", allEntries = true)
    public Sole add(SoleRequest request){
        Sole sole = new Sole();
        sole.setDescription(request.getDescription());
        sole.setName(request.getName());
        sole.setCreateDate(new Date());
        sole.setStatus(0);
        return repository.save(sole);
    }
    @Transactional
    @CachePut(value = "soleCache", key = "#request.name")
    @CacheEvict(value = "soleCache", key = "'getAll'", allEntries = true)
    public Sole update(Integer Id,SoleRequest request){
        Sole sole = repository.getById(Id);
        sole.setDescription(request.getDescription());
        sole.setName(request.getName());
        sole.setUpdateDate(new Date());
        return repository.save(sole);
    }
    @Transactional
    @CacheEvict(value = "soleCache", key = "'getAll'", allEntries = true)
    public Sole delete(Integer Id){
        Sole sole = repository.getById(Id);
        sole.setStatus(1);
        return repository.save(sole);
    }
    @Cacheable(value = "soleCache", key = "#Id")
    public Sole getById(Integer Id){
        Sole sole = repository.getById(Id);
        return sole;
    }
}

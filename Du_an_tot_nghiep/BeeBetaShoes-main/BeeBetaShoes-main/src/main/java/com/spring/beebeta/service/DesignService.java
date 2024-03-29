package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Design;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.DesignRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.DesignRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DesignService {
    @Autowired
    DesignRepository repository;
    @Cacheable(value = "designCache", key = "'getAll'")
    public List<Design> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "designCache", key = "#name")
    public List<Design> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "designCache", key = "#request.name")
    @CacheEvict(value = "designCache", key = "'getAll'", allEntries = true)
    public Design add(DesignRequest request){
        Design design = new Design();
        design.setDescription(request.getDescription());
        design.setName(request.getName());
        design.setCreateDate(new Date());
        design.setStatus(0);
        return repository.save(design);
    }
    @Transactional
    @CachePut(value = "designCache", key = "#request.name")
    @CacheEvict(value = "designCache", key = "'getAll'", allEntries = true)
    public Design update(Integer Id,DesignRequest request){
        Design design = repository.getById(Id);
        design.setDescription(request.getDescription());
        design.setName(request.getName());
        design.setUpdateDate(new Date());
        return repository.save(design);
    }
    @Transactional
    @CacheEvict(value = "designCache", key = "'getAll'", allEntries = true)
    public Design delete(Integer Id){
        Design design = repository.getById(Id);
        design.setStatus(1);
        return repository.save(design);
    }
    @Cacheable(value = "designCache", key = "#Id")
    public Design getById(Integer Id){
        Design design = repository.getById(Id);
        return design;
    }
}

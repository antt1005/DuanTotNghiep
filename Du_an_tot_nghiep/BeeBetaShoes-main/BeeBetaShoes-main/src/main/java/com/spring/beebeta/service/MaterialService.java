package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Material;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.MaterialRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.MaterialRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialService {
    @Autowired
    MaterialRepository repository;
    @Cacheable(value = "materialCache", key = "'getAll'")
    public List<Material> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "materialCache", key = "#name")
    public List<Material> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "materialCache", key = "#request.name")
    @CacheEvict(value = "materialCache", key = "'getAll'", allEntries = true)
    public Material add(MaterialRequest request){
        Material material = new Material();
        material.setDescription(request.getDescription());
        material.setName(request.getName());
        material.setCreateDate(new Date());
        material.setStatus(0);
        return repository.save(material);
    }
    @Transactional
    @CachePut(value = "materialCache", key = "#request.name")
    @CacheEvict(value = "materialCache", key = "'getAll'", allEntries = true)
    public Material update(Integer Id,MaterialRequest request){
        Material material = repository.getById(Id);
        material.setDescription(request.getDescription());
        material.setName(request.getName());
        material.setUpdateDate(new Date());
        return repository.save(material);
    }
    @Transactional
    @CacheEvict(value = "materialCache", key = "'getAll'", allEntries = true)
    public Material delete(Integer Id){
        Material material = repository.getById(Id);
        material.setStatus(1);
        return repository.save(material);
    }
    @Cacheable(value = "materialCache", key = "#Id")
    public Material getById(Integer Id){
        Material material = repository.getById(Id);
        return material;
    }
}

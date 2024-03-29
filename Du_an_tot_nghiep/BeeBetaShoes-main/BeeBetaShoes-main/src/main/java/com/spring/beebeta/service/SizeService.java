package com.spring.beebeta.service;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Size;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.SizeRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.BrandRequest;
import com.spring.beebeta.request.SizeRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SizeService {
    @Autowired
    SizeRepository repository;
    @Cacheable(value = "sizeCache", key = "'getAll'")
    public List<Size> getAll(){
        return repository.getAll();
    }

    @Cacheable(value = "sizeCache", key = "#name")
    public List<Size> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "sizeCache", key = "#request.name")
    @CacheEvict(value = "sizeCache", key = "'getAll'", allEntries = true)
    public Size add(SizeRequest request){
        Size size = new Size();
        size.setDescription(request.getDescription());
        size.setName(request.getName());
        size.setCreateDate(new Date());
        size.setStatus(0);
        return repository.save(size);
    }
    @Transactional
    @CachePut(value = "sizeCache", key = "#request.name")
    @CacheEvict(value = "sizeCache", key = "'getAll'", allEntries = true)
    public Size update(Integer Id,SizeRequest request){
        Size size = repository.getById(Id);
        size.setDescription(request.getDescription());
        size.setName(request.getName());
        size.setUpdateDate(new Date());
        return repository.save(size);
    }
    @Transactional
    @CacheEvict(value = "sizeCache", key = "'getAll'", allEntries = true)
    public Size delete(Integer Id){
        Size size = repository.getById(Id);
        size.setStatus(1);
        return repository.save(size);
    }
    @Cacheable(value = "sizeCache", key = "#Id")
    public Size getById(Integer Id){
        Size size = repository.getById(Id);
        return size;
    }
}

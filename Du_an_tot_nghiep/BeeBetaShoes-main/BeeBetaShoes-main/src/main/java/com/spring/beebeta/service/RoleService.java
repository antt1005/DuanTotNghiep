package com.spring.beebeta.service;

import com.spring.beebeta.entity.Role;
import com.spring.beebeta.repository.RoleRepository;
import com.spring.beebeta.request.RoleRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleRepository repository;
    @Cacheable(value = "roleCache", key = "'getAll'")
    public List<Role> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "roleCache", key = "#name")
    public List<Role> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "roleCache", key = "#request.name")
    @CacheEvict(value = "roleCache", key = "'getAll'", allEntries = true)
    public Role add(RoleRequest request){
        Role role = new Role();
        role.setName(request.getName());
        role.setStatus(0);
        return repository.save(role);
    }
    @Transactional
    @CachePut(value = "roleCache", key = "#request.name")
    @CacheEvict(value = "roleCache", key = "'getAll'", allEntries = true)
    public Role update(Integer Id,RoleRequest request ){
        Role role = repository.getById(Id);
        role.setName(request.getName());
        return  repository.save(role);
    }
    @Transactional
    @CacheEvict(value = "roleCache", key = "'getAll'", allEntries = true)
    public Role delete(Integer Id){
        Role role = repository.getById(Id);
        role.setStatus(1);
        return repository.save(role);
    }
    @Cacheable(value = "roleCache", key = "#Id")
    public Role getById(Integer Id){
        Role role = repository.getById(Id);
        return role;
    }
}

package com.spring.beebeta.rest;

import com.spring.beebeta.request.RoleRequest;
import com.spring.beebeta.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/role")
public class RoleRest {
    @Autowired
    RoleService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getAllbyName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody RoleRequest request , BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return  ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }

    @PutMapping("/udpate/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id,@Valid @RequestBody RoleRequest request ,BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.update(Id,request));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id){
        return ResponseEntity.ok(service.delete(id));
    }
}

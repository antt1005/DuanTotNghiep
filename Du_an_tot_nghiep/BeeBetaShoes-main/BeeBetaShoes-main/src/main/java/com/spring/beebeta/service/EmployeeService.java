package com.spring.beebeta.service;

import com.spring.beebeta.entity.Customer;
import com.spring.beebeta.entity.Employee;
import com.spring.beebeta.entity.Role;
import com.spring.beebeta.repository.EmployeeRepository;
import com.spring.beebeta.request.CapNhatProfile;
import com.spring.beebeta.request.ChangeForm;
import com.spring.beebeta.request.EmployeeRequest;
import com.spring.beebeta.request.ForgetForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    @Cacheable(value = "employeeCache", key = "'getAll'")
    public List<Employee> getAll(){
        return repository.getAll();
    }
    @Cacheable(value = "employeeCache", key = "#name")
    public List<Employee> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    @Transactional
    @CachePut(value = "employeeCache", key = "#request.code")
    @CacheEvict(value = "employeeCache", key = "'getAll'", allEntries = true)
    public Employee add(EmployeeRequest request){
        Employee employee = new Employee();
        employee.setCode(request.getCode());
        employee.setFullname(request.getFullname());
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword());
        employee.setImage(request.getImage());
        employee.setGender(request.getGender());
        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setStatus(0);
        employee.setRole(Role.builder().Id(request.getIdRole()).build());
        return repository.save(employee);
    }
    @Transactional
    @CachePut(value = "employeeCache", key = "#request.code")
    @CacheEvict(value = "employeeCache", key = "'getAll'", allEntries = true)
    public Employee update(Integer id,EmployeeRequest request){
        Employee employee = repository.getById(id);
        employee.setCode(request.getCode());
        employee.setFullname(request.getFullname());
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword());
        employee.setImage(request.getImage());
        employee.setGender(request.getGender());
        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setRole(Role.builder().Id(request.getIdRole()).build());
        return repository.save(employee);
    }
    @Transactional
    @CacheEvict(value = "employeeCache", key = "'getAll'", allEntries = true)
    public Employee delete(Integer Id){
        Employee employee = repository.getById(Id);
        employee.setStatus(1);
        return repository.save(employee);
    }
    @Cacheable(value = "employeeCache", key = "#Id")
    public Employee getById(Integer Id){
        Employee employee = repository.getById(Id);
        return employee;
    }
    @Cacheable(value = "employeeCache", key = "#username")
    public Employee getByUsername(String username){
        return repository.getByUsername(username);
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    // quên mật khẩu
    public Employee forget(ForgetForm form){
        Employee employee = repository.getByUsername(form.getUsername());
        employee.setPassword(generateRandomString(8));
        employee.setUpdateDate(new Date());
        return repository.save(employee);
    }
    // đổi mật khẩu
    public Employee change(Integer idEmployee, ChangeForm form){
        Employee employee = repository.getById(idEmployee);
        employee.setPassword(form.getRePasswordMoi());
        employee.setUpdateDate(new Date());
        return repository.save(employee);
    }

    //cập nhật profile
    public Employee updateprofile(Integer idEmployee, CapNhatProfile form){
        Employee employee = repository.getById(idEmployee);
        employee.setFullname(form.getFullname());
        employee.setEmail(form.getEmail());
        employee.setPhone(form.getPhone());
        employee.setGender(form.getGender());
        employee.setImage(form.getImage());
        employee.setUpdateDate(new Date());
        return repository.save(employee);
    }

    public List<Employee> getAllByFilter(Integer idRole){
        return repository.getEmployeeByRole(idRole);
    }
}

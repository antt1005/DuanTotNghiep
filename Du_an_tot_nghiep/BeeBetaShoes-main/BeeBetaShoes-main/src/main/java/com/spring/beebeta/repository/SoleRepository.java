package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Size;
import com.spring.beebeta.entity.Sole;
import com.spring.beebeta.entity.Toe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoleRepository extends JpaRepository<Sole,Integer> {
    @Query(value = "Select e from Sole e where e.Status = 0 order by e.CreateDate desc ")
    public List<Sole> getAll();
    @Query(value = "Select e from Sole e where e.Status = 0 and e.Name like :name")
    public List<Sole> searchByName(@Param("name") String name);
    @Query(value = "select e from Sole e where e.Id = :id")
    public Sole getById(@Param("id") Integer Id);
}

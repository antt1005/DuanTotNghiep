package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Size;
import com.spring.beebeta.entity.Toe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToeRepository extends JpaRepository<Toe,Integer> {
    @Query(value = "Select e from Toe e where e.Status = 0 order by e.CreateDate desc ")
    public List<Toe> getAll();
    @Query(value = "Select e from Toe e where e.Status = 0 and e.Name like :name")
    public List<Toe> searchByName(@Param("name") String name);
    @Query(value = "select e from Toe e where e.Id = :id")
    public Toe getById(@Param("id") Integer Id);
}

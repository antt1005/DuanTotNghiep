package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Brand;
import com.spring.beebeta.entity.Heelcushion;
import com.spring.beebeta.entity.Size;
import com.spring.beebeta.entity.Toe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeelcushionRepository extends JpaRepository<Heelcushion,Integer> {
    @Query(value = "Select e from Heelcushion e where e.Status = 0 order by e.CreateDate desc ")
    public List<Heelcushion> getAll();
    @Query(value = "Select e from Heelcushion e where e.Status = 0 and e.Name like :name")
    public List<Heelcushion> searchByName(@Param("name") String name);
    @Query(value = "select e from Heelcushion e where e.Id = :id")
    public Heelcushion getById(@Param("id") Integer Id);
}

package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Background;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Background,Integer> {

    @Query(value = "select e from Background e where e.Id = :Id")
    public Background getById(@Param("Id") Integer id);
}

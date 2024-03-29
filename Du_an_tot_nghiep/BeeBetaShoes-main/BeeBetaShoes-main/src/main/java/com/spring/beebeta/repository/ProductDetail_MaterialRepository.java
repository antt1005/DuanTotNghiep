package com.spring.beebeta.repository;

import com.spring.beebeta.entity.ProductDetail_Material;
import com.spring.beebeta.entity.Toe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetail_MaterialRepository extends JpaRepository<ProductDetail_Material,Integer> {

    @Query(value = "Select e from ProductDetail_Material  e where  e.productDetail.Id =:id")
    List<ProductDetail_Material> getAllById(@Param("id") Integer id);
}

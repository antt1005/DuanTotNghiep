package com.spring.beebeta.service;

import com.spring.beebeta.entity.Material;
import com.spring.beebeta.entity.ProductDetail;
import com.spring.beebeta.entity.ProductDetail_Material;
import com.spring.beebeta.entity.Toe;
import com.spring.beebeta.repository.ProductDetail_MaterialRepository;
import com.spring.beebeta.repository.ToeRepository;
import com.spring.beebeta.request.ProductDetail_MaterialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetail_MaterialService {
    @Autowired
    ProductDetail_MaterialRepository repository;

    public ProductDetail_Material add(ProductDetail_MaterialRequest request){
        ProductDetail_Material productDetail_material = new ProductDetail_Material();
        productDetail_material.setProductDetail(ProductDetail.builder().Id(request.getIdProductDetail()).build());
        productDetail_material.setMaterial(Material.builder().Id(request.getIdMaterial()).build());
        return repository.save(productDetail_material);
    }
    public void delete(Integer idProductDetail){
        List<ProductDetail_Material> list = repository.getAllById(idProductDetail);
        for(ProductDetail_Material p : list){
            repository.delete(p);
        }
    }
}

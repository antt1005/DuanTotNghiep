package com.spring.beebeta.service;

import com.spring.beebeta.entity.*;
import com.spring.beebeta.repository.ProductDetailRepository;
import com.spring.beebeta.request.ProductDetailRequest;
import jakarta.persistence.Id;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    @Autowired
    ProductDetailRepository repository;
    public List<ProductDetail> getAll() {
        return repository.getAll();
    }
    public List<ProductDetail> getAll1() {
        return repository.getAll1();
    }
    public List<ProductDetail> getAllBanChay(){
        return repository.getAllBanChay();
    }
    public List<ProductDetail> getAllbyProductName(String name){
//        String [] spl = name.split(" ");
//        String text = "";
//        text += spl[0];
//        if(spl.length > 2){
//            for (int i = 1; i < spl.length - 1; i++) {
//                text += " near " +spl[i];
//            }
//        }
//        if(spl.length > 1){
//            text += " near " +spl[spl.length - 1];
//        }
        return repository.getAllByProductName("%"+name+"%");
    }
    public List<ProductDetail> getAllbyFilter(Integer IdColor,Integer IdSize,Integer IdMaterial,Integer IdCategory, Integer IdBrand , Integer IdToe, Integer IdSole,Integer IdShoelace,Integer IdHeelcushion, Integer IdDesign,Double min , Double max,Double minTL , Double maxTL,Integer soLuong,Integer soLuong1){
        String text = "";
//        if(name != null){
//            String [] spl = name.split(" ");
//
//            text += spl[0];
//            if(spl.length > 2){
//                for (int i = 1; i < spl.length - 1; i++) {
//                    text += " near " +spl[i];
//                }
//            }
//            if(spl.length > 1){
//                text += " near " +spl[spl.length - 1];
//            }
//        }

        return repository.getAllByFilter(IdColor,IdSize,IdMaterial,IdCategory,IdBrand,IdToe,IdSole,IdShoelace,IdHeelcushion,IdDesign,min,max,minTL,maxTL,soLuong,soLuong1);
    }
    public Page<ProductDetail> phanTrang(Integer page){
        Pageable pageable = PageRequest.of(page,10);
        return repository.findAll(pageable);
    }
    public ProductDetail getById(Integer id){
       ProductDetail productDetail = repository.getById(id);
       return productDetail;
    }
    public ProductDetail add(ProductDetailRequest request){
        ProductDetail productDetail = new ProductDetail();
        productDetail.setWeight(request.getWeight());
        productDetail.setPrice(request.getPrice());
        productDetail.setDiscount(request.getDiscount());
        productDetail.setDescription(request.getDescription());
        productDetail.setProduct(Product.builder().Id(request.getIdProduct()).build());
        productDetail.setBrand(Brand.builder().Id(request.getIdBrand()).build());
        productDetail.setCategory(Category.builder().Id(request.getIdCategory()).build());
        productDetail.setToe(Toe.builder().Id(request.getIdToe()).build());
        productDetail.setDesign(Design.builder().Id(request.getIdDesign()).build());
        productDetail.setSole(Sole.builder().Id(request.getIdSole()).build());
        productDetail.setHeelcushion(Heelcushion.builder().Id(request.getIdHeelcushion()).build());
        productDetail.setShoelace(Shoelace.builder().Id(request.getIdShoelace()).build());
        productDetail.setDiscountDate(request.getDiscountDate());
        productDetail.setCreateDate(new Date());
        productDetail.setStatus(0);
        return repository.save(productDetail);
    }
    public ProductDetail delete(Integer IdProductDetail){
        ProductDetail p = repository.getById(IdProductDetail);
        p.setStatus(1);
        return repository.save(p);
    }
    public ProductDetail delete1(Integer IdProductDetail){
        ProductDetail p = repository.getById(IdProductDetail);
        p.setStatus(0);
        return repository.save(p);
    }
    public ProductDetail update(Integer id,ProductDetailRequest request){
        ProductDetail productDetail = repository.getById(id);
        productDetail.setWeight(request.getWeight());
        productDetail.setPrice(request.getPrice());
        productDetail.setDiscount(request.getDiscount());
        productDetail.setDescription(request.getDescription());
        productDetail.setBrand(Brand.builder().Id(request.getIdBrand()).build());
        productDetail.setCategory(Category.builder().Id(request.getIdCategory()).build());
        productDetail.setToe(Toe.builder().Id(request.getIdToe()).build());
        productDetail.setDesign(Design.builder().Id(request.getIdDesign()).build());
        productDetail.setSole(Sole.builder().Id(request.getIdSole()).build());
        productDetail.setHeelcushion(Heelcushion.builder().Id(request.getIdHeelcushion()).build());
        productDetail.setShoelace(Shoelace.builder().Id(request.getIdShoelace()).build());
        productDetail.setDiscountDate(request.getDiscountDate());
        productDetail.setUpdateDate(new Date());
        return repository.save(productDetail);
    }
    public ProductDetail getByCode(String code){
        return repository.getByCode(code);
    }

    public List<ProductDetail> getProductByCategory(Integer id,Integer idBrand ,Integer idDesign, Integer idToe, Integer idSole,Integer idShoelcae,Integer idHeelcushion,Integer idProduct){
        return repository.getProductByCategory(id,idBrand,idDesign,idToe,idSole,idShoelcae,idHeelcushion,idProduct);
    }

    public Integer quantitySold(@Param("id") Integer id){
        return repository.quantitySold(id);
    }
    public Double totalSold(@Param("id") Integer id){
        return repository.totalSale(id);
    }
    public List<Voucher> getVoucher(){
        return repository.getVoucher();
    }

    public List<Voucher> getAllVoucher(){
        return repository.getAllVoucher();
    }
    public List<ProductDetail> findAll(){
        return repository.findAll();
    }


//    public List<ProductDetail> searchProductDetails(String name, Integer idMaterial, Integer idColor, Integer idSize, Integer idCategory, Integer idBrand, Integer idToe, Integer idSole, Integer idShoelace, Integer idHeelcushion, Integer idDesign, Double min, Double max, Double minTL, Double maxTL) {
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ProductDetail.class).get();
//
//        BooleanJunction<?> bool = queryBuilder.bool();
//
//        if (name != null) {
//            bool.must(queryBuilder.keyword().onField("product.name").matching(name).createQuery());
//            bool.must(queryBuilder.keyword().onField("product.code").matching(name).createQuery());
//        }
//
//        if (idMaterial != null) {
//            bool.must(queryBuilder.keyword().onField("material.id").matching(idMaterial).createQuery());
//        }
//
//        if (idColor != null) {
//            bool.must(queryBuilder.keyword().onField("color.id").matching(idColor).createQuery());
//        }
//
//        if (idSize != null) {
//            bool.must(queryBuilder.keyword().onField("size.id").matching(idSize).createQuery());
//        }
//
//        // Thêm các điều kiện tương tự cho các trường khác ở đây
//
//        if (min != null && max != null) {
//            bool.must(queryBuilder.range().onField("price").from(min).to(max).createQuery());
//        }
//
//        if (minTL != null && maxTL != null) {
//            bool.must(queryBuilder.range().onField("weight").from(minTL).to(maxTL).createQuery());
//        }
//
//        bool.must(queryBuilder.keyword().onField("status").matching(0).createQuery());
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(bool.createQuery(), ProductDetail.class);
//        fullTextQuery.setSort(new Sort(new SortField("createDate", SortField.Type.LONG, true))); // Sắp xếp theo createDate giảm dần
//
//        return fullTextQuery.getResultList();
//
//}



}

package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Product;
import com.spring.beebeta.entity.ProductDetail;
import com.spring.beebeta.entity.Voucher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    @Query(value = "Select e from ProductDetail e where e.Id = :id")
    public ProductDetail getById(@Param("id") Integer id);
    @Query(value = "Select e from ProductDetail e where e.Status = 0 Order by e.CreateDate desc")
    public List<ProductDetail> getAll();
    @Query(value = "Select e from ProductDetail e where e.Status = 1 Order by e.CreateDate desc")
    public List<ProductDetail> getAll1();
    @Query(value = "SELECT e.Id, e.Price, e.Discount, e.Description, e.CreateDate, e.UpdateDate, e.CreateBy, e.UpdateBy, e.Status, e.IdProduct, e.IdBrand, e.IdToe, e.IdShoelace, e.IdCategory, e.IdHeelcushion, e.IdSole, e.IdDesign, e.Weight, e.DiscountDate\n" +
            "            FROM ProductDetail e\n" +
            "           JOIN BillDetail bd ON bd.IdProductDetail = e.Id\n" +
            "            JOIN Bill b ON b.Id = bd.IdOrder\n" +
            "            WHERE e.Status = 0 AND b.Status = 3 AND b.PaymentDate >= DATEADD(DAY, -30, GETDATE()) \n" +
            "            GROUP BY e.Id, e.Price, e.Discount, e.Description, e.CreateDate, e.UpdateDate, e.CreateBy, e.UpdateBy, e.Status, e.IdProduct, e.IdBrand, e.IdToe, e.IdShoelace, e.IdCategory, e.IdHeelcushion, e.IdSole, e.IdDesign, e.Weight, e.DiscountDate\n" +
            "            ORDER BY SUM(bd.Quantity) DESC",nativeQuery = true)
    public List<ProductDetail> getAllBanChay();
    @Query(value = "Select e.Id,e.Price,e.Discount,e.Description,e.CreateDate,e.UpdateDate,e.CreateBy,e.UpdateBy,e.Status,e.IdProduct,e.IdBrand,e.IdToe,e.IdShoelace,e.IdCategory,e.IdHeelcushion,e.IdSole,e.IdDesign,e.Weight,e.DiscountDate from ProductDetail e \n" +
            "join Product p on p.Id = e.IdProduct\n" +
            "where e.Status = 0\n" +
            "and (p.Name like :name) OR (p.Code like :name)" , nativeQuery = true)
    public List<ProductDetail> getAllByProductName(@Param("name") String name);
    @Query(value = "Select e.Id,e.Price,e.Discount,e.Description,e.CreateDate,e.UpdateDate,e.CreateBy,e.UpdateBy,e.Status,e.IdProduct,e.IdBrand,e.IdToe,e.IdShoelace,e.IdCategory,e.IdHeelcushion,e.IdSole,e.IdDesign,e.Weight,e.DiscountDate from ProductDetail e join ProductDetail_Material m on m.IdProductDetail = e.Id\n" +
            " join Material ma on ma.Id = m.IdMaterial\n" +
            " join ProductDetail_Color_Size p on p.IdProductDetail = e.Id\n" +
            " join Color c on c.Id = p.IdColor\n" +
            " join Product pro on pro.Id = e.IdProduct\n" +
            " join Size s on s.Id = p.IdSize \n" +
            " where (ma.Id = :idmaterial or :idmaterial is null) and (c.Id = :idcolor or :idcolor is null) and (s.Id = :idsize or :idsize is null) and (e.IdCategory = :idcategory or :idcategory is null) and (e.IdBrand = :idbrand or :idbrand is null) and (e.IdToe = :idtoe or :idtoe is null) " +
            " and (e.IdSole = :idsole or :idsole is null) and (e.IdShoelace = :idshoelace or :idshoelace is null) and (e.IdHeelcushion = :idheelcushion or :idheelcushion is null) and (e.IdDesign = :iddesign or :iddesign is null) and e.Price >= :min and e.Price <= :max and e.Weight >= :minTL and e.Weight <= :maxTL and e.Status = 0\n" +
            " and e.Id in (\n" +
            "        select IdProductDetail\n" +
            "        from ProductDetail_Color_Size\n" +
            "        group by IdProductDetail\n" +
            "        having SUM(Quantity) < :soLuong AND SUM(Quantity) > :soLuong1 OR :soLuong IS NULL\n" +
            "    )" +
            " group by e.Id,e.Price,e.Discount,e.Description,e.CreateDate,e.UpdateDate,e.CreateBy,e.UpdateBy,e.Status,e.IdProduct,e.IdBrand,e.IdToe,e.IdShoelace,e.IdCategory,e.IdHeelcushion,e.IdSole,e.IdDesign,e.Weight,e.DiscountDate\n" +
            " order by e.createDate desc" +
            " ",nativeQuery = true)
    public List<ProductDetail> getAllByFilter(@Param("idcolor") Integer IdColor , @Param("idsize") Integer IdSize,@Param("idmaterial") Integer IdMaterial,@Param("idcategory") Integer IdCategory , @Param("idbrand") Integer IdBrand ,@Param("idtoe") Integer IdToe,@Param("idsole") Integer IdSole,@Param("idshoelace") Integer IdShoelcae,@Param("idheelcushion") Integer IdHeelcushion,@Param("iddesign") Integer IdDesign,@Param("min") Double min ,@Param("max") Double max,@Param("minTL") Double minTL ,@Param("maxTL") Double maxTL,@Param("soLuong") Integer soLuong,@Param("soLuong1") Integer soLuong1);
    @Query("Select e from ProductDetail  e where e.product.Code = :code")
    public ProductDetail getByCode(@Param("code") String code);
    @Query(value = "SELECT e FROM ProductDetail e " +
            "WHERE e.Status = 0 " +
            "AND (e.category.Id = :id OR e.brand.Id = :idBrand OR e.design.Id = :idDesign " +
            "OR e.toe.Id = :idToe OR e.sole.Id = :idSole OR e.shoelace.Id = :idShoelcae " +
            "OR e.heelcushion.Id = :idHeelcushion) " +
            "AND e.Id <> :idProduct " +
            "ORDER BY e.CreateDate DESC")
    public List<ProductDetail> getProductByCategory(@Param("id") Integer id,
                                                    @Param("idBrand") Integer idBrand,
                                                    @Param("idDesign") Integer idDesign,
                                                    @Param("idToe") Integer idToe,
                                                    @Param("idSole") Integer idSole,
                                                    @Param("idShoelcae") Integer idShoelcae,
                                                    @Param("idHeelcushion") Integer idHeelcushion,
                                                    @Param("idProduct") Integer idProduct);
    @Query(value = "Select SUM(b.Quantity) from BillDetail b \n" +
            "join Bill  c on c.Id = b.bill.Id \n" +
            "where b.productDetail.Id = :id and c.Status = 3")
    public Integer quantitySold(@Param("id") Integer id);
    @Query(value = "Select SUM (b.Quantity * b.UnitPrice) from BillDetail b \n" +
            "join Bill  c on c.Id = b.bill.Id \n" +
            "where b.productDetail.Id = :id and c.Status = 3")
    public Double totalSale(@Param("id") Integer id);
    @Query(value = "Select e from Voucher e \n" +
            "where e.IsVoucher = true\n" +
            "and e.Status = 0")
    public List<Voucher> getVoucher();

    @Query(value = "Select e from Voucher  e " +
            "where e.Status = 0")
    public List<Voucher> getAllVoucher();

    @Modifying
    @Transactional
    @Query(value = "Update ProductDetail set Discount = 0 , DiscountDate = null WHERE DiscountDate <= GETDATE() + 1",nativeQuery = true)
    public void updateDiscount();

}

package com.spring.beebeta.repository;

import com.spring.beebeta.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query(value = "Select e from Voucher e where e.Status = 0 or e.Status = 1")
    public List<Voucher> getAll();
    @Query(value = "Select e from Voucher e where e.Status = 0 or e.Status = 1 and e.Name like :name")
    public List<Voucher> searchByName(@Param("name") String name);
    @Query(value = "select e from Voucher e where e.Id = :id")
    public Voucher getById(@Param("id") Integer Id);


    @Modifying
    @Transactional
    @Query(value = "Update Voucher set Status = 1 WHERE EndDate <= GETDATE() + 1",nativeQuery = true)
    public void updateExpensive();
}

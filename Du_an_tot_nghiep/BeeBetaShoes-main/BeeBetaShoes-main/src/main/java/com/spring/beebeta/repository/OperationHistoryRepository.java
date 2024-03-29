package com.spring.beebeta.repository;

import com.spring.beebeta.entity.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory,Integer> {

    @Query(value = "select e from OperationHistory e order by e.CreateDate desc")
    public List<OperationHistory> getAll();
}

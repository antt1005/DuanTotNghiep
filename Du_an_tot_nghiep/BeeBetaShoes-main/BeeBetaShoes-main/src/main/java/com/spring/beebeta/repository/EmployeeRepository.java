package com.spring.beebeta.repository;
import com.spring.beebeta.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "Select e from Employee e where e.Status = 0")
    public List<Employee> getAll();
    @Query(value = "Select e from Employee e where e.Fullname like :fullname")
    public List<Employee> searchByName(@Param("fullname") String fullname);
    @Query(value = "select e from Employee e where e.Id = :id")
    public Employee getById(@Param("id") Integer Id);
    @Query(value = "select e from Employee e where e.Username = :username")
    public Employee getByUsername(@Param("username") String username);

    @Query(value = "select e from Employee e where e.Status = 0 and (e.role.Id = :idRole or :idRole is null)")
    public List<Employee> getEmployeeByRole(@Param("idRole") Integer id);

    @Query(value = "Select e from Employee e where e.Status = :status")
    public List<Employee> getByStatus(@Param("status") Integer status);

}

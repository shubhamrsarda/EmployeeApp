package com.getarrays.employeemanager.repo;

import com.getarrays.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Void deleteEmployeeById(Long id);

    //JPQL query -> deal with java object
    //@Query(value = "select e from Employee e where e.name = ?1 and e.phone = ?2")

    //@Query
    Employee getEmployeeByNameAndPhone(String name,String phone);

    // NQl query -> native query deal with database
    //@Query(value ="select * from employeemanager e where e.name=:name and e.phone=:phone",nativeQuery = true);



    Optional<Employee> findEmployeeById(Long id);
}

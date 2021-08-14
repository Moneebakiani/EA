package edu.miu.cs544.springstartproject.repository;

import edu.miu.cs544.springstartproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select distinct d from Department d where d.Name= :name")
    Optional<Department> findByName(@Param("name") String name);


}

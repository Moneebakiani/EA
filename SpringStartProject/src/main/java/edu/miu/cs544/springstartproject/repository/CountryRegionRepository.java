package edu.miu.cs544.springstartproject.repository;

import edu.miu.cs544.springstartproject.model.CountryRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CountryRegionRepository extends JpaRepository<CountryRegion,String> {
}

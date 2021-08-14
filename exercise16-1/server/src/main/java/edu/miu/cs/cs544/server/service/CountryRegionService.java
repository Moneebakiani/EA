package edu.miu.cs.cs544.server.service;


import edu.miu.cs.cs544.server.domain.CountryRegion;

import java.util.List;

public interface CountryRegionService {

    CountryRegion findById(String id);

    List<CountryRegion> findAll();

}

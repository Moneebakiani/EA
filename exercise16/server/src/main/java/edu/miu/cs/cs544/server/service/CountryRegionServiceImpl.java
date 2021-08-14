package edu.miu.cs.cs544.server.service;

import edu.miu.cs.cs544.server.domain.CountryRegion;
import edu.miu.cs.cs544.server.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CountryRegionServiceImpl implements CountryRegionService {
	
	@Autowired
	private CountryRegionRepository repository;

	@Override
	public List<CountryRegion> findAll() {
		List<CountryRegion> countries = repository.findAll();
		countries.sort(Comparator.comparing(CountryRegion::getName));
		return countries;
	}

	@Override
	public CountryRegion findById(String id) {
		return repository.findById(id).get();
	}

}

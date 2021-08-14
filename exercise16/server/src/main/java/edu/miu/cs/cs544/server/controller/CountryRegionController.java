package edu.miu.cs.cs544.server.controller;

import edu.miu.cs.cs544.server.domain.CountryRegion;
import edu.miu.cs.cs544.server.service.CountryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class CountryRegionController {
	
	@Autowired
	private CountryRegionService service;
	
	@GetMapping("/countries")
	public List<CountryRegion> findAll() {
		return service.findAll();
	}

	@GetMapping("/countries/{id}")
	public CountryRegion findById(@PathVariable String id) {
		return service.findById(id);
	}

}

package com.pro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pro.model.ApiData;
import com.pro.model.Dto;
import com.pro.model.Entries;

@RestController
public class MyController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<Object> getEntriesByCategoriesHandler(@PathVariable("category") String category){
		
		
		String url = "https://api.publicapis.org/entries";
		
		ApiData data = restTemplate.getForObject(url, ApiData.class);
		
		List<Entries> result = data.getEntries();
		
		List<Dto> dtos = result.stream().filter(s -> s.getCategory() != category)
				.map(s -> new Dto(s.getApi() , s.getDescription()))
				.collect(Collectors.toList());

		
		return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
	
	
	@GetMapping("/entries")
	public ResponseEntity<Object> getEntriesByCategoriesHandler(@RequestBody Entries entries){
		
		
		String url = "https://api.publicapis.org/entries";
		
		ApiData data = restTemplate.getForObject(url, ApiData.class);
		
		List<Entries> result = data.getEntries();
		
		result.add(entries);
		
		
		return new ResponseEntity<Object>(result,HttpStatus.ACCEPTED);
		
		
		
	}

}

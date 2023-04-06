package com.pro.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiData {
	
     private String title;
     
     private List<Entries> entries = new ArrayList<>();

}

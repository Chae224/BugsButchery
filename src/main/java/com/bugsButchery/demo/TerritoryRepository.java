package com.bugsButchery.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TerritoryRepository extends JpaRepository<Territory, Integer>{
	
	ArrayList<Territory> findAllByTerritoryFamily(int territoryFamily);
	
}

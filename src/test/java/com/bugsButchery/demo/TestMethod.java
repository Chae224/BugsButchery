package com.bugsButchery.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMethod {

	@Autowired
	BugsButcheryService myService;
	
	@Test
	public void test() {
		
		ArrayList<Territory> allTerritories = new ArrayList<Territory>();
		
		for(Territory entry : myService.myTerritoryRepository.findAll()) {
			allTerritories.add(entry);
		}
		
		Player maurane = new Player("Maurane", "fourmis violettes", 30);
		Player sylvain = new Player("Sylvain", "fourmis vertes", 30);
		
		myService.createNewPlayer(maurane);
		myService.createNewPlayer(sylvain);
		
		myService.placeFirstAnts(maurane, allTerritories.get(0));
      	myService.placeFirstAnts(sylvain, allTerritories.get(1));
      	
      	myService.placeAnts(maurane, maurane.getPlayerTerritoryList().get(0), 40);
      	myService.placeAnts(sylvain, sylvain.getPlayerTerritoryList().get(0), 4);
      	
      	myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(0), 3, sylvain, sylvain.getPlayerTerritoryList().get(0), 2);
      	System.out.println(maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
      	System.out.println(sylvain.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
      	
      	myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(0), 2, sylvain, sylvain.getPlayerTerritoryList().get(0), 2);
      	System.out.println(maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
      	System.out.println(sylvain.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
      	
      	myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(0), 2, sylvain, sylvain.getPlayerTerritoryList().get(0), 1);
      	myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(0), 2, sylvain, sylvain.getPlayerTerritoryList().get(0), 1);
      	myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(0), 2, sylvain, sylvain.getPlayerTerritoryList().get(0), 1);
      	for (Territory entry : maurane.getPlayerTerritoryList()) {
      		System.out.println(entry.getTerritoryName());
      	}
	}

}

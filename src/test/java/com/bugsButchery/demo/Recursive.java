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
public class Recursive {

	@Autowired
	BugsButcheryService myService;
	
	@Autowired
	Game myGame;
	
	@Test
	public void test() {
//---- commencement partie ----//
    	
        
        ArrayList<Territory> allTerritories = new ArrayList<Territory>();
        
        
        for(Territory entry : myService.myTerritoryRepository.findAll()) {
        	allTerritories.add(entry);
        }
      
        Player maurane = new Player("Maurane", "fourmis violettes", 30);        
      	Player valentin = new Player("Valentin", "fourmis noires", 30);
      	Player sylvain = new Player("Sylvain", "fourmis vertes", 30);
      	Player eloise = new Player("Eloise", "fourmis rouges", 30);

      	

      	for (Territory entry : allTerritories) {
      		System.out.println("Territoire :"+entry.getTerritoryName());
      		for(Territory frontier : entry.getTerritoryFrontiers()) {
      			System.out.println("frontiere"+frontier.getTerritoryName());
      		}
      	}
      	

      	/////////////////AFFICHAGE///////////////
//      		
//          	System.out.println(""+maurane.getPlayerName()+"   "+ maurane.getPlayerAntsBreed());
//          	for(int i=0 ;i<maurane.getPlayerTerritoryList().size(); i=i+1) {
//          		System.out.println(maurane.getPlayerTerritoryList().get(i).getTerritoryName());
//          	}
//          	for(int i=0 ;i<maurane.getPlayerTerritoryFamilyList().size(); i=i+1) {
//          		System.out.println(maurane.getPlayerTerritoryList().get(i).getTerritoryName());
//          	}
//      	

      	
      	
      	
      	//ajout des player dans playersAlive
      	myService.createNewPlayer(maurane);
      	myService.createNewPlayer(valentin);
      	myService.createNewPlayer(sylvain);
      	myService.createNewPlayer(eloise);
      	
 		//myService.changePlayer
 		myGame.setPlayerTurn(myGame.getPlayersAlive().get(0));
      
      	
      	/////////////////AFFICHAGE///////////////
      	
//      	for(int i=0 ;i<myService.getPlayersAlive().size(); i=i+1) {
//      		System.out.println(myService.getPlayersAlive().get(i).getPlayerName());
//      	}

      
      	//choix des territoire par le placement des 1er fourmis de dans
      	myService.placeFirstAnts(maurane, allTerritories.get(1));
      	myService.placeFirstAnts(maurane, allTerritories.get(6));
      	myService.placeFirstAnts(maurane, allTerritories.get(3));
      	myService.placeFirstAnts(valentin, allTerritories.get(7));
      	myService.placeFirstAnts(valentin, allTerritories.get(0));
      	myService.placeFirstAnts(sylvain, allTerritories.get(5));
      	myService.placeFirstAnts(sylvain, allTerritories.get(8));
      	myService.placeFirstAnts(eloise, allTerritories.get(2));
      	myService.placeFirstAnts(eloise, allTerritories.get(4));
      	
      	
      	
      	
      	
      	//placement du reste des fourmis dans ses propre territoire
      	myService.placeAnts(maurane, allTerritories.get(1), 4);
      	myService.placeAnts(maurane, allTerritories.get(6), 4);
      	myService.placeAnts(maurane, allTerritories.get(3), 4);
      	myService.placeAnts(valentin, allTerritories.get(7), 10);
      	myService.placeAnts(valentin, allTerritories.get(0), 18);
      	myService.placeAnts(sylvain, allTerritories.get(5), 10);
      	myService.placeAnts(sylvain, allTerritories.get(8), 18);
      	myService.placeAnts(eloise, allTerritories.get(2), 10);
      	myService.placeAnts(eloise, allTerritories.get(4), 18);
      	//on peut mettre territoryMaurane etc...
      	


      	
      	

		myService.addAntsHill(maurane, maurane.getPlayerTerritoryList().get(2));
 		myService.addAntsHill(valentin, valentin.getPlayerTerritoryList().get(1));
 		myService.addAntsHill(sylvain, sylvain.getPlayerTerritoryList().get(1));
 		myService.addAntsHill(eloise, eloise.getPlayerTerritoryList().get(1));
 
 		
      	/////////////////AFFICHAGE///////////////

      	System.out.println(""+eloise.getPlayerName()+"   "+ eloise.getPlayerAntsBreed());
      	for(int i=0 ;i<eloise.getPlayerTerritoryList().size(); i=i+1) {
      		System.out.println(eloise.getPlayerTerritoryList().get(i).getTerritoryName());
      		System.out.println(eloise.getPlayerTerritoryList().get(i).getTerritoryAntsNb());
      		System.out.println(eloise.getPlayerTerritoryList().get(i).getTerritoryOwner().getPlayerName());
      		
      	}
   

 		
 		
 		//---- combat ----//
 		

 		
 		
      	/////////////////////AFFICHAGE////////////////////////
 		System.out.println("name of player playing"+myGame.getPlayerTurn().getPlayerName());

 		
 		myService.refillAvailableAnts(maurane);
 		
 		//////////////////////////AFFICHAGE/////////////////////////

 		System.out.println("nb ant terr1  "+allTerritories.get(1).getTerritoryAntsNb());

 		
      	myService.placeAnts(maurane, allTerritories.get(1), 2);
      	myService.placeAnts(maurane, allTerritories.get(6), 1);
 		

 		System.out.println("nb ant terr1    "+allTerritories.get(1).getTerritoryAntsNb());



 		
 		//FAUT IL LES METTRE ?
 		myService.requestAttack(maurane.getPlayerTerritoryList().get(1), eloise.getPlayerTerritoryList().get(0), 3);
 		myService.requestDefense(eloise.getPlayerTerritoryList().get(0), 2);
 		//myService.diceFight(maurane, maurane.getPlayerTerritoryList().get(1), 3, eloise, eloise.getPlayerTerritoryList().get(0), 2);
 		
 		//Vise la fourmiliere
 		//myService.diceFight(maurane, territoryMaurane.get(0), 3, eloise, territoryEloise.get(1), 2);
 		System.out.println("Nb de fourmis avant depart"+maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
 		System.out.println("Nb de fourmis avant depart"+allTerritories.get(3).getTerritoryAntsNb());
 		myService.moveAvailable(maurane, maurane.getPlayerTerritoryList().get(0), allTerritories.get(3) , 5);
 		System.out.println("Nb de fourmis  apres depart"+maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
 		System.out.println("Nb de fourmis  apres depart"+allTerritories.get(3).getTerritoryAntsNb());

 
 		//myService.changePlayer();
 		
 		
 		
 		
/////////////   2nd tour   ////////////////
 		
 		myService.refillAvailableAnts(valentin);
 		
 		//si des fourmi les placer
 		
 		/////////////////////// Affichage//////////////////////////:
 		
// 		System.out.println("nb ant val before attack   "+valentin.getPlayerTerritoryList().get(1).getTerritoryAntsNb());
// 		System.out.println("nb ant maurane before attack   "+maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());
// 		System.out.println("proprio du territoire attaqué   "+allTerritories.get(3).getTerritoryOwner().getPlayerName());
//      	for(int i=0 ;i<allTerritories.size(); i=i+1) {
//      		System.out.println(allTerritories.get(i).getTerritoryName()+"  Proprio:  "+allTerritories.get(i).getTerritoryOwner().getPlayerName());
//      	}
 		
 		
 		
 		//FAUT IL LES METTRE ?
 		myService.requestAttack(valentin.getPlayerTerritoryList().get(1), maurane.getPlayerTerritoryList().get(0), 3);
 		
 		myService.requestDefense(maurane.getPlayerTerritoryList().get(2), 2);
 		
 		//myService.diceFight(valentin, valentin.getPlayerTerritoryList().get(1), 3, maurane, maurane.getPlayerTerritoryList().get(0), 2);
 		
 		
 		/////////////////////// Affichage//////////////////////////

 		System.out.println("nb ant val after attack   "+valentin.getPlayerTerritoryList().get(1).getTerritoryAntsNb());
 		System.out.println("nb ant maurane after attack   "+maurane.getPlayerTerritoryList().get(0).getTerritoryAntsNb());

 		System.out.println("proprio du territoire attaqué   "+allTerritories.get(3).getTerritoryOwner().getPlayerName());
 		
// 		for(int i=0 ;i<allTerritories.size(); i=i+1) {
//      		System.out.println(allTerritories.get(i).getTerritoryName()+"  Proprio:  "+allTerritories.get(i).getTerritoryOwner().getPlayerName());
//      	}
 		
 		System.out.println("player avant changement :    "+myGame.getPlayerTurn().getPlayerName() +"Activation de la méthode skip");
 		//myService.changePlayer();
 		myService.skip();
 		System.out.println("player apres changement :    "+myGame.getPlayerTurn().getPlayerName());
    }

		
}


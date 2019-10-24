package com.bugsButchery.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class WebSocketController {

	@Autowired
	BugsButcheryService bugService;
	private int playerIncr=0;

	//new game
	//
	@MessageMapping("/newGame")
	@SendTo("/bugsbutchery")
	public Game newGame() {
		bugService.createAllFamilies();
		bugService.createAllTerritories();
		System.out.println("it's working");
		return bugService.myGame;
	}

	//new player
		//
    @MessageMapping("/newPlayer")
    @SendTo("/bugsbutchery")
    public Game newPlayer(Player player) {
        bugService.createNewPlayer(player);
        System.out.println(player.getPlayerName());
        
    	playerIncr=playerIncr+1;
    	if(playerIncr ==1) {
    		bugService.myGame.setPlayerTurn(bugService.myGame.getPlayersAlive().get(0));	
    	}
    	
        return bugService.myGame;
    }
      
	

	//multi turn pick territory untill all taken
		//
	@MessageMapping("/pickTerritory")
	@SendTo("/bugsbutchery")
	public Game pickTerritory(MessageReceived message) {
		
	String territoryName= message.getTerritory1();
	System.out.println("territoryName=  "+territoryName);
	
	
	
	
	for(Territory entry : bugService.myGame.getAllTerritories()) {
		System.out.println("entry=  "+ entry.getTerritoryName());
		if(entry.getTerritoryName().equals(territoryName) ){
			System.out.println("il y a un match");
			System.out.println(bugService.myGame.getPlayerTurn().getPlayerName());
			bugService.placeFirstAnts(bugService.myGame.getPlayerTurn(), entry);
			
		}
		
	}
		
		
		return bugService.myGame;
	}
	
	
	//multi turn reinforcement untill all spent
		//
	@MessageMapping("/addAnt")
	@SendTo("/bugsbutchery")
	public Game addAnt(Player player, Territory territory, int ants) {
		bugService.placeAnts(player, territory, ants);
		return bugService.myGame;
	}
	
	//multi turn pick anthill
		//
	@MessageMapping("/addAnthill")
	@SendTo("/bugsbutchery")
	public Game addAnthill(Player player, Territory territory, int ants) {
		bugService.addAntsHill(player, territory);
		return bugService.myGame;
	}
	
		//reinforcement
	@MessageMapping("/refill")
	@SendTo("/bugsbutchery")
	public Game refill(Player player) {
		bugService.refillAvailableAnts(player);
		return bugService.myGame;
	}
		
	//attack
	@MessageMapping("/requestAttack")
	@SendTo("/bugsbutchery")
	public Game requestAttack(Territory attacker, Territory target, int nbrDiceAttack) {
		bugService.requestAttack(attacker, target, nbrDiceAttack);
		return bugService.myGame;
	}
	
	@MessageMapping("/requestDefense")
	@SendTo("/bugsbutchery")
	public Game requestDefense(Territory defender, int nbrDiceDefense) {
		bugService.requestDefense(defender, nbrDiceDefense);
		return bugService.myGame;
		
	}
	
	@MessageMapping("/fight")
	@SendTo("/bugsbutchery")
	public Game fight(Player current, Territory attacker, int nbrDiceAttack, Player defender, Territory target, int nbrDiceDefender) {
		bugService.diceFight(current, attacker, nbrDiceAttack, defender, target, nbrDiceDefender);
		return bugService.myGame;
		
	}
	
	
	//skip 
	@MessageMapping("/skip")
	@SendTo("/bugsbutchery")
	public Game skip() {
		bugService.changePlayer();
		return bugService.myGame;
	}
	
	
	//move
	@MessageMapping("/move")
	@SendTo("/bugsbutchery")
	public Game move(Player player, Territory territoryStart, Territory territoryArrival, int antNbr) {
		bugService.moveAvailable(player, territoryStart, territoryArrival, antNbr);
		return bugService.myGame;
	}
}
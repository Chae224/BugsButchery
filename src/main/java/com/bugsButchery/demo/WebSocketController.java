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


	
	@MessageMapping("/echo")
	@SendTo("/bugsbutchery")
	public Game echo() {
		return bugService.myGame;
	}
	
	
	//new game
	//
	@MessageMapping("/newGame")
	@SendTo("/bugsbutchery")
	public Game newGame() {
		bugService.createAllFamilies();
		bugService.createAllTerritories();
		bugService.setOn();
		System.out.println("it's working");
		return bugService.myGame;
	}


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
	System.out.println("toto");
	return bugService.myGame;
		
	}
	
	

	
	
	
	//multi turn reinforcement untill all spent
		//
	@MessageMapping("/addAnt")
	@SendTo("/bugsbutchery")
	public Game addAnt(MessageReceived message) {
		
		String territoryName= message.getTerritory1();
		System.out.println("territoryName=  "+territoryName);
	
		for(Territory entry : bugService.myGame.getPlayerTurn().getPlayerTerritoryList()) {
			System.out.println("entry=  "+ entry.getTerritoryName());
			if(entry.getTerritoryName().equals(territoryName) ){
				System.out.println("il y a un match");
				System.out.println(bugService.myGame.getPlayerTurn().getPlayerName());
				bugService.placeAnts(bugService.myGame.getPlayerTurn(), entry,message.getNbAnts());
				
			}
			
		}
		
		
		return bugService.myGame;
	}
	
	
	
	// multi turn pick anthill
		
	@MessageMapping("/addAnthill")
	@SendTo("/bugsbutchery")
	public Game addAnthill(MessageReceived message) {
		
		String territoryName= message.getTerritory1();
		System.out.println("territoryName=  "+territoryName);
		

		for(Territory entry : bugService.myGame.getPlayerTurn().getPlayerTerritoryList()) {
			System.out.println("entry=  "+ entry.getTerritoryName());
			if(entry.getTerritoryName().equals(territoryName) ){
				System.out.println("il y a un match");
				System.out.println(bugService.myGame.getPlayerTurn().getPlayerName());
				bugService.addAntsHill(bugService.myGame.getPlayerTurn(), entry);
				
			}
			
		}
		
		return bugService.myGame;
	}
	
	
	

		

	
	//attack
	@MessageMapping("/requestAttack")
	@SendTo("/bugsbutchery")
	public Game requestAttack(MessageReceived message) {
		String territoryNameAttacker= message.getTerritory1();
		String territoryNameTarget= message.getTerritory2();
		int nbrDiceAttack = message.getNbrDiceAttack();
		
		
		for(Territory territoryAttacker:bugService.myGame.getAllTerritories()) {
			System.out.println("entry=  "+ territoryAttacker.getTerritoryName());
			if(territoryAttacker.getTerritoryName().equals(territoryNameAttacker) ){
				System.out.println("il y a un match");
				
				for(Territory territoryTarget:bugService.myGame.getAllTerritories()) {
					System.out.println("entry=  "+ territoryTarget.getTerritoryName());
					if(territoryTarget.getTerritoryName().equals(territoryNameTarget) ){
						System.out.println("il y a un double match");
						
						bugService.myGame.setTerritoryAttacker(territoryAttacker);
						bugService.myGame.setTerritoryTarget(territoryTarget);
						bugService.myGame.setNbrDiceAttack(nbrDiceAttack);
					
						bugService.requestAttack(territoryAttacker, territoryTarget, nbrDiceAttack);
						
					}
				}
			}
			
		}
		
		
		return bugService.myGame;
		
	}
	
	
	
//	@MessageMapping("/requestDefense")
//	@SendTo("/bugsbutchery")
//	public Game requestDefense(Territory defender, int nbrDiceDefense) {
//		bugService.requestDefense(defender, nbrDiceDefense);
//		return bugService.myGame;
//		
//	}
	
	
	
	@MessageMapping("/requestDefense")
	@SendTo("/bugsbutchery")
	public Game requestDefense(MessageReceived message) {
		
		int nbrDiceDefense = message.getNbrDiceDefense();

				bugService.myGame.setNbrDiceDefense(nbrDiceDefense);
				bugService.requestDefense(bugService.myGame.getTerritoryTarget(), nbrDiceDefense);
	
		

		return bugService.myGame;
		
	}
	
	
	

	
	
	//skip                     // voir si on est en move ou attack
	@MessageMapping("/skip")
	@SendTo("/bugsbutchery")
	public Game skip() {
		bugService.skip();

		return bugService.myGame;
	}
	

	
	
	//move
		@MessageMapping("/move")
		@SendTo("/bugsbutchery")
		public Game move(MessageReceived message) {
			
			
			
			String territoryNameStart= message.getTerritory1();
			String territoryNameArrival= message.getTerritory2();
			int antNbr = message.getNbAnts();
			
			

			
			
			for(Territory territoryStart:bugService.myGame.getAllTerritories()) {
				if(territoryStart.getTerritoryName().equals(territoryNameStart) ){
					
					for(Territory territoryArrival:bugService.myGame.getAllTerritories()) {
						if(territoryArrival.getTerritoryName().equals(territoryNameArrival) ){
												
	
							bugService.moveAvailable(territoryStart.getTerritoryOwner(), territoryStart, territoryArrival, antNbr);
							
						}
					}
				}
				
			}
			
			
			
			return bugService.myGame;
		}
		
	
}
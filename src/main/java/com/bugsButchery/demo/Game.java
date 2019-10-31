package com.bugsButchery.demo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class Game {



	protected ArrayList<Player> playersAlive = new ArrayList<Player>();
	protected Player playerTurn; // = playersAlive.get(0);
	protected int pathExist;
	protected ArrayList<Territory> allTerritories = new ArrayList<Territory>();
    protected ArrayList<Family> allFamilies = new ArrayList<Family>();
//    protected String message;
    protected ArrayList<String> message = new ArrayList<String>();
	protected int nbAnthill=0;

	protected HashMap<String, Boolean> divOn = new HashMap<String, Boolean>(){{
    	//put("logInOn", true);
    	//put("full", false);
    	//put("sasOn", false);
    	put("newGameButton", false);
    	put("gameSetOn", false);
    	put("gameOn", false);
    	put("attackOn", false);
    	put("defenseOn", false);
    	put("moveOn", false);
    	put("placeFirstAntsOn", false);
    	put("placeAntsOn", false);
    	put("availableAntsRefill", true);
    	put("placeAnthillOn", false);
    }};

   // ecran1, login: 	   full:false ,  !e.playerAlive
    //ecran2, full:			full: true, !e.playerAlive 
    //ecran3, sasOn:		e.playerAlive, gameSetOn == false
    //ecran4, setOn: 		e.playerAlive, gameSetOn == true 
    //ecrans5(+n), gameOn: 	e.playerAlive, gameSetOn == false, gameOn == true 
    
    
  //---------------- ARENA--------------------
	
  	protected Territory territoryAttacker;
  	protected Territory territoryTarget;
  	protected int nbrDiceAttack;
  	protected int nbrDiceDefense;


  	
  	//-------------------------------------------
    
    
    public int getNbAnthill() {
		return nbAnthill;
	}
	public Territory getTerritoryAttacker() {
		return territoryAttacker;
	}
	public void setTerritoryAttacker(Territory territoryAttacker) {
		this.territoryAttacker = territoryAttacker;
	}
	public Territory getTerritoryTarget() {
		return territoryTarget;
	}
	public void setTerritoryTarget(Territory territoryTarget) {
		this.territoryTarget = territoryTarget;
	}
	public int getNbrDiceAttack() {
		return nbrDiceAttack;
	}
	public void setNbrDiceAttack(int nbrDiceAttack) {
		this.nbrDiceAttack = nbrDiceAttack;
	}
	public int getNbrDiceDefense() {
		return nbrDiceDefense;
	}
	public void setNbrDiceDefense(int nbrDiceDefense) {
		this.nbrDiceDefense = nbrDiceDefense;
	}
	
	public void setNbAnthill(int nbAnthill) {
		this.nbAnthill = nbAnthill;
	}
	public HashMap<String, Boolean> getDivOn() {
		return divOn;
	}
	public void setDivOn(HashMap<String, Boolean> divOn) {
		this.divOn = divOn;
	}

	public ArrayList<String> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}
	public ArrayList<Family> getAllFamilies() {
		return allFamilies;
	}
	public void setAllFamilies(ArrayList<Family> allFamilies) {
		this.allFamilies = allFamilies;
	}
	public ArrayList<Territory> getAllTerritories() {
		return allTerritories;
	}
	public void setAllTerritories(ArrayList<Territory> allTerritories) {
		this.allTerritories = allTerritories;
	}
	public ArrayList<Player> getPlayersAlive() {
		return playersAlive;
	}
	public void setPlayersAlive(ArrayList<Player> playersAlive) {
		this.playersAlive = playersAlive;
	}
	public Player getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}
	public int getPathExist() {
		return pathExist;
	}
	public void setPathExist(int pathExist) {
		this.pathExist = pathExist;
	}
	
}

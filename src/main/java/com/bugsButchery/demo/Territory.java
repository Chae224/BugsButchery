package com.bugsButchery.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
public class Territory {

	@Id
	@GeneratedValue
	private int territoryId;
	private String territoryName;
	private int territoryValue;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "frontier", 
			  joinColumns = @JoinColumn(name = "territory_id"),
			  inverseJoinColumns = @JoinColumn(name = "frontier_id"))
	
    @JsonIgnoreProperties("territoryFrontiers")
	private List<Territory> territoryFrontiers;
	private int territoryFamily;
	@Transient
	private boolean isAnthill;	
	@Transient
	private int territoryAntsNb=0;
	
    @JsonIgnoreProperties("playerTerritoryList")
	@Transient
	private Player territoryOwner = null;
	
	public Territory() {
		super();
	}
	
	public int getTerritoryId() {
		return territoryId;
	}
	public void setTerritoryId(int territoryId) {
		this.territoryId = territoryId;
	}
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	public int getTerritoryValue() {
		return territoryValue;
	}
	public void setTerritoryValue(int territoryValue) {
		this.territoryValue = territoryValue;
	}
	public List<Territory> getTerritoryFrontiers() {
		return territoryFrontiers;
	}
	public void setTerritoryFrontiers(List<Territory> territoryFrontiers) {
		this.territoryFrontiers = territoryFrontiers;
	}
	public Player getTerritoryOwner() {
		return territoryOwner;
	}
	public void setTerritoryOwner(Player territoryOwner) {
		System.out.println("Le set terrirtory owner se fait");
		this.territoryOwner = territoryOwner;
	}
	public boolean isAnthill() {
		return isAnthill;
	}
	public void setAnthill(boolean isAnthill) {
		this.isAnthill = isAnthill;
	}
	public int getTerritoryFamily() {
		return territoryFamily;
	}
	public void setTerritoryFamily(int territoryFamily) {
		this.territoryFamily = territoryFamily;
	}
	public int getTerritoryAntsNb() {
		return territoryAntsNb;
	}
	public void setTerritoryAntsNb(int territoryAntsNb) {
		this.territoryAntsNb = territoryAntsNb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + territoryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Territory other = (Territory) obj;
		if (territoryId != other.territoryId)
			return false;
		return true;
	}
	
	
}

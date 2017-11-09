package com.webscrape.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity (name="betmatch")
public class BetMatch implements java.io.Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	//@Id @Column(name="matchid")
	//private String matchid;
	
	@Column(name="teama")	
	private String teama;
	
	@Column(name="data")	
	private String data;
	
	@Column(name="ora")	
	private String ora;
	
	@Column(name="teamb")	
	private String teamb;
	
	/*public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}*/
	public String getTeama() {
		return teama;
	}
	public void setTeama(String teama) {
		this.teama = teama;
	}
	public String getTeamb() {
		return teamb;
	}
	public void setTeamb(String teamb) {
		this.teamb = teamb;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
    
	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL,orphanRemoval = true)
    /*@JoinColumns({ 
      @JoinColumn(name="matchid", referencedColumnName="matchid"),
    })*/
	private List <Bet> bets = new ArrayList<Bet>();

	public List<Bet> getBets() {
		return bets;
	}
	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

}

package com.webscrape.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="bet")
public class Bet  implements java.io.Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "share")
	private double share;
	
	@Column(name = "betid")
	private String betid;
	
	@Column(name = "matchid")	
	private String matchid;
	
	@Column(name = "type")	
	private String type;
	
	@Column(name = "time", nullable=false, updatable = false, insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")	
	private Timestamp time;

	public String getBetid() {
		return betid;
	}
	public void setBetid(String betid) {
		this.betid = betid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getShare() {
		return share;
	}
	public void setShare(double share) {
		this.share = share;
	}
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchid" ,insertable=false, updatable=false)
    private BetMatch match;

	public BetMatch getMatch() {
		return match;
	}
	public void setMatch(BetMatch match) {
		this.match = match;
	}
    
}

	package com.example.demo.Model;
	
	import javax.persistence.Entity;
	import javax.persistence.Id;
	
	@Entity
	public class Students {
		
		@Id 
	private int id;
	private String name;
	private String tech;
	private String loc;
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	
	}

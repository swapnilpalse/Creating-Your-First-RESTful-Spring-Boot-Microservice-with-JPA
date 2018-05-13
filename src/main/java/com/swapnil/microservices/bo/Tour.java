package com.swapnil.microservices.bo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Tour implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String title;
	@Column(length=1000000)
	private String description;
	@Column(length=1000000)
	private String blurb;
	@Column
	private Integer price;
	@Column
	private String duration;
	@Column(length=1000000)
	private String bullets;
	@Column(length=1000000)
	private String keywords;
	@ManyToOne
	private TourPackage tourPackage;
	@Column
	private Difficulty difficulty;
	@Column 
	private Region region;

	
	
	public Tour() {
		super();
	}

	public Tour(String title, String description, String blurb, Integer price, String duration, String bullets,
			String keywords, TourPackage tourPackage,Difficulty difficulty, Region region) {
		super();
		this.title = title;
		this.description = description;
		this.blurb = blurb;
		this.price = price;
		this.duration = duration;
		this.bullets = bullets;
		this.keywords = keywords;
		this.tourPackage = tourPackage;
		this.region=region;
		this.difficulty=difficulty;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBullets() {
		return bullets;
	}

	public void setBullets(String bullets) {
		this.bullets = bullets;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public TourPackage getTourPackage() {
		return tourPackage;
	}

	public void setTourPackage(TourPackage tourPackage) {
		this.tourPackage = tourPackage;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
}

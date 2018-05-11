package com.swapnil.microservices.bo;

import javax.persistence.*;

@Entity
public class TourPackage {
@Id
private String code;
@Column
private String name;


public TourPackage() {
	super();
}


public TourPackage(String code, String name) {
	super();
	this.code = code;
	this.name = name;
}


public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


@Override
public String toString() {
	return "TourPackage [code=" + code + ", name=" + name + "]";
}



}

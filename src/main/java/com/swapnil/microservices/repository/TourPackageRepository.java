package com.swapnil.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.swapnil.microservices.bo.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String>{
TourPackage findByName(String name);
}

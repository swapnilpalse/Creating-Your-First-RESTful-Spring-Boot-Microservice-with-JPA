package com.swapnil.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.swapnil.microservices.bo.Tour;

public interface TourRepository extends CrudRepository<Tour, Long>{

}

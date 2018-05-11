package com.swapnil.microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.microservices.bo.Tour;
import com.swapnil.microservices.bo.TourPackage;
import com.swapnil.microservices.repository.TourPackageRepository;

@Service
public class TourPackageService {
	private TourPackageRepository tourPackageRepository;

	@Autowired
	public TourPackageService(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}
	public TourPackage createTourPackageRepository(String code, String name) {
		if(!tourPackageRepository.existsById(code)) {
			tourPackageRepository.save(new TourPackage(code, name));
		}
		return null;
	}
	
	public Iterable<TourPackage> lookup(){
		return tourPackageRepository.findAll();
	}
	
	public long total() {
		return tourPackageRepository.count(); 
	}
}

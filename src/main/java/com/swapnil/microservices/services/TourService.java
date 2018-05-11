package com.swapnil.microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.microservices.bo.Difficulty;
import com.swapnil.microservices.bo.Region;
import com.swapnil.microservices.bo.Tour;
import com.swapnil.microservices.bo.TourPackage;
import com.swapnil.microservices.repository.TourPackageRepository;
import com.swapnil.microservices.repository.TourRepository;

@Service
public class TourService {
	private TourPackageRepository tourPackageRepository;
	private TourRepository tourRepository;

	@Autowired
	public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
		this.tourPackageRepository = tourPackageRepository;
		this.tourRepository = tourRepository;
	}

	public Tour createTour(String title, String description, String blurb, Integer price, String duration,
			String bullets, String keywords, String tourPackageName, Difficulty difficult, Region region) {
		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName);

		return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage,
				difficult, region));

	}
	
	public Iterable<Tour> lookup(){
		return tourRepository.findAll();
	}
	public long total() {
		return tourRepository.count();
	}
}

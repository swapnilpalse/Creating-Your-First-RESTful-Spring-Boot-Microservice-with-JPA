package com.swapnil.microservices;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.microservices.bo.Difficulty;
import com.swapnil.microservices.bo.Region;
import com.swapnil.microservices.services.TourPackageService;
import com.swapnil.microservices.services.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@SpringBootApplication
public class MicroservicesApplication implements CommandLineRunner {
	@Autowired
	private TourPackageService tourPackageService;
	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

	static class TourFromFile {
		private String packageType, title, description, blurb, price, length, bullets, keywords, difficulty, region;

		/**
		 * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile
		 * Object.
		 *
		 * @return a List of TourFromFile objects.
		 * @throws IOException
		 *             if ObjectMapper unable to open file.
		 */
		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).readValue(
					TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),
					new TypeReference<List<TourFromFile>>() {
					});
		}
	}

	@Override
	public void run(String... args) throws Exception {
		tourPackageService.createTourPackageRepository("BC", "Backpack Cal");
		tourPackageService.createTourPackageRepository("CC", "California Calm");
		tourPackageService.createTourPackageRepository("CH", "California Hot springs");
		tourPackageService.createTourPackageRepository("CY", "Cycle California");
		tourPackageService.createTourPackageRepository("DS", "From Desert to Sea");
		tourPackageService.createTourPackageRepository("KC", "Keywood Winds");
		tourPackageService.createTourPackageRepository("NW", "Nature Watch");
		tourPackageService.createTourPackageRepository("SC", "Snowboard Cali");
		tourPackageService.createTourPackageRepository("TC", "Taste of California");
		tourPackageService.lookup().forEach(tourPackage -> System.out.println(tourPackage));
		TourFromFile.importTours().forEach(t-> tourService.createTour(t.title, t.description, t.blurb, Integer.parseInt(t.price), t.length, t.bullets, t.keywords, t.packageType, Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region)));
		System.out.println("Number of Tours=" +tourService.total());
	}
}

package com.swapnil.microservices.web;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.microservices.bo.Tour;
import com.swapnil.microservices.bo.TourRating;
import com.swapnil.microservices.bo.TourRatingPk;
import com.swapnil.microservices.repository.TourRatingRepository;
import com.swapnil.microservices.repository.TourRepository;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
	TourRatingRepository tourRatingRepository;
	TourRepository tourRepository;

	@Autowired
	public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
		this.tourRatingRepository = tourRatingRepository;
		this.tourRepository = tourRepository;
	}

	public TourRatingController() {

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable(value = "tourId") int tourId,
			@RequestBody @Validated RatingDto ratingDto) {
		Tour tour = verifyTour(tourId);
		tourRatingRepository.save(new TourRating(new TourRatingPk(tour, ratingDto.getCustomerId()),
				ratingDto.getScore(), ratingDto.getComment()));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId,Pageable pageable) {
		verifyTour(tourId);
		Page<TourRating> tourRatingPage= tourRatingRepository.findByPkTourId(tourId, pageable);
		List<RatingDto> ratingDtos=tourRatingPage.getContent().stream().map(tourRating -> toDto(tourRating)).collect(Collectors.toList());
		return new PageImpl<RatingDto>(ratingDtos,pageable,tourRatingPage.getTotalPages());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/average")
	public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
		verifyTour(tourId);
		List<TourRating> tourRatings = tourRatingRepository.findByPkTourId(tourId);
		OptionalDouble average = tourRatings.stream().mapToInt(TourRating::getScore).average();
		return new AbstractMap.SimpleEntry<String, Double>("avergae",
				average.isPresent() ? average.getAsDouble() : null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId,
			@RequestBody @Validated RatingDto ratingDto) {
		TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
		tourRating.setScore(ratingDto.getScore());
		tourRating.setComment(ratingDto.getComment());
		return toDto(tourRatingRepository.save(tourRating));

	}

	@RequestMapping(method = RequestMethod.PATCH)
	public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,
			@RequestBody @Validated RatingDto ratingDto) {
		TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
		if (ratingDto.getScore() != null) {
			tourRating.setScore(ratingDto.getScore());
		}
		if (ratingDto.getComment() != null) {
			tourRating.setComment(ratingDto.getComment());
		}
		return toDto(tourRatingRepository.save(tourRating));

	}

	@RequestMapping(method = RequestMethod.DELETE,path="/{customerId}")
	public void delete(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
		TourRating tourRating=tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId);
		tourRatingRepository.delete(tourRating);
	}

			
	private RatingDto toDto(TourRating tourRating) {
		return new RatingDto(tourRating.getScore(), tourRating.getComment(), tourRating.getPk().getCustomerId());
	}

	private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
		TourRating rating = tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId);
		if (rating == null) {
			throw new NoSuchElementException("Tour-Rating pair for request(" + tourId + " for customer" + customerId);
		}
		return rating;
	}

	private Tour verifyTour(int tourId) throws NoSuchElementException {
		Tour tour = tourRepository.findById(tourId).get();
		if (tour == null) {
			throw new NoSuchElementException("Tour does not exist " + tourId);
		}
		return tour;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();

	}

}

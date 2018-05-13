package com.swapnil.microservices.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.swapnil.microservices.bo.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

	@Override
	@RestResource(exported=false)
	void delete(Tour arg0);
	@Override
	@RestResource(exported=false)
void deleteAll() ;

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends Tour> arg0) ;

	@Override
	@RestResource(exported=false)
	void deleteById(Long arg0);

	@Override
	@RestResource(exported=false)
	<S extends Tour> S save(S arg0);

	@Override
	@RestResource(exported=false)
	<S extends Tour> Iterable<S> saveAll(Iterable<S> arg0);
	
}

package com.swapnil.microservices.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.swapnil.microservices.bo.TourPackage;
@RepositoryRestResource(collectionResourceRel="packages", path="packages")
public interface TourPackageRepository extends CrudRepository<TourPackage, String>{


	TourPackage findByName(@Param("name")String name);

	@Override
	@RestResource(exported=false)
	void delete(TourPackage arg0);

	@Override
	@RestResource(exported=false)
	void deleteAll();

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends TourPackage> arg0);


	@Override
	@RestResource(exported=false)
	<S extends TourPackage> S save(S arg0);

	@Override
	@RestResource(exported=false)
	<S extends TourPackage> Iterable<S> saveAll(Iterable<S> arg0);
	
	
}

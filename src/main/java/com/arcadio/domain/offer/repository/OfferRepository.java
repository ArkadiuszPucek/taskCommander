package com.arcadio.domain.offer.repository;

import com.arcadio.domain.offer.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {


}

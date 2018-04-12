package com.netcracker.repository;

import com.netcracker.jpa.CarInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsInfoRepository extends CrudRepository<CarInfo, Integer> {
}

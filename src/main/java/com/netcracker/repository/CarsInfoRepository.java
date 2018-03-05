package com.netcracker.repository;

import com.netcracker.jpa.CarInfo;
import org.springframework.data.repository.CrudRepository;

public interface CarsInfoRepository extends CrudRepository<CarInfo, Integer> {
}

package com.demo.services.api;

import com.demo.model.entities.City;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface CityService {
    void registerCity(City city);
}

package com.demo.services.impl;

import com.demo.dao.CityDao;
import com.demo.model.entities.City;
import com.demo.services.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public void registerCity(City city) {
        this.cityDao.saveAndFlush(city);
    }
}

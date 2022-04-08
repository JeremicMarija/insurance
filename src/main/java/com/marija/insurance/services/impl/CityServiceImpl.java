package com.marija.insurance.services.impl;

import com.marija.insurance.domain.City;
import com.marija.insurance.repository.CityRepository;
import com.marija.insurance.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}

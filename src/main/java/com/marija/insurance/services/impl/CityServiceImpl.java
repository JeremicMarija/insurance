package com.marija.insurance.services.impl;

import com.marija.insurance.domain.City;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.exception.ResourceNotFoundException;
import com.marija.insurance.repository.CityRepository;
import com.marija.insurance.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public City getCityById(long id){
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()){
            return city.get();
        }else {
            throw new ResourceNotFoundException("City","Id", id);
        }
    }
}

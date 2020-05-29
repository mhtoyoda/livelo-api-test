package com.toyoda.livelo.service;

import com.toyoda.livelo.dto.CityDTO;
import com.toyoda.livelo.exception.ResourceDuplicationException;
import com.toyoda.livelo.exception.ResourceNotFoundException;
import com.toyoda.livelo.model.City;
import com.toyoda.livelo.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityRepository cityRepository;

    public CityDTO saveCity(CityDTO cityDTO) {
        if(cityRepository.findByNameAndState(cityDTO.getName(), cityDTO.getState()).isPresent()) {
            throw new ResourceDuplicationException("City/State Duplicate");
        }
        City city = modelMapper.map(cityDTO, City.class);
        return modelMapper.map(cityRepository.save(city), CityDTO.class);
    }

    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOList = new ArrayList<>();
        cityRepository.findAll().forEach(c -> cityDTOList.add(modelMapper.map(c, CityDTO.class)));
        return cityDTOList;
    }

    public List<CityDTO> findByName(String name) {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
        cityList.forEach(c -> cityDTOList.add(modelMapper.map(c, CityDTO.class)));
        return cityDTOList;
    }

    public List<CityDTO> findByState(String state) {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityRepository.findByState(state).orElseThrow(ResourceNotFoundException::new);
        cityList.forEach(c -> cityDTOList.add(modelMapper.map(c, CityDTO.class)));
        return cityDTOList;
    }
}

package com.toyoda.livelo.service;

import com.toyoda.livelo.dto.CityDTO;
import com.toyoda.livelo.dto.ClientDTO;
import com.toyoda.livelo.exception.ResourceArgumentInvalidException;
import com.toyoda.livelo.exception.ResourceNotFoundException;
import com.toyoda.livelo.model.City;
import com.toyoda.livelo.model.Client;
import com.toyoda.livelo.repository.CityRepository;
import com.toyoda.livelo.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    public ClientDTO saveClient(ClientDTO clientDTO) {
        CityDTO cityDTO = clientDTO.getCity();
        City city = cityRepository.findByNameAndState(cityDTO.getName(), cityDTO.getState()).orElseThrow(ResourceArgumentInvalidException::new);
        Client client = modelMapper.map(clientDTO, Client.class);
        client.setCity(city);
        return modelMapper.map(clientRepository.save(client), ClientDTO.class);
    }

    public List<ClientDTO> findAll() {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientRepository.findAll().forEach(c -> clientDTOS.add(modelMapper.map(c, ClientDTO.class)));
        return clientDTOS;
    }

    public ClientDTO findByName(String name) {
        Client client = clientRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
        return modelMapper.map(client, ClientDTO.class);
    }

    public ClientDTO findById(String id) {
        Client client = clientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return modelMapper.map(client, ClientDTO.class);
    }

    public void removeClient(String id) {
        Client client = clientRepository.findById(id).orElseThrow(ResourceArgumentInvalidException::new);
        clientRepository.delete(client);
    }

    public ClientDTO updateClient(String id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id).orElseThrow(ResourceArgumentInvalidException::new);
        client.setName(clientDTO.getName());
        client.setGender(clientDTO.getGender());
        client.setBirthDay(clientDTO.getBirthDay());
        City city = client.getCity();
        City cityFinded = cityRepository.findByNameAndState(clientDTO.getCity().getName(), clientDTO.getCity().getState()).orElseThrow(ResourceArgumentInvalidException::new);
        city.setId(cityFinded.getId());
        city.setName(cityFinded.getName());
        city.setState(cityFinded.getState());
        clientRepository.save(client);
        return modelMapper.map(clientRepository.save(client), ClientDTO.class);
    }
}

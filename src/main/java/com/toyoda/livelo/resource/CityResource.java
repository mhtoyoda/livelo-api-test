package com.toyoda.livelo.resource;

import com.toyoda.livelo.dto.CityDTO;
import com.toyoda.livelo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityResource {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> list() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CityDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(cityService.findByName(name));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<CityDTO>> findByState(@PathVariable String state) {
        return ResponseEntity.ok(cityService.findByState(state));
    }

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok().body(cityService.saveCity(cityDTO));
    }
}

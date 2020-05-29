package com.toyoda.livelo.resource;

import com.toyoda.livelo.dto.ClientDTO;
import com.toyoda.livelo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> list() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClientDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(clientService.findByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(clientService.saveClient(clientDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeClient(@PathVariable String id) {
        clientService.removeClient(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@PathVariable String id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(clientService.updateClient(id, clientDTO));
    }
}

package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Client;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import me.timofeev.jewelryshop.repo.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepository;
    private final ChequeRepository chequeRepository;

    public ClientController(ClientRepository clientRepository, ChequeRepository chequeRepository) {
        this.clientRepository = clientRepository;
        this.chequeRepository = chequeRepository;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        optionalClient.ifPresent(client -> {
            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());

            if (client.getContactInfo() != null && updatedClient.getContactInfo() != null) {
                client.getContactInfo().setPhone(updatedClient.getContactInfo().getPhone());
                client.getContactInfo().setEmail(updatedClient.getContactInfo().getEmail());
                client.getContactInfo().setAddress(updatedClient.getContactInfo().getAddress());
            } else if (updatedClient.getContactInfo() != null) {
                client.setContactInfo(updatedClient.getContactInfo());
            }

            clientRepository.save(client);
        });

        return optionalClient.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getClient() != null && cheque.getClient().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            clientRepository.deleteById(id);
        });
    }
}
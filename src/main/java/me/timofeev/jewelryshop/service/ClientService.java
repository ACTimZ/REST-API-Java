package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Client;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import me.timofeev.jewelryshop.repo.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ChequeRepository chequeRepository;

    public ClientService(ClientRepository clientRepository, ChequeRepository chequeRepository) {
        this.clientRepository = clientRepository;
        this.chequeRepository = chequeRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Long id, Client updatedClient) {
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

    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getClient() != null && cheque.getClient().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            clientRepository.delete(client);
        }
    }
}
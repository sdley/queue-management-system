package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.model.Client;
import sn.sdley.queueManagementSystem.repositories.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    // Méthodes pour gérer les clients

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        if (client != null){
            client.setAdresse(clientDetails.getAdresse());
            return clientRepository.save(client);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

//    public List<Client> getClientsByTicket(String ticket) {
//        return clientRepository.findByTicket(ticket);
//    }




}

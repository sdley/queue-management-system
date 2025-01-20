package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class FileAttente extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;

    @OneToMany(mappedBy = "fileAttente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Client> clients;

//  A revoir
    @ManyToOne
    private Admin admin;

    public FileAttente() {
    }


    // Getters, setters, constructeurs, méthodes

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
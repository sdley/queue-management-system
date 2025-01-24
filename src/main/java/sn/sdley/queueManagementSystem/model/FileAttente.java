package sn.sdley.queueManagementSystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "file_attente")
public class FileAttente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "fileAttente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Client> clients;

    //  A revoir
    /*
        ERROR 17864 --- [queueManagementSystem] [           main]
        j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA
        EntityManagerFactory: Collection
        'sn.sdley.queueManagementSystem.model.Admin.listeFileAttente' is 'mappedBy'
        a property named 'admin' which does not exist in the target entity
        'sn.sdley.queueManagementSystem.model.FileAttente'
     */
    @ManyToOne
    private Admin admin;

    public FileAttente() {
    }

    public FileAttente(Service service, List<Client> clients) {
        this.service = service;
        this.clients = clients;
    }

    // Getters, setters, constructeurs, méthodes


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.queueManagementSystem.model.FileAttente;

public interface FileAttenteRepository extends JpaRepository<FileAttente, Long> {
    FileAttente findByServiceNom(String serviceName);

}

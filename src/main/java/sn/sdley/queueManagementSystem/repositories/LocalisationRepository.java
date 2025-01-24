package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.model.Service;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
    List<Localisation> findByService(Service service);

    Localisation findByNom(String name);
}

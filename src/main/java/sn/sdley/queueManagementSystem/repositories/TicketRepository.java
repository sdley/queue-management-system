package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.sdley.queueManagementSystem.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /** Récupérer la liste des clients ayant pris un ticket pour un service donné
     * findClientsByService_Nom : Service_Nom et ServiceNom sont identiques
     * Ils se traduits par Service.Nom correspondant à la colonne nom dans la table Service
     * @param nomService
     * @return
     */
    List<Ticket> findClientsByServiceNom(String nomService);

    List<Ticket> findByServiceNomAndLocalisation(String nomService, String localisation);


    /**
     * // Récupérer la liste des tickets pour un service et une localisation
     *     List<Ticket> findByService_NomAndService_Localisations_Nom(String nomService,
     *                                                                String nomLocalisation);
     * Service_Localisations est une liste, donc il peut y avoir plusieurs localisations pour un service.
     * Spring Data JPA ne sait pas comment filtrer une seule localisation correctement dans une liste.
     * Utilisation d'une requête JPQL pour faire une jointure correcte entre Ticket, Service et Localisation.


    @Query("SELECT t FROM Ticket t JOIN t.service s JOIN s.localisations l WHERE
     s.nom = :nomService AND l.nom = :nomLocalisation")
    List<Ticket> findByServiceAndLocalisation(@Param("nomService") String nomService,
                                              @Param("nomLocalisation") String nomLocalisation);

    @Query("SELECT t FROM Ticket t WHERE t.service.nom = :nomService AND EXISTS
    (SELECT l FROM Localisation l WHERE l.service = t.service AND l.nom = :nomLocalisation)")
    List<Ticket> findByServiceAndLocalisation(@Param("nomService") String nomService,
                                              @Param("nomLocalisation") String nomLocalisation);
        */

    // Methode pour recuperer le dernier ticket pour un service donné
    @Query(value = "SELECT * FROM ticket WHERE nom_service = " +
            "(SELECT nom FROM service WHERE nom = :serviceName) ORDER BY id DESC LIMIT 1",
            nativeQuery = true)
    Optional<Ticket> findLastTicketByService(@Param("serviceName") String serviceName);

    // Methode pour recuperer le ticket en cours de traitement
    Ticket findByServiceNomAndLocalisationAndStatus(String serviceName,
                                                    String location, String status);

    Page<Ticket> findByServiceNomAndLocalisationAndStatusAndAgentIdOrderByIdAsc(String serviceNom,
                                                                        String localisation,
                                                                        String status,
                                                                        Long agentId,
                                                                        Pageable pageable);

    Page<Ticket> findByServiceNomAndLocalisationAndStatusAndAgentIdOrderByIdDesc(String serviceNom,
                                                                                String localisation,
                                                                                String status,
                                                                                Long agentId,
                                                                                Pageable pageable);

    Page<Ticket> findByServiceNomAndLocalisationAndStatusAndAgentIdAndIsPrevClientOrderByIdDesc(
            String serviceName,String location, String status, Long agentId, boolean isPrevClient,
            Pageable pageable);

    // method for admin dashboard
    /*
        Cette requête SQL JPA permet de récupérer une vue d’ensemble des files d’attente
        pour l'administrateur. Elle regroupe les tickets par service et localisation,
        tout en fournissant les informations suivantes :
            Le nombre de clients en attente 🕒
            Le ticket actuellement en cours de traitement 🎫
            Le prochain ticket en attente ⏭️
     */
    @Query("SELECT t.service.nom, t.localisation, " +
            "COUNT(CASE WHEN t.status = 'En Attente' THEN 1 ELSE NULL END), " +
            "(SELECT t2.numero FROM Ticket t2 WHERE t2.status = 'En Cours' AND t2.service.nom = t.service.nom " +
            "AND t2.localisation = t.localisation ORDER BY t2.id ASC LIMIT 1), " +
            "(SELECT t3.numero FROM Ticket t3 WHERE t3.status = 'En Attente' AND t3.service.nom = t.service.nom " +
            "AND t3.localisation = t.localisation ORDER BY t3.id ASC LIMIT 1) " +
            "FROM Ticket t " +
            "GROUP BY t.service.nom, t.localisation")
    List<Object[]> getQueueSummary();

}

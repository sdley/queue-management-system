package sn.sdley.queueManagementSystem.model;


import jakarta.persistence.*;

@Entity
public class Ticket {

    /*
        Cette classe correspond en realite a la classe Client
        Mais pourvu qu'un client ne peut avoir qu'un seul ticket a la fois
        et ne peut etre dans qu'une seule file d'attente a la fois,
        Nous avons estime qu'il n'est pas necessaire de conserver cette classe
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private int position;
    private int peopleAhead;
    private String status;

//    @ManyToOne
//    private Service service;

//    @ManyToOne
//    private Location location;

    // Getters and Setters
}


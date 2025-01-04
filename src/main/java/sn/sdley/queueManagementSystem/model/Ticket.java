package sn.sdley.queueManagementSystem.model;


import jakarta.persistence.*;

@Entity
public class Ticket {
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


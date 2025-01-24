//package sn.sdley.queueManagementSystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import sn.sdley.queueManagementSystem.model.Localisation;
//import sn.sdley.queueManagementSystem.model.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class QueueConfiguration {
//    @Bean
//    public Service Orange() {
//        return new Service("Orange", "Service de télécommunication");
//    }
//
//    @Bean
//    public Service Seneau() {
//        return new Service("Seneau", "Service d\'approvisionnement en eau");
//    }
//
//    @Bean
//    public Service Bank() {
//        return new Service("Bank", "Service bancaire");
//    }
//
//    // Localisations
//    @Bean
//    public Localisation citeKeurGorgui() {
//        return new Localisation("Cité Keur Gorgui", Orange());
//    }
//
//    @Bean
//    public Localisation rufisque() {
//        return new Localisation("Rufisque", Orange());
//    }
//
//    @Bean
//    public Localisation citeKeurGorgui2() {
//        return new Localisation("Cité Keur Gorgui", Bank());
//    }
//
//    @Bean
//    public Localisation keurMassar() {
//        return new Localisation("Keur Massar", Seneau());
//    }
//
//    @Bean
//    public Localisation rufisque2() {
//        return new Localisation("Rufisque", Seneau());
//    }
//
//    @Bean
//    public Localisation rufisque3() {
//        return new Localisation("Rufisque", Bank());
//    }
//
//    // create a list of services
//    @Bean
//    public List<Service> services() {
//        return Arrays.asList(Orange(), Seneau(), Bank());
//    }
//
//    // create a list of localisations
//    @Bean
//    public List<Localisation> localisations() {
//        return Arrays.asList(citeKeurGorgui(), rufisque(), citeKeurGorgui2(), keurMassar(), rufisque2(), rufisque3());
//    }
//
//    //
//}

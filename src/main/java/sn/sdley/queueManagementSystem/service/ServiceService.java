package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.repositories.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    // Méthodes pour gérer les services

    public List<sn.sdley.queueManagementSystem.model.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public sn.sdley.queueManagementSystem.model.Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public sn.sdley.queueManagementSystem.model.Service createService(sn.sdley.queueManagementSystem.model.Service service) {
        return serviceRepository.save(service);
    }

    public sn.sdley.queueManagementSystem.model.Service updateService(Long id, sn.sdley.queueManagementSystem.model.Service serviceDetails) {
        sn.sdley.queueManagementSystem.model.Service service = getServiceById(id);
        if (service != null){
            service.setNom(serviceDetails.getNom());
            service.setLocalisations(serviceDetails.getLocalisations());
            return serviceRepository.save(service);
        }
        return null;
    }


}

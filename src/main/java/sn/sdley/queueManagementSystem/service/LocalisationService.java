package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.model.Localisation;
import sn.sdley.queueManagementSystem.repositories.LocalisationRepository;

import java.util.List;

@Service
public class LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    public List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }

    public Localisation getLocalisationById(Long id) {
        return localisationRepository.findById(id).orElse(null);
    }

    public Localisation createLocalisation(Localisation localisation) {
        return localisationRepository.save(localisation);
    }

    public Localisation updateLocalisation(Long id, Localisation localisationDetails) {
        Localisation localisation = getLocalisationById(id);
        if (localisation != null){
            localisation.setNom(localisationDetails.getNom());
//            localisation.setServices(localisationDetails.getServices());
            return localisationRepository.save(localisation);
        }
        return null;
    }

    public void deleteLocalisation(Long id) {
        localisationRepository.deleteById(id);
    }

    public void deleteAllLocalisations() {
        localisationRepository.deleteAll();
    }


}

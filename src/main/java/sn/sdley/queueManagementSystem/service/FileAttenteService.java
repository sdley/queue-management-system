package sn.sdley.queueManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.sdley.queueManagementSystem.model.FileAttente;
import sn.sdley.queueManagementSystem.repositories.FileAttenteRepository;

import java.util.List;

@Service
public class FileAttenteService {

    @Autowired
    private FileAttenteRepository fileAttenteRepository;

    // Méthodes pour gérer les files d'attente
    public List<FileAttente> getAllFilesAttente() {
        return fileAttenteRepository.findAll();
    }

    public FileAttente getFileAttenteById(Long id) {
        return fileAttenteRepository.findById(id).orElse(null);
    }

    public FileAttente createFileAttente(FileAttente fileAttente) {
        return fileAttenteRepository.save(fileAttente);
    }

    // Find file d'attente by service id
    public FileAttente getFileAttenteByServiceName(String serviceName) {
        return fileAttenteRepository.findByServiceNom(serviceName);
    }

    // Find `file attente` clients by service id
//    public List<Client> getFileAttenteClientsByServiceName(String serviceName) {
//        return fileAttenteRepository.findByServiceNom(serviceName).getClients();
//    }

    public FileAttente updateFileAttente(Long id, FileAttente fileAttenteDetails) {
        FileAttente fileAttente = getFileAttenteById(id);
        if (fileAttente != null){
//            fileAttente.setNom(fileAttenteDetails.getNom());
            fileAttente.setClients(fileAttenteDetails.getClients());
            return fileAttenteRepository.save(fileAttente);
        }
        return null;
    }

    public void deleteFileAttente(Long id) {
        fileAttenteRepository.deleteById(id);
    }
}

package com.campus.visitor.service;

import com.campus.visitor.model.StatutVisite;
import com.campus.visitor.model.Visiteur;
import com.campus.visitor.repository.VisiteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;



@Service
@RequiredArgsConstructor
public class VisiteurService {


    private final VisiteurRepository visiteurRepository;

    public Visiteur enregistrer(Visiteur visiteur) {
        visiteur.setNumeroBadge(genererBadge());

        visiteur.setStatut(StatutVisite.EN_ATTENTE);

        visiteur.setDateInscription(LocalDateTime.now());

        return visiteurRepository.save(visiteur);
    }

    public List<Visiteur> tousLesVisiteurs() {
        return visiteurRepository.findAll();
    }

    public Optional<Visiteur> trouverParId(Long id) {
        return visiteurRepository.findById(id);
    }

    public Optional<Visiteur> trouverParBadge(String numeroBadge) {
        return visiteurRepository.findByNumeroBadge(numeroBadge);
    }

    public void approuver(Long id) {
        Visiteur v = visiteurRepository.findById(id).orElseThrow();
        v.setStatut(StatutVisite.APPROUVE);
        visiteurRepository.save(v);
    }

    public void refuser(Long id) {
        Visiteur v = visiteurRepository.findById(id).orElseThrow();
        v.setStatut(StatutVisite.REFUSE);
        visiteurRepository.save(v);
    }

    public void enregistrerEntree(Long id) {
        Visiteur v = visiteurRepository.findById(id).orElseThrow();
        v.setStatut(StatutVisite.ENTRE);
        visiteurRepository.save(v);
    }

    public void enregistrerSortie(Long id) {
        Visiteur v = visiteurRepository.findById(id).orElseThrow();
        v.setStatut(StatutVisite.SORTI);
        visiteurRepository.save(v);
    }


    public List<Visiteur> rechercherParNom(String terme) {
        return visiteurRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(terme, terme);
    }

    public List<Visiteur> filtrerParStatut(StatutVisite statut) {
        return visiteurRepository.findByStatut(statut);
    }


    public long compterTotal()      { return visiteurRepository.count(); }
    public long compterEnAttente()  { return visiteurRepository.countByStatut(StatutVisite.EN_ATTENTE); }
    public long compterPresents()   { return visiteurRepository.countByStatut(StatutVisite.ENTRE); }
    public long compterApprouves()  { return visiteurRepository.countByStatut(StatutVisite.APPROUVE); }


    public void supprimer(Long id) {
        visiteurRepository.deleteById(id);
    }

    private String genererBadge() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String nombre = String.format("%04d", new Random().nextInt(9999));
        return "BADGE-" + date + "-" + nombre;
    }
}

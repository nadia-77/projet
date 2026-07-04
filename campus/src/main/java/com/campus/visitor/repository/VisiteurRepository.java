package com.campus.visitor.repository;

import com.campus.visitor.model.StatutVisite;
import com.campus.visitor.model.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VisiteurRepository extends JpaRepository<Visiteur, Long> {


    Optional<Visiteur> findByEmail(String email);

    Optional<Visiteur> findByNumeroBadge(String numeroBadge);

    List<Visiteur> findByStatut(StatutVisite statut);

    List<Visiteur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
            String nom, String prenom
    );

    long countByStatut(StatutVisite statut);
}

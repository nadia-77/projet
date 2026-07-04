package com.campus.visitor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "visiteurs")
@Data
@NoArgsConstructor
public class Visiteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'adresse email n'est pas valide")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "La CIN ou passeport est obligatoire")
    private String cinPasseport;


    @NotBlank(message = "Veuillez indiquer la personne à visiter")
    private String personneVisitee;

    @NotBlank(message = "Le motif de la visite est obligatoire")
    @Column(length = 500)
    private String motifVisite;

    private String batimentDestination;

    private LocalDate dateVisitePrevue;
    @Column(unique = true)
    private String numeroBadge;

    @Enumerated(EnumType.STRING)
    private StatutVisite statut = StatutVisite.EN_ATTENTE;

    private LocalDateTime dateInscription = LocalDateTime.now();

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}

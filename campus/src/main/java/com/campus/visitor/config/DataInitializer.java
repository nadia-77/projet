package com.campus.visitor.config;

import com.campus.visitor.model.RoleUtilisateur;
import com.campus.visitor.model.Utilisateur;
import com.campus.visitor.repository.UtilisateurRepository;
import com.campus.visitor.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;

    @Override
    public void run(String... args) {

        if (!utilisateurRepository.existsByEmail("admin@campus.ma")) {
            Utilisateur admin = new Utilisateur();
            admin.setNom("Administrateur");
            admin.setPrenom("Campus");
            admin.setEmail("admin@campus.ma");
            admin.setMotDePasse("admin123");
            admin.setRole(RoleUtilisateur.ADMIN);
            utilisateurService.creer(admin);
            System.out.println("Compte Admin créé : admin@campus.ma / admin123");
        }

        if (!utilisateurRepository.existsByEmail("agent@campus.ma")) {
            Utilisateur agent = new Utilisateur();
            agent.setNom("Sécurité");
            agent.setPrenom("Agent");
            agent.setEmail("agent@campus.ma");
            agent.setMotDePasse("agent123");
            agent.setRole(RoleUtilisateur.AGENT);
            utilisateurService.creer(agent);
            System.out.println("Compte Agent créé : agent@campus.ma / agent123");
        }
    }
}

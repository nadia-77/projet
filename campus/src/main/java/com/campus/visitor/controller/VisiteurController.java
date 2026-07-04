package com.campus.visitor.controller;

import com.campus.visitor.model.StatutVisite;
import com.campus.visitor.model.Visiteur;
import com.campus.visitor.service.VisiteurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/visiteur")
@RequiredArgsConstructor
public class VisiteurController {

    private final VisiteurService visiteurService;


    @GetMapping("/liste")
    public String liste(Model model,
                        @RequestParam(required = false) String recherche,
                        @RequestParam(required = false) String statut) {

        if (recherche != null && !recherche.isBlank()) {

            model.addAttribute("visiteurs", visiteurService.rechercherParNom(recherche));
            model.addAttribute("recherche", recherche);

        } else if (statut != null && !statut.isBlank()) {

            model.addAttribute("visiteurs", visiteurService.filtrerParStatut(StatutVisite.valueOf(statut)));
            model.addAttribute("statutSelectionne", statut);

        } else {

            model.addAttribute("visiteurs", visiteurService.tousLesVisiteurs());
        }

        model.addAttribute("statuts", StatutVisite.values());
        return "visiteur/liste"; // → templates/visiteur/liste.html
    }


    @GetMapping("/inscription")
    public String formulaire(Model model) {
        model.addAttribute("visiteur", new Visiteur()); // objet vide pour le formulaire
        return "visiteur/inscription";
    }


    @PostMapping("/inscription")
    public String enregistrer(@Valid @ModelAttribute Visiteur visiteur,
                               BindingResult result,
                               RedirectAttributes ra) {

        if (result.hasErrors()) {
            return "visiteur/inscription";
        }

        Visiteur sauve = visiteurService.enregistrer(visiteur);
        ra.addFlashAttribute("messageBadge", sauve.getNumeroBadge());
        return "redirect:/visiteur/confirmation/" + sauve.getId();
    }


    @GetMapping("/confirmation/{id}")
    public String confirmation(@PathVariable Long id, Model model) {
        Visiteur visiteur = visiteurService.trouverParId(id).orElseThrow();
        model.addAttribute("visiteur", visiteur);
        return "visiteur/confirmation";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Visiteur visiteur = visiteurService.trouverParId(id).orElseThrow();
        model.addAttribute("visiteur", visiteur);
        return "visiteur/detail";
    }


    @PostMapping("/approuver/{id}")
    public String approuver(@PathVariable Long id, RedirectAttributes ra) {
        visiteurService.approuver(id);
        ra.addFlashAttribute("succes", "Visite approuvée avec succès !");
        return "redirect:/visiteur/detail/" + id;
    }


    @PostMapping("/refuser/{id}")
    public String refuser(@PathVariable Long id, RedirectAttributes ra) {
        visiteurService.refuser(id);
        ra.addFlashAttribute("erreur", "Visite refusée.");
        return "redirect:/visiteur/detail/" + id;
    }


    @PostMapping("/entree/{id}")
    public String entree(@PathVariable Long id, RedirectAttributes ra) {
        visiteurService.enregistrerEntree(id);
        ra.addFlashAttribute("succes", "Entrée enregistrée !");
        return "redirect:/visiteur/detail/" + id;
    }


    @PostMapping("/sortie/{id}")
    public String sortie(@PathVariable Long id, RedirectAttributes ra) {
        visiteurService.enregistrerSortie(id);
        ra.addFlashAttribute("succes", "Sortie enregistrée !");
        return "redirect:/visiteur/detail/" + id;
    }


    @PostMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id, RedirectAttributes ra) {
        visiteurService.supprimer(id);
        ra.addFlashAttribute("succes", "Visiteur supprimé.");
        return "redirect:/visiteur/liste";
    }


    @GetMapping("/scan")
    public String scanPage() {
        return "visiteur/scan";
    }

    @PostMapping("/scan")
    public String scanBadge(@RequestParam String numeroBadge, Model model) {
        return visiteurService.trouverParBadge(numeroBadge)
                .map(v -> "redirect:/visiteur/detail/" + v.getId())
                .orElseGet(() -> {
                    model.addAttribute("erreur", "Badge non trouvé : " + numeroBadge);
                    return "visiteur/scan";
                });
    }
}

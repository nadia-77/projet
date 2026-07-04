package com.campus.visitor.controller;

import com.campus.visitor.service.VisiteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final VisiteurService visiteurService;

    @GetMapping("/")
    public String accueil() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("total",      visiteurService.compterTotal());
        model.addAttribute("enAttente",  visiteurService.compterEnAttente());
        model.addAttribute("presents",   visiteurService.compterPresents());
        model.addAttribute("approuves",  visiteurService.compterApprouves());
        model.addAttribute("visiteurs",  visiteurService.tousLesVisiteurs());
        return "dashboard";
    }


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}

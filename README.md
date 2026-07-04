Système de Gestion des Visiteurs sur Campus

Ce projet est une application web développée avec Spring Boot.
L'objectif est de gérer numériquement les entrées et sorties des visiteurs sur le campus universitaire, en remplaçant le registre papier par un système digital avec badges virtuels.

Description

L'application permet à un visiteur de s'inscrire en ligne via un formulaire public sans avoir besoin de se connecter. Une fois la demande soumise, un numéro de badge unique est généré automatiquement. Un agent de sécurité consulte ensuite la demande et peut l'approuver ou la refuser. Si elle est approuvée, l'agent enregistre l'entrée du visiteur sur le campus au moment de son arrivée, puis sa sortie quand il repart. Toutes ces actions sont visibles dans un tableau de bord avec des statistiques en temps réel.

Fonctionnalités

L'application permet l'inscription d'un visiteur via un formulaire public, la génération automatique d'un badge virtuel avec numéro unique, l'approbation ou le refus d'une demande de visite par un agent, l'enregistrement des entrées et sorties sur le campus, la recherche d'un visiteur par nom ou par numéro de badge, l'affichage d'un tableau de bord avec les statistiques, et une connexion sécurisée avec deux rôles : Admin et Agent.

Architecture

Le projet suit l'architecture MVC en quatre couches. Le Model représente les tables de la base de données sous forme de classes Java annotées avec JPA. Le Repository s'occupe de l'accès à la base de données, Spring génère automatiquement les requêtes SQL à partir du nom des méthodes. Le Service contient toute la logique métier : enregistrer un visiteur, changer son statut, générer le badge. Le Controller reçoit les requêtes HTTP de l'utilisateur, appelle le service, et retourne la bonne page HTML.

Technologies utilisées

Le projet utilise Java 17 comme langage de programmation, Spring Boot 3.2.0 comme framework principal, Spring Data JPA avec Hibernate pour l'accès à la base de données MySQL, Spring Security pour l'authentification et la gestion des rôles, Thymeleaf comme moteur de templates HTML, Bootstrap 5.3 pour l'interface utilisateur, et Lombok pour réduire le code répétitif.

Structure du projet

Le projet contient les fichiers suivants : CampusVisitorApplication.java est le point de démarrage de l'application. Dans le dossier model se trouvent Visiteur.java, Utilisateur.java, StatutVisite.java et RoleUtilisateur.java qui représentent les tables MySQL. Dans le dossier repository se trouvent VisiteurRepository.java et UtilisateurRepository.java pour l'accès à la base. Dans le dossier service se trouvent VisiteurService.java pour la logique métier et UtilisateurService.java pour l'authentification. Dans le dossier controller se trouvent DashboardController.java et VisiteurController.java pour gérer les URLs. Dans le dossier config se trouvent SecurityConfig.java pour la sécurité et DataInitializer.java pour créer les comptes par défaut au démarrage. Les pages HTML se trouvent dans le dossier templates : dashboard.html, auth/login.html, visiteur/inscription.html, visiteur/confirmation.html, visiteur/liste.html, visiteur/detail.html et visiteur/scan.html.

Base de données

La base de données s'appelle campus_visitors et est créée automatiquement au démarrage. Elle contient deux tables principales. La table visiteurs stocke les informations du visiteur (nom, prénom, email, téléphone, CIN ou passeport), les informations de la visite (personne visitée, motif, bâtiment, date prévue), le numéro de badge généré automatiquement, le statut de la visite (EN_ATTENTE, APPROUVE, ENTRE, SORTI, REFUSE), et la date d'inscription. La table utilisateurs stocke les comptes de connexion avec leur rôle (ADMIN ou AGENT) et leur mot de passe chiffré avec BCrypt.

Installation et démarrage

Pour lancer le projet, il faut avoir Java 17, Maven et XAMPP installés sur la machine. Il faut d'abord démarrer XAMPP et activer les services Apache et MySQL. Ensuite, il faut ouvrir le fichier application.properties et vérifier que le nom d'utilisateur MySQL est root et que le mot de passe est vide si XAMPP est utilisé sans mot de passe. Puis lancer l'application avec la commande mvn spring-boot:run depuis le terminal, ou faire un clic droit sur CampusVisitorApplication.java dans IntelliJ et choisir Run. L'application sera accessible à l'adresse http://localhost:8080.

Comptes par défaut

Deux comptes sont créés automatiquement au premier démarrage. Le compte Admin avec l'email admin@campus.ma et le mot de passe admin123 a accès à toutes les fonctionnalités. Le compte Agent avec l'email agent@campus.ma et le mot de passe agent123 peut gérer les visiteurs.

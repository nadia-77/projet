package com.campus.visitor.config;

import com.campus.visitor.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurService utilisateurService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(utilisateurService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth


                .requestMatchers(
                    "/visiteur/inscription",
                    "/visiteur/confirmation/**",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()


                .anyRequest().authenticated()
            )


            .formLogin(form -> form
                .loginPage("/login")                 // notre page de connexion personnalisée
                .loginProcessingUrl("/login")        // URL qui traite le formulaire
                .defaultSuccessUrl("/dashboard", true) // où aller après connexion
                .failureUrl("/login?erreur=true")    // où aller si mauvais mot de passe
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/deconnexion")
                .logoutSuccessUrl("/login?deconnecte=true")
                .permitAll()
            );

        return http.build();
    }
}

package oit.is.z3417.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken", "/match", "/fight").authenticated()
            .anyRequest().permitAll());
    http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User.withUsername("ほんだ")
        .password("{bcrypt}$2b$10$3iZjS9gnA3VE2AGigbzereg.qYBdJ0iPHDQlWTfOShBhGdOO3xPte")
        .roles("USER").build();
    UserDetails user2 = User.withUsername("いがき")
        .password("{bcrypt}$2b$10$rJD8BZ2Ft/ZlGAeWiqRM6uSY0j1/NSHR8KqyIdc3p7UNNeVlMJVO2")
        .roles("USER").build();

    return new InMemoryUserDetailsManager(user1, user2);
  }
}

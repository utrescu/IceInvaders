package net.xaviersala.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuració de Spring Security però en comptes de fer-ho
 * a l'estil antic (via XML) amb una classe de tipus @Configuration
 * 
 * La idea és definir COM es fa per autenticar els usuaris de la 
 * web i també decidir on poden entrar i on no els usuaris no identificats.
 * 
 * @author xavier
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserMongoAuthenticatorProvider mongoAuthenticationProvider;
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  /**
   * Definim quin és l'accés que cal per les determinades url.
   * 
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {    
    http
    .authorizeRequests()
        .antMatchers("/", 
            "/home", 
            "/where", 
            "/recomana", 
            "/products", 
            "/product/**", 
            "/basket", 
            "/about", 
            "/empty", 
            "/alta",
            "/webjars/**").permitAll()
        .antMatchers("/add_usuaris").hasAuthority("ROLE_ADMIN")
        .anyRequest().authenticated()
        .and()
    .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
    .logout()
        .permitAll();
  }
  
  /**
   * Definim quines són les adreces que no necessiten seguretat.
   * 
   * No és necessàri accés segur per les adreces de recursos bàsics: 
   * CSS, Javascript, Imatges, tipus de lletres.
   */
  @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/js/**","/css/**","/images/**","/fonts/**");
    }
  
  
  /**
   * Configuració de l'autenticació dels usuaris. 
   * 
   * Ho he provat de dues formes: 
   * 
   * 1. Afegint els usuaris amb l'autenticador
   * de memòria
   *    
   *    auth.inMemoryAuthentication().withUser("usuari").password("contrasenya").roles("USER");
   *    auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
   * 
   * 2. Amb un validador personalitzat, MongoAuthenticationProvider, que identifica els usuaris
   * a la base de dades. Aquest és el que he deixat
   * 
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
    auth.authenticationProvider(mongoAuthenticationProvider);
  }

}

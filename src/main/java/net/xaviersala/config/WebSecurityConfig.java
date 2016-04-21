package net.xaviersala.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {    
    http
    .authorizeRequests()
        .antMatchers("/", "/home", "/where", "/recomana", "/products", "/product/**", "/basket", "/about", "/empty").permitAll()
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
   * Seguretat web no necessària per les adreces de recursos bàsics: 
   * CSS, Javascript, Imatges, tipus de lletres.
   */
  @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/js/**","/css/**","/images/**","/fonts/**");
    }
  
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("usuari").password("contrasenya").roles("USER");
  }

}

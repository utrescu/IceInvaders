package net.xaviersala.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
public class MVCConfig extends WebMvcConfigurerAdapter {
  
}

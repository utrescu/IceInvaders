/**
 * 
 */
package net.xaviersala.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import net.xaviersala.model.Cistella;
import net.xaviersala.model.Producte;
import net.xaviersala.model.Venda;
import net.xaviersala.repositories.CistellaRepository;
import net.xaviersala.repositories.ProducteRepository;


/**
 * @author xavier
 *
 */
@Controller
@SessionAttributes({"cistella"})
public class IceController  {
  
  private static final Log log = LogFactory.getLog(IceController.class);
  
  ProducteRepository mongo;
  CistellaRepository compres;
  
  /**
   * Creació del control·lador passant-hi com a paràmetre la base de dades.
   * @param mongo
   */
  @Autowired
  public IceController(ProducteRepository mongo, CistellaRepository compres) {
    this.mongo = mongo;
    this.compres = compres;
  }
  
  
  @ModelAttribute("cistella")
  public Cistella getPerson(){
      return new Cistella();
  }
  
  /**
   * Mostra la pàgina principal.
   * 
   * @param model model per la vista
   * @return pàgina a mostrar
   */
  @RequestMapping("/")
  public String arrel() {

    // Podria localitzar els més usats i posar-los al model
    log.info(".. Pàgina principal: ");    
    return "home";
  }

  /**
   * Informació sobre l'empresa.
   * 
   * @return pàgina a mostrar
   */
  @RequestMapping("/about")
  public String about() {
    log.info(".. Qui sóm");
    return "about";
  }

  /**
   * Recomanacions d'ús.
   * 
   * @return pàgina a mostrar.
   */
  @RequestMapping("/recomana")
  public String recomana() {
    log.info(".. Recomanacions");
    return "recomana";
  }
  
  /**
   * Llista en quins llocs es poden comprar productes.
   * 
   * @return pàgina a mostrar
   */
  @RequestMapping("/where")
  public String onComprar() {
    log.info(".. On comprar");
    return "where";
  }
  
  /**
   * Llistar tots els productes que compleixin una determinada condició (o cap).
   * 
   * @param keyword paraula a cercar
   * @param page pàgina a mostrar
   * @param model model per la vista
   * @return pàgina a carregar
   */
  @RequestMapping("/products")
  public String llistaProductes(@RequestParam (required = false) String keyword, @RequestParam(required = false) Integer page, Model model) {
    List<Producte> productes;
    Page<Producte> paginaDeProductes;

    int numeroDePagina = 0;
    
    if (page != null) {
       numeroDePagina =  page;        
    }
    
    log.info(".. Productes " + keyword + "(" + numeroDePagina + ")");
    if (keyword==null) {
      
      paginaDeProductes = mongo.findAll(new PageRequest(numeroDePagina, 6));
      
    } else {
      paginaDeProductes = mongo.findByNom(keyword, new PageRequest(numeroDePagina, 6));
    }
    productes = paginaDeProductes.getContent();
    log.debug("........ " + productes);
    
    model.addAttribute("pagina",numeroDePagina);
    model.addAttribute("productes", productes);    
    return "products";
  }
  
  /**
   * Cerca d'un producte a partir del seu nom.
   * 
   * @param nom nom del producte
   * @param model model per la vista
   * @return pàgina a carregar
   */
  @RequestMapping(value="/product/{nom}")
  public String mostraProducte(@PathVariable("nom") String nom, Model model) {
    
    log.info(".. Demana per " + nom);
    Producte producte = mongo.findByNom(nom);
    if (producte == null) {
      return "error";
    }
    model.addAttribute("producte", producte);
    
    return "product";    
  }

  /**
   * Mostra la cistella de la compra.
   * 
   * @return pàgina amb la cistella
   */
  @RequestMapping("/basket")
  public String cistella() {
    log.info("... Mostrar Cistella");
    return "basket";
  }
  
  /**
   * Mostra la cistella de la compra.
   * 
   * @return pàgina amb la cistella
   */
  @RequestMapping(value="/basket", method=RequestMethod.POST)
  public String afegirCistella(@RequestParam("producte") String nomProducte, @ModelAttribute("cistella") Cistella cistella) {
    
    if (nomProducte != null) {
        Producte producte = mongo.findOne(nomProducte);
        if (producte != null) {
          log.info("... Afegir " + producte.getNom() + " a la cistella");
          cistella.afegirProducte(new Venda(producte,1));
        }
    }
        
    return "redirect:/basket";
  }
  
  /**
   * Passar a pagar.
   * 
   * @return pàgina a mostrar
   */
  @RequestMapping("/checkout")
  public String checkout(@ModelAttribute("cistella") Cistella cistella, SessionStatus status, Model model) {
    
    log.info("... Intenta pagar ....");
    
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    
    // Comprovo que n'hi ha algun
    if (!cistella.teVendes()) {
      return "redirect:/basket";
    } else {
        log.info("...... Paga!");
        cistella.setUsername(username);
        // Desar la cistella
        model.addAttribute("compra", cistella);
        compres.save(cistella);    
        status.setComplete();      
    }
    return "checkout";
  }
  
  /**
   * Elimina el contingut de la cistella.
   * 
   * @param cistella contingut de la cistella
   * @param status estat de la sessió
   * @return Torna a la pàgina principal
   */
  @RequestMapping("/empty")
  public String empty(SessionStatus status) {
    status.setComplete();
    return "redirect:/";
  }
  
  /**
   * Presenta la pantalla de login.
   * @param error  En cas d'error es torna a aquesta pàgina
   * @param request Aconsegueixo el token CSRF de la petició.
   * @return S'envien a /login en POST (i ja se n'encarregarà Spring Security
   *         de la identificació)
   */
  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String login(@RequestParam(value="error",required=false) String error,
      HttpServletRequest request) {

    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
      return "redirect:/";
    }    
    return "login";
  }
   


  
}

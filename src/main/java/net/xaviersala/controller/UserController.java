package net.xaviersala.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.xaviersala.model.Cistella;
import net.xaviersala.model.Usuari;
import net.xaviersala.repositories.CistellaRepository;
import net.xaviersala.repositories.UsuariDades;
import net.xaviersala.repositories.UsuariService;

@Controller
public class UserController {
  
  private static final Log log = LogFactory.getLog(UserController.class);
  
  
  UsuariService usuaris;
  CistellaRepository comandes;
  
  /**
   * Constructor del Controller.
   * 
   * La idea és que sigui comprovable i per això li passem
   * per paràmetres els repositoris. 
   * 
   * @param usuaris repositori d'usuaris
   * @param compres repositori de comandes
   */
  @Autowired
  public UserController(UsuariService usuaris, CistellaRepository compres) {
    this.usuaris = usuaris;
    this.comandes = compres;
  }
  
  /**
   * Mostrar el perfil de l'usuari.
   * @param model
   * @return
   */
  @RequestMapping("/usuari")
  public String veurePerfil(Model model) {
    
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    
    Usuari usuari = usuaris.buscaUsuari(username);
    if (usuari == null) {
      log.error("Usuari " + username + " no existent");
      model.addAttribute("error", "Usuari incorrecte");
      return "error";
    }
    
    log.info("Veure perfil: " + username);
    
    List<Cistella> cistelles = comandes.findByUsername(username);
    
    model.addAttribute("usuari", username);
    model.addAttribute("dades", usuari.getDades());
    model.addAttribute("comandes", cistelles);
    return "account";
  }
  
  /**
   * Desar les noves dades de l'usuari
   * @param dades dades a desar
   * @param bindingResult resultat de la validació
   * @return Enviem al perfil
   */
  @RequestMapping(value="/usuari", method=RequestMethod.POST)
  public String desarUsuari(@ModelAttribute @Valid UsuariDades dades, BindingResult bindingResult) {
    
    
    if (bindingResult.hasErrors()) {
      log.error("Errors en l'actualització del perfil");
      return "account";
    }

    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    log.info("Actualitzar perfil: " + username);
    usuaris.desaUsuari(username, dades); 
    return "redirect:/usuari";
  }
  
  
  /**
   * Mostrar una comanda
   * @param id id de la comanda
   * @param model model per Thymeleaf
   * @return Enviem a la pàgina amb la comanda o a error
   */
  @RequestMapping("/usuari/comanda")
  public String llistaComanda(@RequestParam("id") String id, Model model) {
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    Cistella comanda = comandes.findByIdAndUsername(id, username);
    if (comanda == null) {
      log.error("Comanda " + id + " no existent o no es de l'usuari " + username);
      model.addAttribute("error", "Comanda inexistent o no és de l'usuari");
      return "error";
    }
    log.info("Veure comanda: " + id + " usuari: " + username);
    model.addAttribute("cistella", comanda);
    return "comanda";
  }
  
  
  /**
   * Temporal per afegir l'usuari xavier.
   * @return
   */
  @RequestMapping("/add_usuaris")
  public String creaUsuaris() {
    usuaris.crearUsuari("xavier", "sala", new UsuariDades("Xavier", "Sala", "x@x.net", "Cabanes"));    
    return "redirect:/";
  }


}

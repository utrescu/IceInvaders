package net.xaviersala.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.xaviersala.model.Usuari;
import net.xaviersala.repositories.UsuariDades;
import net.xaviersala.repositories.UsuariService;

@Controller
@SessionAttributes({"cistella"})
public class UserController {
  
  @Autowired
  UsuariService usuaris;
  
  @RequestMapping("/usuari")
  public String veurePerfil(Model model) {
    
    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    
    Usuari usuari = usuaris.buscaUsuari(username);
    if (usuari == null) {
      return "/error";
    }
    
    model.addAttribute("usuari", username);
    model.addAttribute("dades", usuari.getDades());
    return "account";
  }
  
  @RequestMapping(value="/usuari", method=RequestMethod.POST)
  public String desarUsuari(@ModelAttribute @Valid UsuariDades dades, BindingResult bindingResult) {
    
    if (bindingResult.hasErrors()) {
      return "account";
    }

    String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();    
    usuaris.desaUsuari(username, dades); 
    return "redirect:/usuari";
  }
  
  @RequestMapping("/add_usuaris")
  public String creaUsuaris() {
    usuaris.crearUsuari("xavier", "sala", new UsuariDades("Xavier", "Sala", "x@x.net", "Cabanes"));    
    return "redirect:/";
  }


}

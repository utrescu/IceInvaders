package net.xaviersala.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.xaviersala.repositories.UsuariDades;

@Component
public class UsuariValidator implements Validator {
  
  @Override
  public boolean supports(Class<?> classe) {    
    return UsuariDades.class.equals(classe);
  }

  @Override
  public void validate(Object objecte, Errors errors) {

    UsuariDades usuari = (UsuariDades) objecte;
    
    String password = usuari.getContrasenya(); 
    String confPassword = usuari.getRepeatContrasenya(); 
        
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "L'usuari no pot estar en blanc");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasenya", "", "La contrasenya no pot estar en blanc");
            
    // Comprova les contrasenyes
    if(!password.equals(confPassword)){
        errors.rejectValue("contrasenya","", "Les contrasenyes no són iguals");
    }
    
    
  }

}

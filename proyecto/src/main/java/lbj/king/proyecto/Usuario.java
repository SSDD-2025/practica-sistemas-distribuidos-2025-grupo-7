package lbj.king.proyecto;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Usuario {
    private String DNI;
    private String nombreUsuario;
    private String contraseña;

    public Usuario(String dni,String n,String c){
        DNI=dni;
        nombreUsuario=n;
        contraseña=c;
    }

    public void setdni(String dni){
        DNI=dni;
    }
    public void setnombre(String n){
        nombreUsuario=n;
    }
    public void setcontraseña(String c){
        contraseña=c;
    }

}

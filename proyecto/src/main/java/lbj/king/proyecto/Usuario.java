package lbj.king.proyecto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;
import org.springframework.web.context.annotation.SessionScope;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@SessionScope
@Entity
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private String DNI;
    private String name;
    private String password;
    private float currency;


    public Usuario(){
        
    }

    public Usuario(String dni,String name,String psw){
        DNI=dni;
        this.name=name;
        this.password=psw;
    }

    public void setdni(String dni){
        DNI=dni;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String psw){
        this.password=psw;
    }
    public void setCurrency(float cv){
        this.currency=cv;
    }

    public String getdni(){
        return this.DNI;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public float getCurrency(){
        return this.currency;
    }

}

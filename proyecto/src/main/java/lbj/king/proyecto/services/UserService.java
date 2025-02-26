package lbj.king.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRep;
    @PostConstruct
    public void init(){
        Usuario u1 = new Usuario("a", "a");
        userRep.save(u1);
        Usuario u2 = new Usuario("EjemploDos", "espa1bila");
        userRep.save(u2);
    }

    public List<Usuario> getUsuarios(){
        List<Usuario> l = userRep.findAll();
        return l;
    }

    public void save(Usuario u){
        userRep.save(u);
    }

    public Optional<Usuario> findByName(long n){
        return userRep.findById(n);
    }
    public Optional<Usuario> findById(long n){
        return userRep.findById(n);
    }

}

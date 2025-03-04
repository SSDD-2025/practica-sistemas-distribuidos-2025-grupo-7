package lbj.king.proyecto.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.repositories.PlayRepository;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRep;
    @Autowired
    private PlayRepository pRep;
    @PostConstruct
    public void init(){
        Userr u1 = new Userr("a", "a");
        userRep.save(u1);
        Userr u2 = new Userr("EjemploDos", "espa1bila");
        userRep.save(u2);
    }

    public List<Userr> getUsuarios(){
        List<Userr> l = userRep.findAll();
        return l;
    }

    public void save(Userr u){
        userRep.save(u);
    }

    public Optional<Userr> findByName(long n){
        return userRep.findById(n);
    }
    public Optional<Userr> findById(long n){
        return userRep.findById(n);
    }

    public void save(Userr u, MultipartFile imag) throws IOException{
		if(!imag.isEmpty()) {
			u.setImage(BlobProxy.generateProxy(imag.getInputStream(), imag.getSize()));
		}
		this.save(u);
	}
    public List<Userr> findAll(){
        return userRep.findAll();
    }


    @Transactional
    public void deleteUserById(Long userId) {
        pRep.deleteByUserId(userId);
        userRep.deleteById(userId);
    }
}

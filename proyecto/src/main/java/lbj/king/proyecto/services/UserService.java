package lbj.king.proyecto.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.DTO.UserrMapper;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.repositories.PlayRepository;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRep;
    @Autowired
    private PlayRepository pRep;

    //para api rest
    @Autowired
    private UserrMapper mapper;

    public List<Userr> getUsuarios(){
        List<Userr> l = userRep.findAll();
        return l;
    }

    public void save(Userr u){
        userRep.save(u);
    }

    public Optional<Userr> findByName(String n){
        return userRep.findByName(n);
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

    //para api rest
    private UserrDTO toDTO(Userr user) {
		return mapper.toDTO(user);
	}

	private Userr toDomain(UserrDTO userrDTO) {
		return mapper.toDomain(userrDTO);
	}

	private Collection<UserrDTO> toDTOs(Collection<Userr> users) {
		return mapper.toDTOs(users);
	}


    public Collection<UserrDTO> getUsers(){
        return toDTOs(userRep.findAll());
    }
    public UserrDTO getUser(long id) {
        return toDTO(userRep.findById(id).orElseThrow());
    }
    public /*ResponseEntity<*/UserrDTO/*>*/ createUser(UserrDTO userrDTO) {       
        if(userrDTO.id() != null){
            throw new IllegalArgumentException();
        } 
        Userr userr = toDomain(userrDTO); 
        userRep.save(userr);
        return toDTO(userr);
    }
    
    //FALTA MODIFICAR USER
    //quizas falte para cambiar un user pero para eso q lo borren supongo

    public UserrDTO deleteUserr(long id){
        Userr userr = userRep.findById(id).orElseThrow();
        userRep.deleteById(id);
        return toDTO(userr);
    }

    public void createImageUserr(long id, URI location, InputStream inputStream, long size) {
        Userr userr = userRep.findById(id).orElseThrow();
        userr.setImageUrl(location.toString());
        userr.setImage(BlobProxy.generateProxy(inputStream, size));

        userRep.save(userr);
    }
    public Resource getImageUserr(long id) throws SQLException{
        Userr userr = userRep.findById(id).orElseThrow();
        if (userr.getImage() != null) {
            return (Resource) new InputStreamResource(userr.getImage().getBinaryStream());
        } else {
            throw new NoSuchElementException();
        }
    }
    public void replaceImageUserr(long id, InputStream inputStream, long size){
        Userr userr = userRep.findById(id).orElseThrow();
        if(userr.getImage() == null){
            throw new NoSuchElementException();
        }
        userr.setImage(BlobProxy.generateProxy(inputStream, size));
        userRep.save(userr);
    }
    public void deleteImageUserr(long id){
        Userr userr = userRep.findById(id).orElseThrow();
        if(userr.getImage() == null){
            throw new NoSuchElementException();
        }
        userr.setImage(null);
        userr.setImage(null);
        userRep.save(userr);

    }
}

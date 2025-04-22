package lbj.king.proyecto.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.DTO.UserrBasicDTO;
import lbj.king.proyecto.DTO.UserrBasicMapper;
import lbj.king.proyecto.DTO.UserrCompleteDTO;
import lbj.king.proyecto.DTO.UserrCompleteDTOMapper;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.DTO.UserrMapper;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.repositories.GameRepository;
import lbj.king.proyecto.repositories.PlayRepository;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRep;
    @Autowired
    private PlayRepository pRep;
    @Autowired
    private GameRepository gameRep;

    //para api rest
    @Autowired
    private UserrMapper mapper;
    @Autowired
    private UserrBasicMapper basicMapper;
    @Autowired
    private UserrCompleteDTOMapper completeMapper;

    // public List<Userr> getUsuarios(){
    //     List<Userr> l = userRep.findAll();
    //     return l;
    // }

    // public void save(Userr u){
    //     userRep.save(u);
    // }

    // public Optional<Userr> findByName(String n){
    //     return userRep.findByName(n);
    // }
    // public Optional<Userr> findById(long n){
    //     return userRep.findById(n);
    // }

    // public void save(Userr u, MultipartFile imag) throws IOException{
	// 	if(!imag.isEmpty()) {
	// 		u.setImage(BlobProxy.generateProxy(imag.getInputStream(), imag.getSize()));
	// 	}
	// 	this.save(u);
	// }
    // public List<Userr> findAll(){
    //     return userRep.findAll();
    // }


    // @Transactional
    // public void deleteUserById(Long userId) {
    //     pRep.deleteByUserId(userId);
    //     userRep.deleteById(userId);
    // }

    public Collection<UserrDTO> getUsers(){
        return toDTOs(userRep.findAll());
    }

    public UserrDTO save(UserrDTO userDTO) {
        Userr user = toDomain(userDTO);
        userRep.save(user);
        return toDTO(user);
    }

    public Optional<UserrDTO> findByName(String name) {
        return userRep.findByName(name)
                .map(this::toDTO);
    }
    public Optional<UserrBasicDTO> findByNameBasic(String name) {
        return userRep.findByName(name).map(this::toBasicDTO);
    }
    public Optional<UserrCompleteDTO> findByNameComplete(String name) {
        return userRep.findByName(name).map(this::toCompleteDTO);
    }

    public Optional<UserrDTO> findById(long id) {
        return userRep.findById(id)
                .map(this::toDTO);
    }

    public UserrDTO save(UserrDTO userDTO, MultipartFile image) throws IOException {
        Userr user = toDomain(userDTO);
        if(!image.isEmpty()) {
            user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        userRep.save(user);
        return toDTO(user);
    }

    public List<UserrDTO> findAll() {
        return userRep.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void updateCurrencyUser(Long id, float money){
        Userr u = userRep.findById(id).orElseThrow();
        u.setCurrency(u.getCurrency() + money);
        userRep.save(u);
    }
    
    public void updateLessCurrencyUser(Long id, float money){
        Userr u = userRep.findById(id).orElseThrow();
        u.setCurrency(u.getCurrency() - money);
        userRep.save(u);
    }

    public void setImageUser(Long id, Blob imag){
        Userr u = userRep.findById(id).orElseThrow();
        u.setImage(imag);
        u.setImageBool(true);
        userRep.save(u);
    }

    public void addPlayToId(Long playId, Long userId){
        Userr user = userRep.findById(userId).orElseThrow();
        Play play = pRep.findById(playId).orElseThrow();
        user.addPlay(play);
        userRep.save(user);
    }

    public void userWinPlay(Long userId, Long playId, Long gameId ){
        Userr user = userRep.findById(userId).orElseThrow();
        Play play = pRep.findById(playId).orElseThrow();
        Game game = gameRep.findById(gameId).orElseThrow();
        user.setCurrency(user.getCurrency()+play.getBet()*game.getWinMultp());
        userRep.save(user);
    }
    
    
    @Transactional
    public UserrDTO deleteUserById(Long userId) {
        Userr user = userRep.findById(userId).orElseThrow();
        pRep.deleteByUserId(userId);
        userRep.deleteById(userId);
        return toDTO(user);
    }

    //para api rest
    private UserrDTO toDTO(Userr user) {
		return mapper.toDTO(user);
	}
    private UserrBasicDTO toBasicDTO(Userr user) {
		return basicMapper.toDTO(user);
	}
    private UserrCompleteDTO toCompleteDTO(Userr user) {
		return completeMapper.toDTO(user);
	}

	private Userr toDomain(UserrDTO userrDTO) {
		return mapper.toDomain(userrDTO);
	}

	private Collection<UserrDTO> toDTOs(Collection<Userr> users) {
		return mapper.toDTOs(users);
	}


    // public Collection<UserrDTO> getUsers(){
    //     return toDTOs(userRep.findAll());
    // }

	public Page<UserrDTO> getUsersPageable(Pageable pageable) {
		return userRep.findAll(pageable).map(this::toDTO);
	}

    public UserrDTO getUser(long id) {
        return toDTO(userRep.findById(id).orElseThrow());
    }

    // public Userr getLoggedUser() {
    //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
    //     return userRep.findByName(username).get();
    // }

    public UserrDTO getLoggedUserDTO() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return toDTO(userRep.findByName(username).get());
        // return mapper.toDTO(getLoggedUser());
    }

    public UserrDTO createUser(UserrDTO userrDTO) {       
        if(userrDTO.id() != null){
            throw new IllegalArgumentException();
        } 
        Userr userr = toDomain(userrDTO); 
        userRep.save(userr);
        return toDTO(userr);
    }
    
    public UserrDTO replaceUser(long id, UserrDTO updatedUserrDTO) throws SQLException{
		Userr updatedUserr = toDomain(updatedUserrDTO);
		updatedUserr.setId(id);

		userRep.save(updatedUserr);

		return toDTO(updatedUserr);
    }

    public UserrDTO deleteUserr(long id){
        Userr userr = userRep.findById(id).orElseThrow();
        userRep.deleteById(id);
        return toDTO(userr);
    }

    public void createImageUserr(long id, InputStream inputStream, long size) {
        Userr userr = userRep.findById(id).orElseThrow();
        userr.setImage(BlobProxy.generateProxy(inputStream, size));

        userRep.save(userr);
    }
    public Resource getImageUserr(long id) throws SQLException{
        Userr userr = userRep.findById(id).orElseThrow();
        if (userr.getImage() != null) {
            return new InputStreamResource(userr.getImage().getBinaryStream());
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


    public Optional<Userr>  getUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof UserDetails) {

            username = ((UserDetails)principal).getUsername();

        } else {

            username = principal.toString();

        }

        return userRep.findByName(username);

    }
}

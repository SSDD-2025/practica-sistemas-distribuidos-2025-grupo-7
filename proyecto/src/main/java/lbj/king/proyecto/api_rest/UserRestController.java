package lbj.king.proyecto.api_rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.DTO.UserrMapper;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService uSer;
    @Autowired
    private UserrMapper mapper;
    @Autowired
    private PrizeService prizeSer;



    @GetMapping("/me")
	public UserrDTO me() {
		return uSer.getLoggedUserDTO();
	}

    @PostMapping("/me/addCurrency")
    public UserrDTO addCurrency(@RequestParam int currency) throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr userr = uSer.findByName(username).orElseThrow();
        userr.setCurrency(userr.getCurrency() + currency);
        uSer.save(userr);
        return uSer.getLoggedUserDTO();
    }

    @DeleteMapping("/me")
    public UserrDTO deletUserrMe(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Userr user = uSer.findByName(username).orElseThrow();
       

        if (user.getPrizeList() != null){
            for (Prize p : user.getPrizeList()) {
                    prizeSer.changePrize(p);

                }
        }
        uSer.deleteUserById(user.getId());
        return mapper.toDTO(user);
    }


    @GetMapping("/")
    public Page<UserrDTO> getUsers(Pageable pageable) {
        return uSer.getUsersPageable(pageable);
    }

    @GetMapping("/{id}")
    public UserrDTO getUser(@PathVariable long id) {
        return uSer.getUser(id);
    }

    @PostMapping("/")
    public ResponseEntity<UserrDTO> createUser(@RequestBody UserrDTO userrDTO) {        
        userrDTO = uSer.createUser(userrDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(userrDTO.id()).toUri();
        return ResponseEntity.created(location).body(userrDTO);
    }
    
    @PutMapping("/{id}")
	public UserrDTO replaceUser(@PathVariable long id, @RequestBody UserrDTO updatedUserrDTO) throws SQLException {
		return uSer.replaceUser(id, updatedUserrDTO);
	}

    @DeleteMapping("/{id}")
    public UserrDTO deletUserr(@PathVariable Long id){
        Userr userr = uSer.findById(id).orElseThrow();

        if (userr.getPrizeList() != null){
            for (Prize p : userr.getPrizeList()) {
                    prizeSer.changePrize(p);

                }
        }
        uSer.deleteUserById(id);
        return mapper.toDTO(userr);
    }

    @PostMapping("/me/image")
    public ResponseEntity<Object> createImageUserr(@RequestParam MultipartFile imageFile) throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr userr = uSer.findByName(username).orElseThrow();
        long id = userr.getId();
        uSer.createImageUserr(id, imageFile.getInputStream(), imageFile.getSize());
        URI location = fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/me/image")
    public ResponseEntity<Object> getImageUserr() throws SQLException, IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr userr = uSer.findByName(username).orElseThrow();
        long id = userr.getId();
        Resource image =  uSer.getImageUserr(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            .body(image);
    }
    
    @PutMapping("/me/image")
    public ResponseEntity<Object> replaceImageUserr(@RequestParam MultipartFile image) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr userr = uSer.findByName(username).orElseThrow();
        long id = userr.getId();
        uSer.replaceImageUserr(id, image.getInputStream(), image.getSize());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me/image")
    public ResponseEntity<Object> deleteImageUserr() throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr userr = uSer.findByName(username).orElseThrow();
        long id = userr.getId();
        uSer.deleteImageUserr(id);
        return ResponseEntity.noContent().build();
    }
}
    
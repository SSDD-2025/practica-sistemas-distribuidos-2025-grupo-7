package lbj.king.proyecto.api_rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
    private PrizeService prizeSer;



    @GetMapping("/me")
	public UserrDTO me() {
		return uSer.getLoggedUserDTO();
	}

    @PostMapping("/me/addCurrency")
    public UserrDTO addCurrency(@RequestParam int currency) throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO userr = uSer.findByName(username).orElseThrow();

        uSer.updateCurrencyUser(userr.id(), currency);
        
        return uSer.getLoggedUserDTO();
    }

    @DeleteMapping("/me")
    public UserrDTO deletUserrMe(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        UserrDTO user = uSer.findByName(username).orElseThrow();
       

        if (user.prizeList() != null){
            for (PrizeDTO p : user.prizeList()) {
                    prizeSer.changePrize(p);

                }
        }
        uSer.deleteUserById(user.id());
        return user;
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
        UserrDTO userr = uSer.findById(id).orElseThrow();

        if (userr.prizeList() != null){
            for (PrizeDTO p : userr.prizeList()) {
                    prizeSer.changePrize(p);
                }
        }
        uSer.deleteUserById(id);
        return userr;
    }

    @PostMapping("/me/image")
    public ResponseEntity<Object> createImageUserr(@RequestParam MultipartFile imageFile) throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO userr = uSer.findByName(username).orElseThrow();
        long id = userr.id();
        uSer.createImageUserr(id, imageFile.getInputStream(), imageFile.getSize());
        URI location = fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/me/image")
    public ResponseEntity<Object> getImageUserr() throws SQLException, IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO userr = uSer.findByName(username).orElseThrow();
        long id = userr.id();
        Resource image =  uSer.getImageUserr(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            .body(image);
    }
    
    @PutMapping("/me/image")
    public ResponseEntity<Object> replaceImageUserr(@RequestParam MultipartFile image) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO userr = uSer.findByName(username).orElseThrow();
        long id = userr.id();
        uSer.replaceImageUserr(id, image.getInputStream(), image.getSize());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me/image")
    public ResponseEntity<Object> deleteImageUserr() throws IOException{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO userr = uSer.findByName(username).orElseThrow();
        long id = userr.id();
        uSer.deleteImageUserr(id);
        return ResponseEntity.noContent().build();
    }
}
    
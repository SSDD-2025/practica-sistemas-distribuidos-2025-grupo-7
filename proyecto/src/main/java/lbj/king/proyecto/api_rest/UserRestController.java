package lbj.king.proyecto.api_rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.DTO.UserrMapper;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

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

    @GetMapping("/")
    public Collection<UserrDTO> getUsers() {
        return uSer.getUsers();
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
	public UserrDTO replaceUser(@PathVariable long id, @PathVariable UserrDTO updatedUserrDTO) throws SQLException {

		return uSer.replaceUser(id, updatedUserrDTO);
	}

    @DeleteMapping("/{id}")
    public UserrDTO deletUserr(@PathVariable Long id){
        Userr userr = uSer.findById(id).orElseThrow();
        uSer.deleteUserById(id);
        return mapper.toDTO(userr);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> createImageUserr(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException{
        URI location = fromCurrentRequest().build().toUri();
        uSer.createImageUserr(id, location, imageFile.getInputStream(), imageFile.getSize());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImageUserr(@PathVariable long id) throws SQLException, IOException {
        Resource image =  uSer.getImageUserr(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            .body(image);
    }
    
    @PutMapping("/{id}/image")
    public ResponseEntity<Object> replaceImageUserr(@PathVariable long id, @RequestParam MultipartFile image) throws IOException {
        uSer.replaceImageUserr(id, image.getInputStream(), image.getSize());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteImageUserr(@PathVariable long id) throws IOException{
        uSer.deleteUserr(id);
        return ResponseEntity.noContent().build();
    }
}
    
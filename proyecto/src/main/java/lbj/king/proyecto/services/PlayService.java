package lbj.king.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.repositories.PlayRepository;

@Service
public class PlayService {

    @Autowired
    private PlayRepository playRep;

    public List<Play> getUsuarios(){
        List<Play> l = playRep.findAll();
        return l;
    }

    public void save(Play p){
        playRep.save(p);
    }

    public Optional<Play> findByName(long n){
        return playRep.findById(n);
    }

    @Transactional
    public void deletePartidasByUsuarioId(Long usuarioId) {
        playRep.deleteByUserId(usuarioId);
    }

    @Transactional
    public void deletePartidaById(Long id){
        playRep.deleteById(id);
    }
}

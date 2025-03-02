package lbj.king.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Partida;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.repositories.PlayRepository;

@Service
public class PlayService {

    @Autowired
    private PlayRepository playRep;

    public List<Partida> getUsuarios(){
        List<Partida> l = playRep.findAll();
        return l;
    }

    public void save(Partida p){
        playRep.save(p);
    }

    public Optional<Partida> findByName(long n){
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

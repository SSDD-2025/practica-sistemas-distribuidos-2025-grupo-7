package lbj.king.proyecto.services;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.PlayMapper;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.repositories.PlayRepository;

@Service
public class PlayService {


    @Autowired
    private PlayRepository playRep;
    @Autowired
    private PlayMapper mapper;

    public PlayDTO save(PlayDTO playDTO) {
        Play play = toDomain(playDTO);
        playRep.save(play);
        return toDTO(play);
    }

    public Optional<PlayDTO> findById(long id) {
        return playRep.findById(id)
                .map(this::toDTO);
    }

    @Transactional
    public void deletePartidasByUsuarioId(Long usuarioId) {
        playRep.deleteByUserId(usuarioId);
    }

    @Transactional
    public void deletePartidaById(Long id){
        playRep.deleteById(id);
    }

    public Collection<PlayDTO> findByUserId(Long id){
        return toDTOs(playRep.findByUserId(id));
    }

    public void setWinDTO(Long id, float mult, float bet){
        Play play = playRep.findById(id).orElseThrow();
        play.setWin(bet * mult);
        playRep.save(play);
    }

    public void setWonDTO(Long id){
        Play play = playRep.findById(id).orElseThrow();
        play.won();
        playRep.save(play);
    }


    //para api rest
    private PlayDTO toDTO(Play play) {
        return mapper.toDTO(play);
    }

    private Play toDomain(PlayDTO playDTO) {
        return mapper.toDomain(playDTO);
    }

    private Collection<PlayDTO> toDTOs(Collection<Play> plays) {
        return mapper.toDTOs(plays);
    }

    public Collection<PlayDTO> getPlays() {
        return toDTOs(playRep.findAll());
    }

    public Page<PlayDTO> getPlaysByUser(long userId, Pageable pageable) {
        return playRep.findPageByUserId(userId, pageable).map(this::toDTO);
    }

    public Page<PlayDTO> getPlaysPageable(Pageable pageable){
        return playRep.findAll(pageable).map(this::toDTO);
    }

    public PlayDTO getPlay(long id) {
        return toDTO(playRep.findById(id).orElseThrow()); 
    }

    public PlayDTO createPlay(PlayDTO playDTO) {
        if(playDTO.id() != null){
            throw new IllegalArgumentException();
        } 
        Play play = toDomain(playDTO);
        playRep.save(play);
        return toDTO(play);
    }

    public PlayDTO replacePlay(long id, PlayDTO updatedPlayDTO) throws SQLException {
        Play updatedPlay = toDomain(updatedPlayDTO);
        updatedPlay.setId(id);

        playRep.save(updatedPlay);

        return toDTO(updatedPlay);
    }

    public PlayDTO deletePlay(long id) {
        Play play = playRep.findById(id).orElseThrow();

        PlayDTO playDTO = toDTO(play);

        playRep.deleteById(id);

        return playDTO;
    }
}

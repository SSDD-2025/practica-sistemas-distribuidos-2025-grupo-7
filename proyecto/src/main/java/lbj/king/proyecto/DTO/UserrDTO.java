package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.Collections;
import java.util.List;

public record UserrDTO(
        Long id,
        String name,   
        float currency,
        boolean imageBool,
        List<String> roles,
        List<PrizeDTO> prizeList,
        List<PlayDTO> playList,
        Blob image

        ) {
                // public UserrDTO {
                //         prizeList = prizeList != null ? prizeList : Collections.emptyList();
                //         playList = playList != null ? playList : Collections.emptyList();
                //         roles = roles != null ? roles : Collections.emptyList();
                // }

                // public List<PlayDTO> getLista(){
                //         return playList;
                // }
                // public List<PlayDTO> getPlayList(){
                //         return playList;
                // }
}

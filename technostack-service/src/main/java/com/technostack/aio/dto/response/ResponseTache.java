package com.technostack.aio.dto.response;

import com.technostack.aio.model.Tache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseTache {
    private String tache_code;
    private String tache_name;
    private String tache_intitule;
    private String tache_sequence;

    public static ResponseTache mapToResponse(Tache tache) {

        return
                ResponseTache
                        .builder()
                        .tache_code(tache.getCode())
                        .tache_intitule(tache.getIntitule())
                        .tache_name(tache.getName())
                        .tache_sequence(tache.getSequence())
                        .build();
    }
}

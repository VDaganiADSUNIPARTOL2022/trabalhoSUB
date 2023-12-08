package br.unipar.trabalhosub.backend.service;

import java.util.List;

import br.unipar.trabalhosub.backend.dto.PaisDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IPaisService {

    @GET("paises")
    Call<List<PaisDTO>> getPaises();

}

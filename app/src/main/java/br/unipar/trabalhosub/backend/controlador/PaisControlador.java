package br.unipar.trabalhosub.backend.controlador;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.unipar.trabalhosub.backend.dao.PaisDAO;
import br.unipar.trabalhosub.backend.dto.PaisDTO;
import br.unipar.trabalhosub.backend.entidade.Pais;
import br.unipar.trabalhosub.backend.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisControlador {

    public Context context;

    public PaisControlador(Context context) {
        this.context = context;
    }

    public void getPais() {
        PaisDAO paisDAO = PaisDAO.getInstancia(context);
        try {
            Call<List<PaisDTO>> call = new RetrofitConfig().paisService().getPaises();
            call.enqueue(new Callback<List<PaisDTO>>() {
                @Override
                public void onResponse(Call<List<PaisDTO>> call, Response<List<PaisDTO>> response) {
                    List<PaisDTO> dto = response.body();
                    for (PaisDTO paisDTO : dto) {
                        Pais pais = new Pais(paisDTO.getCodigo(), paisDTO.getDescricao());
                        paisDAO.insert(pais);
                    }
                    Toast.makeText(context, dto.toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<PaisDTO>> call, Throwable t) {
                    Toast.makeText(context, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {

        }
    }
}

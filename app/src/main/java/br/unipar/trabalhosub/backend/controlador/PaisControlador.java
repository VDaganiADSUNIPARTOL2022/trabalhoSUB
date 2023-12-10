package br.unipar.trabalhosub.backend.controlador;

import android.content.Context;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

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
    public void buscarPaisesDaAPI() {
        PaisDAO paisDAO = PaisDAO.getInstancia(context);
        try {
            Toast.makeText(context, "Buscando...", Toast.LENGTH_LONG).show();
            Call<List<PaisDTO>> call = new RetrofitConfig().paisService().getPaises();
            call.enqueue(new Callback<List<PaisDTO>>() {
                @Override
                public void onResponse(Call<List<PaisDTO>> call, Response<List<PaisDTO>> response) {
                    for (PaisDTO dto : response.body()) {
                        if (paisDAO.buscarPorId(dto.getCodigo()) == null) {
                            paisDAO.salvar(new Pais(dto.getCodigo(), dto.getDescricao()));
                        }
                    }
                    Toast.makeText(context, "Busca Finalizada!", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<List<PaisDTO>> call, Throwable t) {
                    Toast.makeText(context, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
        }
    }
    public List<PaisDTO> buscarPaisesDoBancoDados() {
        PaisDAO paisDAO = PaisDAO.getInstancia(context);
        List<Pais> entidade = paisDAO.buscarTodos();
        return entidade.stream().map(x -> new PaisDTO(x.getCodigo(), x.getDescricao())).collect(Collectors.toList());
    }
    public void deletarTudo() {
        PaisDAO paisDAO = PaisDAO.getInstancia(context);
        paisDAO.deletarTudo();
    }
}

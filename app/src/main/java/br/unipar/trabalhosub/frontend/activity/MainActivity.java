package br.unipar.trabalhosub.frontend.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unipar.trabalhosub.R;
import br.unipar.trabalhosub.backend.controlador.PaisControlador;
import br.unipar.trabalhosub.backend.dto.PaisDTO;
import br.unipar.trabalhosub.frontend.adapter.PaisListaAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPaises;
    private Button btDeletarDados;
    private Button btAtualizarLista;
    private Button btBuscarDadosAPI;
    private List<PaisDTO> paises = new ArrayList<>();
    private PaisControlador paisControlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paisControlador = new PaisControlador(this);

        rvPaises = findViewById(R.id.rvPaises);
        btBuscarDadosAPI = findViewById(R.id.btBuscarDadosAPI);
        btDeletarDados = findViewById(R.id.btDeletarDados);
        btAtualizarLista = findViewById(R.id.btAtualizarLista);

        carregarListaPaises();

        btDeletarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarDadosDoBanco();
                carregarPaisListaAdapter();
            }
        });
        btBuscarDadosAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarPaisesDaAPI();
            }
        });
        btAtualizarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarListaPaises();
            }
        });
    }
    private void carregarListaPaises() {
        Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show();
        paises = paisControlador.buscarPaisesDoBancoDados();
        if (paises.isEmpty()) {
            Toast.makeText(this, "Sem Dados!", Toast.LENGTH_SHORT).show();
            return;
        }
        carregarPaisListaAdapter();
        Toast.makeText(this, "Lista Atualizada!", Toast.LENGTH_SHORT).show();
    }
    private void carregarPaisListaAdapter() {
        PaisListaAdapter adapter = new PaisListaAdapter(paises, this);
        rvPaises.setLayoutManager(new LinearLayoutManager(this));
        rvPaises.setAdapter(adapter);
    }
    private void buscarPaisesDaAPI() {
        paisControlador.buscarPaisesDaAPI();
    }
    private void deletarDadosDoBanco() {
        if (paises.isEmpty()) {
            Toast.makeText(this, "Nada para Deletar!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Deletando...", Toast.LENGTH_SHORT).show();
        paisControlador.deletarTudo();
        paises.clear();
        Toast.makeText(this, "Lista Deletada!", Toast.LENGTH_SHORT).show();
    }
}
package br.unipar.trabalhosub.frontend.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.unipar.trabalhosub.R;
import br.unipar.trabalhosub.backend.controlador.PaisControlador;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaisControlador paisControlador = new PaisControlador(this);
        paisControlador.getPais();
    }
}
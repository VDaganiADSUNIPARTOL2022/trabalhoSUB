package br.unipar.trabalhosub.frontend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.unipar.trabalhosub.R;
import br.unipar.trabalhosub.backend.dto.PaisDTO;

public class PaisListaAdapter extends RecyclerView.Adapter<PaisListaAdapter.ViewHolder> {
    private List<PaisDTO> paises;
    private Context context;
    public PaisListaAdapter(List<PaisDTO> paises, Context context) {
        this.paises = paises;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_lista_pais, parent, false);
        return new ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codigo.setText(String.valueOf(paises.get(position).getCodigo()));
        holder.descricao.setText(paises.get(position).getDescricao());
    }
    @Override
    public int getItemCount() {
        return paises.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView codigo;
        public TextView descricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codigo = itemView.findViewById(R.id.tvCodigo);
            this.descricao = itemView.findViewById(R.id.tvDescricao);
        }
    }
}

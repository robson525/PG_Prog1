package com.example.robmah.sigosapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robmah.sigosapp.R;
import com.example.robmah.sigosapp.modelos.Ocorrencia;

import java.util.ArrayList;

/**
 * Created by Robson on 25/06/2015.
 */
public class HistoricoAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Ocorrencia> listaOcorrencia;

    public HistoricoAdapter(Context context, ArrayList<Ocorrencia> listaOcorrencia){
        this.context = context;
        this.listaOcorrencia = listaOcorrencia;
    }

    @Override
    public int getCount() {
        return listaOcorrencia.size();
    }

    @Override
    public Object getItem(int position) {
        return listaOcorrencia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Ocorrencia ocorrencia = listaOcorrencia.get(position);

        LayoutInflater layoutInflater = (LayoutInflater)
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.listview_ocorrencias, null);

        TextView textTipo = (TextView)view.findViewById(R.id.textTipo);
        textTipo.setText(ocorrencia.getTipo().getNome());

        TextView textDescricao = (TextView)view.findViewById(R.id.textDescricao);
        String descricao = ocorrencia.getDescricao().trim();
        int quebraLinha = descricao.indexOf("\n");
        int maximo = quebraLinha >= 0 ? quebraLinha : descricao.length() < 20 ? descricao.length() : 20;
        textDescricao.setText(ocorrencia.getDescricao().substring(0, maximo) + " ..." );

        TextView textData = (TextView)view.findViewById(R.id.textData);
        textData.setText(ocorrencia.getEnvio().getDataFormatada());

        TextView textHora = (TextView)view.findViewById(R.id.textHora);
        textHora.setText(ocorrencia.getEnvio().getHoraFormatada());

        return view;
    }
}

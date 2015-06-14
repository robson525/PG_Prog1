package com.example.robmah.sigosapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.robmah.sigosapp.controle.OcorrenciaDAO;
import com.example.robmah.sigosapp.controle.UsuarioDAO;
import com.example.robmah.sigosapp.database.DataBase;
import com.example.robmah.sigosapp.modelos.Ocorrencia;
import com.example.robmah.sigosapp.modelos.Usuario;

import java.util.ArrayList;


public class historico extends ActionBarActivity {

    private DataBase database;
    private SQLiteDatabase db;

    private TextView tvNome;
    private TextView tvTelefone;
    private TextView tvfEmail;
    private ListView listOcorrencias;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        database = new DataBase(this);
        db = database.getWritableDatabase();

        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        usuario = usuarioDAO.getUsuario();

        tvNome = (TextView)findViewById(R.id.tvNome);
        tvTelefone = (TextView)findViewById(R.id.tvTelefone);
        tvfEmail = (TextView)findViewById(R.id.tvfEmail);
        listOcorrencias = (ListView)findViewById(R.id.listOcorrencias);


        tvNome.setText( usuario.getNome() );
        tvTelefone.setText( usuario.getTelefone() );
        tvfEmail.setText( usuario.getEmail() );

        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(db);
        ArrayList<Ocorrencia> ocorrencias =  ocorrenciaDAO.getListOcorrencias();
        ArrayAdapter<Ocorrencia> ocorrenciaAdapter =  new ArrayAdapter<Ocorrencia>(this, android.R.layout.simple_list_item_1, ocorrencias);
        listOcorrencias.setAdapter(ocorrenciaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

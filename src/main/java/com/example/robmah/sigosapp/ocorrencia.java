package com.example.robmah.sigosapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.robmah.sigosapp.controle.EnvioDAO;
import com.example.robmah.sigosapp.controle.OcorrenciaDAO;
import com.example.robmah.sigosapp.controle.SetorDAO;
import com.example.robmah.sigosapp.controle.TipoOcorrenciaDAO;
import com.example.robmah.sigosapp.controle.UnidadeDAO;
import com.example.robmah.sigosapp.controle.UsuarioDAO;
import com.example.robmah.sigosapp.database.DataBase;
import com.example.robmah.sigosapp.modelos.Envio;
import com.example.robmah.sigosapp.modelos.Ocorrencia;
import com.example.robmah.sigosapp.modelos.Setor;
import com.example.robmah.sigosapp.modelos.TipoOcorrencia;
import com.example.robmah.sigosapp.modelos.Unidade;
import com.example.robmah.sigosapp.modelos.Usuario;

import java.util.ArrayList;


public class ocorrencia extends ActionBarActivity {

    private DataBase database;
    private SQLiteDatabase db;

    Usuario usuario;

    private EditText edtDescricao;
    private Spinner spnTipo;
    private Spinner spnPapel;
    private Spinner spnUnidade;
    private Spinner spnSetor;
    private Button btEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia);

        database = new DataBase(this);
        db = database.getWritableDatabase();

        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        usuario = usuarioDAO.getUsuario();

        /* Carrega Componentes */
        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        spnTipo = (Spinner)findViewById(R.id.spnTipo);
        spnPapel = (Spinner)findViewById(R.id.spnPapel);
        spnUnidade = (Spinner)findViewById(R.id.spnUnidade);
        spnSetor = (Spinner)findViewById(R.id.spnSetor);
        btEnviar = (Button)findViewById(R.id.btEnviar);
    /******************************************************************/

        /* Spinner TipoOcorrencia*/
        TipoOcorrenciaDAO tipoOcorrenciaDAO = new TipoOcorrenciaDAO(db);
        ArrayList<TipoOcorrencia> TipoOcorrencia =  tipoOcorrenciaDAO.getListTipoOcorrencias();
        ArrayAdapter<TipoOcorrencia> tipoOcorrenciaAdapter = new ArrayAdapter<TipoOcorrencia>(this, android.R.layout.simple_list_item_1,TipoOcorrencia);
        spnTipo.setAdapter(tipoOcorrenciaAdapter);

        ArrayList<String> papeis =  new ArrayList<String>();
        papeis.add("Vítima");
        papeis.add("Testemunha");
        ArrayAdapter<String> papeisAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, papeis);
        spnPapel.setAdapter(papeisAdapter);


        /* Spinnere Unidades */
        UnidadeDAO unidadeDAO = new UnidadeDAO(this.db);
        ArrayList<Unidade> unidades =  unidadeDAO.getListUnidade();
        ArrayAdapter<Unidade> unidadeAdapter = new ArrayAdapter<Unidade>(this, android.R.layout.simple_list_item_1, unidades);
        spnUnidade.setAdapter(unidadeAdapter);

        //Quando Selecionar um Item da Unidade
        spnUnidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Unidade unidade = (Unidade) parent.getItemAtPosition(position);

                SetorDAO setorDAO = new SetorDAO(ocorrencia.this.db);
                ArrayList<Setor> setores = setorDAO.getListSetorByUnidade(unidade.getId());
                ArrayAdapter<Setor> dataAdapter = new ArrayAdapter<Setor>(ocorrencia.this, android.R.layout.simple_list_item_1, setores);
                spnSetor.setAdapter(dataAdapter);

                ocorrencia.this.CarregaSetorDoUsuario();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocorrencia.this.EnviarOcorrencia();
            }
        });


    }


    private void CarregaSetorDoUsuario(){

        for(int i=0; i < spnUnidade.getAdapter().getCount(); i++){
            if( ((Unidade) spnUnidade.getItemAtPosition(i)).getId() == usuario.getSetor().getUnidade().getId() ){
                spnUnidade.setSelection( i );
            }
        }

        for(int i=0; i < spnSetor.getAdapter().getCount(); i++){
            if( ((Setor)spnSetor.getItemAtPosition(i)).getId() == usuario.getSetor().getId()  ){
                spnSetor.setSelection( i );
            }
        }

    }


    private void EnviarOcorrencia(){

        AlertDialog alerta = new AlertDialog.Builder(this).create();
        if( TextUtils.isEmpty(edtDescricao.getText()) ){

            alerta.setTitle("Erro");
            alerta.setMessage("A descrição da Ocorrência precisa ser preenchida.");
            alerta.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ocorrencia.this.edtDescricao.requestFocus();
                }
            });

        }else{

            EnvioDAO envioDAO = new EnvioDAO(db);
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(db);

            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setDescricao( edtDescricao.getText().toString() );
            ocorrencia.setTipo((TipoOcorrencia) spnTipo.getSelectedItem());
            ocorrencia.setPapel(ocorrenciaDAO.getPapel(spnPapel.getSelectedItem().toString()));
            ocorrencia.setSetor((Setor) spnSetor.getSelectedItem());
            ocorrencia.setUsuario(usuario);

            envioDAO.enviarOcorrencia(ocorrencia);

            alerta.setTitle("Sucesso");
            alerta.setMessage("Ocorrencia Enviada com Sucesso.");
            edtDescricao.setText("");

        }

        alerta.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ocorrencia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_historico) {
            Intent intent = new Intent(this, historico.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

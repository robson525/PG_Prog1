package com.example.robmah.sigosapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.robmah.sigosapp.controle.*;
import com.example.robmah.sigosapp.database.DataBase;
import com.example.robmah.sigosapp.modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class identificacao extends ActionBarActivity {

    private DataBase database;
    private SQLiteDatabase db;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private Spinner spnTipoUsuario;
    private EditText edtOutro;
    private EditText edtMatricula;
    private Spinner spnUnidade;
    private Spinner spnSetor;
    private Button btSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identificacao);

        database = new DataBase(this);
        db = database.getWritableDatabase();

        /* Carrega Componentes */
        edtNome = (EditText)findViewById( R.id.edtNome );
        edtEmail = (EditText)findViewById( R.id.edtEmail ) ;
        edtTelefone = (EditText)findViewById( R.id.edtTelefone ) ;
        spnTipoUsuario = (Spinner)findViewById( R.id.spnTipo ) ;
        edtOutro = (EditText)findViewById( R.id.edtOutro ) ;
        edtMatricula  = (EditText)findViewById( R.id.edtIdentificador ) ;
        spnUnidade = (Spinner)findViewById( R.id.spnUnidade ) ;
        spnSetor = (Spinner)findViewById( R.id.spnSetor ) ;
        btSalvar = (Button)findViewById(R.id.btSalvar);
    /******************************************************************/


        /* Pegar Usuario */


        /* Tenta Pegar Email */
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        AccountManager accManager = AccountManager.get(this);
        Account acc[] = accManager.getAccountsByType("com.google");
        if(acc.length > 0){
            edtEmail.setText( acc[0].name );
        }else{
            edtEmail.setText( "robson@gmail.com" );
            Log.e("Email", "Nao encontrado");
        }

        /* Pegar Numero */
        TelephonyManager telefoneM = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String telefone = telefoneM.getLine1Number();
        edtTelefone.setText(telefone);


        /* Tipos de usuario */
        List<String> list = new ArrayList<String>();
        list.add("Estudante");
        list.add("Funcionario");
        list.add("Outro");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        spnTipoUsuario.setAdapter(dataAdapter);

        /* Outro tipo de usuairo*/
        final LinearLayout llOutroTipo = (LinearLayout)findViewById(R.id.llOutroTipo);
        llOutroTipo.setVisibility(View.GONE);
        spnTipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    llOutroTipo.setVisibility(View.VISIBLE);
                }else{
                    llOutroTipo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

                SetorDAO setorDAO = new SetorDAO(identificacao.this.db);
                ArrayList<Setor> setores = setorDAO.getListSetorByUnidade( unidade.getId() );
                ArrayAdapter<Setor> dataAdapter = new ArrayAdapter<Setor>(identificacao.this, android.R.layout.simple_list_item_1, setores);
                spnSetor.setAdapter(dataAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /*Botao*/
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                identificacao.this.Validar();
            }

        });


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


    private void Validar(){
        ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Carregando...");
        mDialog.setCancelable(false);
        mDialog.show();

        //Validacao
        boolean valido = true;
        View elemento = null;
        String label = null;
        boolean email = false;
        if( TextUtils.isEmpty( edtNome.getText() ) ){
            valido = false;
            elemento = edtNome;
            label = ((TextView) findViewById(R.id.tvNome)).getText().toString();
        }
        else if( TextUtils.isEmpty(edtEmail.getText())){
            valido = false;
            elemento = edtEmail;
            label = ((TextView) findViewById(R.id.tvEmail)).getText().toString();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText()).matches() ){
            valido = false;
            elemento = edtEmail;
            label = ((TextView) findViewById(R.id.tvEmail)).getText().toString();
            email = true;
        }
        else if( TextUtils.isEmpty( edtTelefone.getText() ) ){
            valido = false;
            elemento = edtTelefone;
            label = ((TextView) findViewById(R.id.tvTelefone)).getText().toString();
        }
        else if( TextUtils.equals(spnTipoUsuario.getSelectedItem().toString(), "Outro") && TextUtils.isEmpty( edtOutro.getText() ) ){
            valido = false;
            elemento = edtOutro;
            label = ((TextView) findViewById(R.id.tvOutro)).getText().toString();
        }
        else if( TextUtils.isEmpty( edtMatricula.getText() ) ){
            valido = false;
            elemento = edtMatricula;
            label = ((TextView) findViewById(R.id.tvMatricula)).getText().toString();
        }


        if(!valido){

            mDialog.dismiss();
            AlertDialog alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Campo Obrigatorio");
            alerta.setMessage("O Campo " + label + (email ? " e invalido." : " precisa ser preenchido." ));
            final View finalElemento = elemento;
            alerta.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finalElemento.requestFocus();
                }
            });
            alerta.show();

        }else{
            this.Salvar(mDialog);
        }


    }

    private void Salvar(ProgressDialog mDialog){

        Usuario usuario;

        if (spnTipoUsuario.getSelectedItem().toString() == "Estudante"){
            usuario = new Estudante();
        }
        else if(spnTipoUsuario.getSelectedItem().toString() == "Funcionario"){
            usuario = new Funcionario();
        }
        else {
            usuario = new Outro();
        }

        //Usuario usuario = new Usuario();
        usuario.setNome(edtNome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setTelefone(edtTelefone.getText().toString());

        usuario.setIdentificacao(edtMatricula.getText().toString());

        usuario.setSetor((Setor) spnSetor.getSelectedItem());

        usuario.Salvar(db);

        mDialog.dismiss();

        AlertDialog alerta = new AlertDialog.Builder(this).create();

        if(usuario.getUsuarioId() > 0){
            alerta.setCanceledOnTouchOutside(false);
            alerta.setTitle("Sucesso");
            alerta.setMessage("A sua identificaçãoo foi salva com sucesso. \nAgora você já pode enviar Ocorrências.");
            alerta.setButton("Ir para o Inicio", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    identificacao.this.Concluir();
                }
            });
        }else{
            alerta.setTitle("Erro");
            alerta.setMessage("Ocorreu um erro ao salvar seus dados.\nFavor Tente Novamente.");
        }

        alerta.show();

    }


    private void Concluir(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();//finishing activity
    }

}

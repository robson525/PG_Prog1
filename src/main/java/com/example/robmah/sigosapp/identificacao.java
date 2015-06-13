package com.example.robmah.sigosapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.robmah.sigosapp.database.DataBase;

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
        edtTelefone.setText( telefone );
                Log.e("Telefone", telefone);


        ArrayAdapter<String> dataAdapter = null;
        List<String> list = new ArrayList<String>();

        /* Tipos de usuario */
        list.add("Aluno");
        list.add("Funcionario");
        list.add("Outro");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        spnTipoUsuario.setAdapter(dataAdapter);

        list = this.ListUnidade();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        spnUnidade.setAdapter(dataAdapter);

        list = this.ListSetor();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        spnSetor.setAdapter(dataAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_identificacao, menu);
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


    private List<String> ListUnidade(){
        List<String> list = new ArrayList<String>();

        Cursor cursor = db.query("UNIDADE", null, null, null, null, null, null, null);
Log.e("Unidade", String.valueOf(cursor.getCount()));
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add( cursor.getString(cursor.getColumnIndex("NOME") ) );
            }while( cursor.moveToNext() );
        }

        return list;
    }

    private List<String> ListSetor(){
        List<String> list = new ArrayList<String>();

        Cursor cursor = db.query("SETOR", null, null, null, null, null, null, null);
        Log.e("Setor", String.valueOf(cursor.getCount()));
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add( cursor.getString(cursor.getColumnIndex("NOME") ) );
            }while( cursor.moveToNext() );
        }

        return list;
    }


}

package com.example.robmah.sigosapp;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.robmah.sigosapp.controle.UsuarioDAO;
import com.example.robmah.sigosapp.database.DataBase;


public class sigosapp extends Activity {

    private DataBase database;
    private SQLiteDatabase db;

    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new DataBase(this);
        db = database.getWritableDatabase();

        UsuarioDAO usuarioDAO = new UsuarioDAO(db);

        if(usuarioDAO.UsuarioRegistrado()){
            Intent intent = new Intent(this, ocorrencia.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_sigosapp);

        botao = (Button) findViewById( R.id.botao );

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sigosapp.this, identificacao.class);
                startActivityForResult(intent, 3249);

            }
        });


    }

    //Esepra o Retorno da activity Identificação
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3249){
            if(resultCode == Activity.RESULT_OK) {
                Intent intent = new Intent(this, ocorrencia.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sigosapp, menu);
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

package com.example.robmah.sigosapp.database;

/**
 * Created by Robson on 25/05/2015.
 */
public class ScriptsSQL {

    public static String getCreateUnidade(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS UNIDADE ( ");
        sqlBuilder.append("_id INTEGER PRIMARY KEY NOT NULL, ");
        sqlBuilder.append("NOME VARCHAR VARCHAR (100) NOT NULL ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateSetor(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS SETOR ( ");
        sqlBuilder.append("_id INTEGER PRIMARY KEY NOT NULL, ");
        sqlBuilder.append("NOME VARCHAR VARCHAR (100) NOT NULL, ");
        sqlBuilder.append("UNIDADE INTEGER REFERENCES UNIDADE (_id) ON DELETE SET NULL ON UPDATE CASCADE ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }


    public static String getCreateUsuario(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS USUARIO ( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME VARCHAR (200) NOT NULL, ");
        sqlBuilder.append("TELEFONE VARCHAR (14) NOT NULL, ");
        sqlBuilder.append("EMAIL VARCHAR (100) NOT NULL, ");
        sqlBuilder.append("IDENTIFICACAO VARCHAR (30) NOT NULL, ");
        sqlBuilder.append("TIPO INTEGER NOT NULL, ");
        sqlBuilder.append("OUTRO_TIPO VARCHAR (100), ");
        sqlBuilder.append("MUNICIPIO VARCHAR (100), ");
        sqlBuilder.append("SETOR INTEGER REFERENCES SETOR (_id) ON DELETE SET NULL ON UPDATE CASCADE ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }


    public static String[] getDefaultUnidade(){
        String[] sqlBuilder = {
        "INSERT INTO UNIDADE (_id, NOME) VALUES (1, 'Guama');",
        "INSERT INTO UNIDADE (_id, NOME) VALUES (2, 'Altamira');"};

        return sqlBuilder;
    }

    public static String[] getDefaultSetor(){
        String[] sqlBuilder = {
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (1, 'ICB', 1);",
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (2, 'ITEC', 1);",
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (3, 'Ginasio', 1);",
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (4, 'Biblioteca', 1);",
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (5, 'Biblioteca', 2);",
        "INSERT INTO SETOR (_id, NOME, UNIDADE) VALUES (6, 'Bloco A', 2);"};

        return sqlBuilder;
    }


}


package com.edu.uac.co.parcial_sqlite;

public class bdDef {


    public static final String BD_NAME = "REGISTRO";
    public static final String TABLE_NAME = "USUARIOS";
    public static final String ID_COL = "DOCUMENTO";
    public static final String NAME_COL = "NOMBRE";
    public static final String STRATUM_COL = "ESTRATO";
    public static final String WAGE_COL = "SALARIO";
    public static final String EDUCATION_LEVEL_COL = "NIVEL_EDUCACION";

    public static final String CREATE_TESTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            " " + ID_COL + " TEXT PRIMARY KEY," +
            " " + NAME_COL + " TEXT," +
            " " + STRATUM_COL + " TEXT," +
            " " + WAGE_COL + " TEXT," +
            " " + EDUCATION_LEVEL_COL + " TEXT" +
            ");";

}


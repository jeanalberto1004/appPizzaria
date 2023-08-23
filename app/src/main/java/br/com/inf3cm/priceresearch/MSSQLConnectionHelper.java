
        package br.com.inf3cm.priceresearch;


import android.content.Context;

import android.os.StrictMode;

import android.util.Log;


import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;


public class MSSQLConnectionHelper {


// essa classe representa as strings de conexao com banco de dados

// 1 - SQLSERVER em SOMEE.COM

// 2 - SQLSERVER no laboratório/casa LOCALHOST - detalhe o APP só irá funcionar

// no EMULADOR ou conectado via cabo USB ao seu celular

// 3 - MySql


    public static final String TAG = "Database Connection";


    private static String mStringConnectionUrl;

// para conseguir pegar o ipname abrir cmd ou powershell e escrever


// 1 - para o SOMEE.COM

//private static String mStringServerIpName = "Pizzaria.mssql.somee.com";

//private static String mStringUserName = "jean1004";

//private static String mStringPassword = "10042005";

// private static String mStringDatabase = "Pizzaria";


//2 - para LOCALHOST - funciona no emulador ou via cabo usb

    private static String mStringServerIpName ="192.168.11.220";

    private static String mStringUserName = "sa";

    private static String mStringPassword = "@ITB123456";


    private static String mStringDatabase = "Pizzaria";

    private static String mStringPort = "8080";

    private static String mStringInstance = "SQLEXPRESS";

// a codificação anterior funcionar para o - MySql


    public static Connection getConnection(Context mContext){

        Connection mConnection = null;

        try{

// adicionar uma politica (policy) para criacao de uma tarefa (thread)

            StrictMode.ThreadPolicy mPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(mPolicy);


// verificar se o driver(bibilioteca) de conexao foi copiada/implementada para o projeto

            Class.forName("net.sourceforge.jtds.jdbc.Driver");


// criando a string de conexao para o SOMEE.COM

            mStringConnectionUrl = "jdbc:jtds:sqlserver://" + mStringServerIpName +

                    ";databaseName=" + mStringDatabase +

                    ";user=" + mStringUserName +

                    ";password=" + mStringPassword + ";" ;


// para o LOCALHOST ou MySql a mStringConnectionUrl é programada de outra forma


            mConnection = DriverManager.getConnection(mStringConnectionUrl);


// passar para o programador uma mensagem no Android Studio - registrar (Log) o que acontece


            Log.i(TAG , "Connection successful"); // deu certo a conexao com o banco


        } catch (ClassNotFoundException e){

            String mMessage = "Class not found" + e.getMessage();

            Log.e(TAG, mMessage);


        } catch (SQLException e){

            String mMessage = "Database fail" + e.getMessage();

            Log.e(TAG, mMessage);


        } catch (Exception e){

            String mMessage = "Error unknown" + e.getMessage();

            Log.e(TAG, mMessage);


        }



        return mConnection;

    }


}

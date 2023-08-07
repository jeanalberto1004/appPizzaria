package br.com.inf3cm.priceresearch;

import static net.sourceforge.jtds.util.Logger.log;

import android.util.log;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {


    private EditText editTextUsername, editTextPassword;

    private Button buttonRegister;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        editTextUsername = findViewById(R.id.editTextUsername);

        editTextPassword = findViewById(R.id.editTextPassword);

        buttonRegister = findViewById(R.id.buttonRegister);


        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                // Capturar os dados do usuário

                String username = editTextUsername.getText().toString();

                String password = editTextPassword.getText().toString();


                // Você pode realizar ações como enviar os dados para um servidor ou salvar localmente

                // Exemplo: chamar um método para registrar o usuário em um banco de dados


                // Após o registro, você pode redirecionar o usuário para outra atividade

                // Exemplo: startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }

        });

    }

}

public class MinhaClasse{

    private static final String TAG="MinhaClasse";

    public void exemploDeRegistro(){
        log.d(TAG,"isso é uma mensagem de registro com nivel debug.");
    }
}
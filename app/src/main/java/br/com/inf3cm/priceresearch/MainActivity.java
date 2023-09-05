package br.com.inf3cm.priceresearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.tcc.pizzaria.R;


//public static final String IP_VALUE = "192.168.0.13";
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_LoginRegistration_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
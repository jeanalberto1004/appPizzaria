package br.com.inf3cm.priceresearch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import br.com.tcc.pizzaria.R;

public class NewPasswordActivity extends AppCompatActivity {

    EditText mEditTextNewPassword, mEditTextOtp;
    Button mButtonSubmitOtp;
    ProgressBar mProgressBarNewPassword;

    String mEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_senha);

        mEditTextNewPassword = findViewById(R.id.editText_new_password);
        mEditTextOtp = findViewById(R.id.editText_otp);
        mButtonSubmitOtp = findViewById(R.id.button_submit_otp);
        mButtonSubmitOtp.setOnClickListener(new ResetPasswordActivity.ClickButtonSubmit());
//        ClickButtonSubmitNewPassword
        mProgressBarNewPassword = findViewById(R.id.progressBar_new_password);

        mEmail = getIntent().getExtras().getString("email");

    }


    private void performSubmit() {

        Toast.makeText(this, "ops falta algo aqui", Toast.LENGTH_SHORT).show();
        // https://developer.android.com/training/volley?hl=pt-br
        // https://www.codeseasy.com/google-volley-android/

        mProgressBarNewPassword.setVisibility(View.VISIBLE);


        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

        String mUrl = "http://192.168.0.13/app-login-register/new-password.php";



        }
    }


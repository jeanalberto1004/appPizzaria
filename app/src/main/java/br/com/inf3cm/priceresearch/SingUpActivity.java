package br.com.inf3cm.priceresearch;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import br.com.tcc.pizzaria.R;
public class SingUpActivity extends AppCompatActivity{

    private static final String TAG = "SignUpActivity";

    private EditText mEditTextTelefone;

    private EditText mEditTextCep;

    private EditText mEditTextEmail;

    private EditText mEditTextSenha;

    private EditText mEditTextNome;

    private EditText mEditTextLogradouro;

    private EditText mEditTextCidade;

    private EditText mEditTextBairro;


    private EditText mEditTextComplemento;
    private EditText mEditTextNumeroResid;


    private Button mButtonSignUp;

    private TextView mTextViewAlreadyLogin;

    private ProgressBar mProgressBar;

    private String mStringTelefone, mStringCep ,mStringEmail, mStringsenha, mStringNome, mStringLongradouro , mStringCidade , mStringBairro ,  mStringComplemento ,mStringNumeroResid ;

    private boolean isRequired(){
        if(TextUtils.isEmpty(mEditTextTelefone.getText()) ||
                TextUtils.isEmpty(mEditTextCep.getText()) ||
                TextUtils.isEmpty(mEditTextEmail.getText()) ||
                TextUtils.isEmpty(mEditTextSenha.getText()) ||
                TextUtils.isEmpty(mEditTextNome.getText()) ||
                TextUtils.isEmpty(mEditTextLogradouro.getText()) ||
                TextUtils.isEmpty(mEditTextCidade.getText()) ||
                TextUtils.isEmpty(mEditTextBairro.getText()) ||
                TextUtils.isEmpty(mEditTextComplemento.getText()) ||
                TextUtils.isEmpty(mEditTextNumeroResid.getText())) {
            return true;
        } else {
            return false;
        }
    }

//    private boolean isSamePassword(){
//        String mPassword1 = mEditTextPasswordSignUp.getText().toString();
//        String mPassword2 = mEditTextPasswordSignUp2.getText().toString();
//        return mPassword1.equals(mPassword2);
//    }

    private void performActivityLogin(){
        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void postData(){
        if(isRequired()){
            String mTextMessage = getString(R.string.text_error_all_fields_required);
            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
            return;
        }
//
//        if(!isSamePassword()){
//            String mTextMessage = getString(R.string.text_password_are_not_same);
//            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
        mStringTelefone = String.valueOf(mEditTextTelefone.getText());
        mStringCep = String.valueOf(mEditTextCep.getText());
        mStringEmail = String.valueOf(mEditTextEmail.getText());
        mStringsenha = String.valueOf(mEditTextSenha.getText());
        mStringNome = String.valueOf(mEditTextNome.getText()).toLowerCase(Locale.ROOT);
        mStringLongradouro = String.valueOf(mEditTextLogradouro.getText());
        mStringCidade = String.valueOf(mEditTextCidade.getText());
        mStringBairro = String.valueOf(mEditTextBairro.getText());
        mStringComplemento = String.valueOf(mEditTextComplemento.getText());
        mStringNumeroResid = String.valueOf(mEditTextNumeroResid.getText());

        mProgressBar.setVisibility(View.VISIBLE);

        User mUser = new User(mStringTelefone,mStringCep, mStringEmail,mStringsenha , mStringNome, mStringLongradouro,mStringCidade, mStringBairro, mStringComplemento, System.currentTimeMillis() );

        int vResult = UserDao.insertUser(mUser, getApplicationContext());

        String mTextMessage;

        mButtonSignUp.setVisibility(View.GONE);

        if(vResult <= 0){
            mTextMessage = getString(R.string.text_insert_error);
        } else {
            mTextMessage = getString(R.string.text_insert_success);
        }

        mProgressBar.setVisibility(View.GONE);

        Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();

        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(mIntent);
        finish();

    }

    public class ClickButtonSignUp implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            postData();
        }
    }

    public class ClickTextViewAlreadyLogin implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            performActivityLogin();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_se);

             getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mEditTextTelefone = findViewById(R.id.editText_telefone);
        mEditTextCep = findViewById(R.id.editText_cep);
        mEditTextEmail = findViewById(R.id.editText_email);
        mEditTextSenha = findViewById(R.id.editText_password_sign_up);
        mEditTextNome = findViewById(R.id.editText_fullname);
        mEditTextLogradouro = findViewById(R.id.editText_logradouro);
        mEditTextCidade = findViewById(R.id.editText_cidade);
        mEditTextBairro = findViewById(R.id.editText_bairro);
        mEditTextComplemento = findViewById(R.id.editText_complemento);
        mEditTextNumeroResid = findViewById(R.id.editText_numeroResid);

        mTextViewAlreadyLogin = findViewById(R.id.textView_already);
        mTextViewAlreadyLogin.setOnClickListener(new ClickTextViewAlreadyLogin());

        mProgressBar = findViewById(R.id.progressBarSignUp);

        mButtonSignUp = findViewById(R.id.button_sign_up);
        mButtonSignUp.setOnClickListener(new ClickButtonSignUp());

    }
}

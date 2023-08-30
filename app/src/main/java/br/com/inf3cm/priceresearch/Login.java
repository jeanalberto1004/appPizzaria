package br.com.inf3cm.priceresearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import br.com.tcc.pizzaria.R;

public class Login extends AppCompatActivity {

    public static final String TAG = "Login Activity";

            TextView mTextViewNewUser, mTextViewForgotPassword;
            Button mButtonLogin;
            EditText mEditTextEmail , mEditTextPassword;
            ProgressBar mProgressBarLogin;
            String mStringUser ,mStringPassword , mStringEmail;
            SharedPreferences mSharedPreferences;

            private  boolean isRequiredPassword(){return TextUtils.isEmpty(mEditTextPassword.getText());}
            private boolean isValidEmail(String mStringEmail){
               if(mStringEmail == null || mStringEmail.isEmpty()){

                    return false;

                    }
                    return Patterns.EMAIL_ADDRESS.matcher(mStringEmail).matches();
            }

            private void showProduct(){
                    Intent mIntent = new Intent(getApplicationContext() , Product.class);
                    startActivity(mIntent);
                    finish();
            }

            private void verifyLogged(){
                    if (mSharedPreferences.getString("logged" , "false").equals("true")){
                            showProduct();

                    }
            }

            private void postData(){
                    mStringEmail = String.valueOf(mEditTextEmail.getText()).toLowerCase(Locale.ROOT);
                    mStringPassword = String.valueOf(mEditTextPassword.getText());

                    if (!isValidEmail(mStringEmail)){
                            String mTextMessage = getString(R.string.text_email_not_valid);
                            Toast.makeText(getApplicationContext() , mTextMessage , Toast.LENGTH_SHORT).show();
                            return;
                    }

                    if(isRequiredPassword()){
//                            provavelmente esta com erro no mandatory
                            String mTextMessage = getString(R.string.text_error_fill_mandatory_information);
                            Toast.makeText(getApplicationContext() , mTextMessage , Toast.LENGTH_SHORT).show();
                            return;
                    }
                mProgressBarLogin.setVisibility(View.VISIBLE);

                    User mUser = new User(mStringPassword , mStringEmail);

                    String mResult = UserDao.authenticateUser(mUser , getApplicationContext());

                     mProgressBarLogin.setVisibility(View.GONE);

                     if(mResult.isEmpty() || mResult.equals("") || mResult.equals("Exception") ) {
                         String mTextMessage;
                         mTextMessage = getString(R.string.text_email_or_password_incorret);

                      if (mResult.equals("Exception")) {
                       mTextMessage = getString(R.string.text_connection_error);
                       
                      }
                         Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
                      return;
                     }
             SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString("logged","true");
                 mEditor.putString("email" , mStringEmail);
                 mEditor.putString("fullname" , mResult);
                 mEditor.apply();

                 Intent mIntent = new Intent(getApplicationContext() , Product.class);
                 mIntent.putExtra("EXTRA_FULL_NAME" , mResult);
                 startActivity(mIntent);
                 finish();

            }

            public class ClickMyButtonLogin implements View.OnClickListener{
                @Override
                public void onClick(View view) {
                    postData();
                }
            }

            private void showSingUp(){
                Intent mIntent = new Intent(getApplicationContext() , SignUpActivity.class);
                startActivity(mIntent);
                finish();
    }
            public class ClickMyNewUser implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            showSingUp();

        }
    }

            private void showForgotPassword(){
               Intent mIntent = new Intent(getApplicationContext() ,ResetPasswordActivity.class);
               startActivity(mIntent);
               finish();
            }

            public class ClickMyForgotPassword implements View.OnClickListener{
                @Override
                public void onClick(View view) {
                    showForgotPassword();
                }
            }

            public class EditorMyTextAction implements TextView.OnEditorActionListener{
                @Override
                public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE){
                        postData();
                    }

                    return false;
                }
            }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEditTextEmail = findViewById(R.id.editText_email_login);
        mEditTextPassword = findViewById(R.id.editText_password_login);
        mEditTextPassword.setOnEditorActionListener(new EditorMyTextAction());

        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new ClickMyButtonLogin());

        mProgressBarLogin = findViewById(R.id.progressBar_login);

        mTextViewNewUser = findViewById(R.id.textView_new_user);
        mTextViewNewUser.setOnClickListener(new ClickMyNewUser());

        mTextViewForgotPassword = findViewById(R.id.textView_forgot_password);
        mTextViewForgotPassword.setOnClickListener(new ClickMyForgotPassword());

        mSharedPreferences = getSharedPreferences("Tcc_Flash" , MODE_PRIVATE);

        verifyLogged();


    }
}








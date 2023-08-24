package br.com.inf3cm.priceresearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

            private void verifylogged(){
                    if (mSharedPreferences.getString("logged" , "false").equals(true)){
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

                    User mUser = new User(mStringPassword , m0);

                    String mResult = UserDao.authenticateUser(mUser , getApplicationContext());
            }
 }



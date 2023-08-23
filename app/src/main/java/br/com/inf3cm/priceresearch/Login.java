package br.com.inf3cm.priceresearch;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    public static final String TAG = "Login Activity"

            TextView mTextViewNewUser, mTextViewForgotPassword;
            Button mButtonLogin;
            EditText mEditTextEmail , mEditTextPassword;
            ProgressBar mProgressBarLogin;
            String mStringUser ,mStringPassword , mStringEmail;
            SharedPreferences mSharedPreferences;

            private  boolean isRequiredPassword (){
                    return TextUtils.isEmpty(mEditTextPassword.getText())
            private boolean isValidEmail(String mStringEmail.isEmpty()){

                    }
            }

}

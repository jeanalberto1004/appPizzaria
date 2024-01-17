package br.com.inf3cm.priceresearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import br.com.tcc.pizzaria.R;

public class CupomActivity extends AppCompatActivity {

    private static final String TAG = "cupom activity";


    public CupomAdapter mCupomAdapter;

    TextView mTextViewFullName;

    FloatingActionButton mFloatingActionButtonLogout;

    SharedPreferences mSharedPreferences;


    private void setupRecyclerView(){

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView_cupons);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext())); // Apenas passe o contexto (this) aqui
        mRecyclerView.setHasFixedSize(true);
        mCupomAdapter = new CupomAdapter(getApplicationContext(), CupomDao.listAllCupoms(getApplicationContext()) );
        mRecyclerView.setAdapter(mCupomAdapter);

        mCupomAdapter.setCupomList(CupomDao.listAllCupoms(getApplicationContext()));

    }

    private void verifyNotLogged(){
        if(mSharedPreferences.getString("logged", "false").equals("false")){
            showLogin();
        } else {
            //https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
//            mTextViewFullName.setText(mSharedPreferences.getString("fullName", ""));
            setupRecyclerView();
        }
    }

    private void showLogin(){
        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(mIntent);
        finish();
    }


    private void exitAppLogoff(){
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("logged" , "");
        mEditor.putString("email" , "");
        mEditor.putString("fullName", "");
        mEditor.apply();
        showLogin();
    }

    public class ClickMyButtonLogout implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            exitAppLogoff();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom);

//        mTextViewFullName = findViewById(R.id.textView_fullName);


        Intent mIntent = getIntent();
        if(mIntent.hasExtra("EXTRA_FULL_NAME")){
//            mTextViewFullName.setText(mIntent.getStringExtra("EXTRA_FULL_NAME"));
        }


        mFloatingActionButtonLogout = findViewById(R.id.fab_exit_app);
        mFloatingActionButtonLogout.setOnClickListener(new ClickMyButtonLogout());


        mSharedPreferences = getSharedPreferences("MyAppName" , MODE_PRIVATE);

        verifyNotLogged();

    }


}

package br.com.inf3cm.priceresearch;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import br.com.tcc.pizzaria.R;








    public class CardapioActivity extends AppCompatActivity {

        private static final String TAG = "Cardapio activity";


        public CardapioAdapter mCardapioAdapter;
        
        

        SharedPreferences mSharedPreferences;


        private void setupRecyclerView(){

            RecyclerView mRecyclerView = findViewById(R.id.recyclerView_cardapios);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext())); // Apenas passe o contexto (this) aqui
            mRecyclerView.setHasFixedSize(true);
            mCardapioAdapter = new CardapioAdapter(getApplicationContext(), CardapioDao.listAllCardapios(getApplicationContext()) );
            mRecyclerView.setAdapter(mCardapioAdapter);

            mCardapioAdapter.setCardapioList(CardapioDao.listAllCardapios(getApplicationContext()));

        }







        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.actitivity_cardapio);

//        mTextViewFullName = findViewById(R.id.textView_fullName);


            Intent mIntent = getIntent();
            if(mIntent.hasExtra("EXTRA_FULL_NAME")){
//            mTextViewFullName.setText(mIntent.getStringExtra("EXTRA_FULL_NAME"));
            }


          

            mSharedPreferences = getSharedPreferences("MyAppName" , MODE_PRIVATE);



        }


    }



//package br.com.inf3cm.priceresearch;
//
//// 5
//
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import br.com.tcc.pizzaria.R;
//
//public class CupomActivity extends AppCompatActivity {
//
//    private static final String TAG = "CupomActivity";
//
//    public CupomAdapter mCupomAdapter;
//
//    TextView mTextViewFullName ;
//
//    ExtendedFloatingActionButton mFloatingActionButtonAction;
//    FloatingActionButton mFloatingActionButtonLogout;
//
//    FloatingActionButton mFloatingActionButtonFinalizeList;
//
//    TextView  mTextViewLogout, mTextViewFinalize;
//
//    Boolean isAllFabsVisible; //para verificar se os sub FABs são visíveis ou não
//
//    SharedPreferences mSharedPreferences;
//
//    //https://www.youtube.com/watch?v=6E5ODrmUtmo
//    //    bom   https://www.youtube.com/watch?v=Ke9PaRdMcgc
//    // esclareceu melhor https://www.youtube.com/watch?v=qO3FFuBrT2E
////https://developer.android.com/training/basics/intents/result?hl=pt-br
//    ActivityResultLauncher<Intent>  mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if(result.getResultCode() == RESULT_OK && result.getData() != null){
//                Bundle mBundleResponse = result.getData().getExtras();
//                int vId = mBundleResponse.getInt("EXTRA_ID", -1);
//                String mName = mBundleResponse.getString("EXTRA_NAME");
//                float vPrice = mBundleResponse.getFloat("EXTRA_PRICE", -1.0f);
//                float vPerception = mBundleResponse.getFloat("EXTRA_PERCEPTION_PRICE", -1.0f);
//                String mMode = mBundleResponse.getString("EXTRA_MODE");
//
//                String mMessage = "CRUD message";
//
//
//                 Toast.makeText(CupomActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//            } else if(result.getResultCode() == RESULT_CANCELED){
//                // Toast.makeText(CupomActivity.this, "Action to add or edit was canceled", Toast.LENGTH_SHORT).show();
//                Snackbar.make(findViewById(R.id.screen_list), "Action to add or edit was canceled", Snackbar.LENGTH_SHORT).show();
//                //snackbar muitas possibilidades    https://www.geeksforgeeks.org/how-to-add-a-snackbar-in-android/
//            } else {
//                //Toast.makeText(CupomActivity.this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
//                Snackbar.make(findViewById(R.id.screen_list), "An unexpected error occurred", Snackbar.LENGTH_SHORT).show();
//            }
//        }
//    });
//
//
//
////    private void showPopupMenu(View mItemView){  //assista a esse video https://www.youtube.com/watch?v=fl5BB3I3MvQ   e   https://www.youtube.com/watch?v=WsUeeeKte3I  e https://www.youtube.com/watch?v=tIRGRVzYuhc
////        // Criação do PopupMenu
////        PopupMenu popup = new PopupMenu(mItemView.getContext(), mItemView);
////        MenuInflater inflater = popup.getMenuInflater();
//////        // Inflar o menu com o arquivo xml
////        inflater.inflate(R.menu.reaction_menu, popup.getMenu());
////
////       // Definir o ClickListener
////        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////            @Override
////            public boolean onMenuItemClick(MenuItem item) {
////                switch (item.getItemId()) {
////                    case R.id.reaction_edit:
////                        // Implemente a ação para a reação de "like"
////                        Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case R.id.reaction_shop:
////                        // Implemente a ação para a reação de "love"
////                        Toast.makeText(getApplicationContext(), "Shop", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case R.id.reaction_delete:
////                        // Implemente a ação para a reação de "love"
////                        Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case R.id.reaction_reset:
////                        // Implemente a ação para a reação de "love"
////                        Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case R.id.reaction_close:
////                        // Implemente a ação para a reação de "love"
////                        Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
////                        return true;
////                    // adicione mais casos para mais reações
////                    default:
////                        return false;
////                }
////            }
////        });
////
////
////        Toast.makeText(this, "LONG PRESS", Toast.LENGTH_SHORT).show();
////        // Mostrar o PopupMenu
//////        popup.inflate(R.menu.reaction_menu);
//////        popup.setGravity(Gravity.RIGHT);
////   //erro     popup.show();
////    }
//
//
////    private View.OnLongClickListener onLongItemClickListener = new View.OnLongClickListener() {
////        @Override
////        public boolean onLongClick(View view) {
////            showPopupMenu(view);
////            return false;
////        }
////    };
//
//    private void setupRecyclerView(){
//        RecyclerView mRecyclerView = findViewById(R.id.recyclerView_cupons);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        mRecyclerView.setHasFixedSize(true);
//        mCupomAdapter = new CupomAdapter(getApplicationContext(), CupomDao.listAllProducts(getApplicationContext()) );
//        mRecyclerView.setAdapter(mCupomAdapter);
//
//        //tem mais código aqui veja TaskActivity.java do app Gerenciamento de tarefas
//        mCupomAdapter.setProductList(CupomDao.listAllProducts(getApplicationContext()));
//
//        mCupomAdapter.setOnItemClickListener(o);
//
//        //  mCupomAdapter.setOnLongClickListener(onLongItemClickListener);
//
//        // 17-03 https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28  botoes para editar e deletar
//
//    }
//
//    private void verifyNotLogged(){
//        if(mSharedPreferences.getString("logged", "false").equals("false")){
//            showLogin();
//        } else {
//            //https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
//            mTextViewFullName.setText(mSharedPreferences.getString("fullName", ""));
//            setupRecyclerView();
//        }
//    }
//
//    private void showLogin(){
//        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(mIntent);
//        finish();
//    }
//
//    private void exitAppLogoff(){
//        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
//        mEditor.putString("logged" , "");
//        mEditor.putString("email" , "");
//        mEditor.putString("fullName", "");
//        mEditor.apply();
//        showLogin();
//    }
//
//    public class ClickMyButtonLogout implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            exitAppLogoff();
//        }
//    }
//
//    private void changeStateFabsAndTexts(){
//        if(!isAllFabsVisible){
//            mFloatingActionButtonLogout.show();
//            mFloatingActionButtonFinalizeList.show();
//            mTextViewLogout.setVisibility(View.VISIBLE);
//            mTextViewFinalize.setVisibility(View.VISIBLE);
//            mFloatingActionButtonAction.extend();
//            isAllFabsVisible=true;
//        } else {
//
//            mFloatingActionButtonLogout.hide();
//            mFloatingActionButtonFinalizeList.hide();
//            mTextViewLogout.setVisibility(View.GONE);
//            mTextViewFinalize.setVisibility(View.GONE);
//            mFloatingActionButtonAction.shrink();
//            isAllFabsVisible=false;
//        }
//    }
//
//    public class ClickMyButtonAction implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            changeStateFabsAndTexts();
//        }
//    }
//
//
//
//    private void finalizeCupomList(){
//        Toast.makeText(getApplicationContext(), "Vai finalizar", Toast.LENGTH_SHORT).show();
//        //logica para obter os itens com quantidade e gerar lista
//    }
//
//    public class ClickMyButtonFinalizeList implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            finalizeCupomList();
//        }
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cupom);
//
//        mTextViewFullName = findViewById(R.id.textView_fullNme);
//
//        Intent mIntent = getIntent();
//        if(mIntent.hasExtra("EXTRA_FULL_NAME")){
//            mTextViewFullName.setText(mIntent.getStringExtra("EXTRA_FULL_NAME"));
//        }
//
//        mFloatingActionButtonAction = findViewById(R.id.fab_action);
//        mFloatingActionButtonAction.setOnClickListener(new ClickMyButtonAction());
//
//        mFloatingActionButtonLogout = findViewById(R.id.fab_exit_app);
//        mFloatingActionButtonLogout.setOnClickListener(new ClickMyButtonLogout());
//
//
//
//
//        mFloatingActionButtonFinalizeList = findViewById(R.id.fab_finalize_Cupom_list);
//        mFloatingActionButtonFinalizeList.setOnClickListener(new ClickMyButtonFinalizeList());
//
//
//        mTextViewLogout = findViewById(R.id.textView_logout_Cupom);
//        mTextViewFinalize = findViewById(R.id.textView_finalize_Cupom_list);
//
//
//
//        mFloatingActionButtonLogout.setVisibility(View.GONE);
//
//        mFloatingActionButtonFinalizeList.setVisibility(View.GONE);
//
//        mTextViewLogout.setVisibility(View.GONE);
//
//        mTextViewFinalize.setVisibility(View.GONE);
//
//        isAllFabsVisible = false;
//
//        mFloatingActionButtonAction.shrink(); //estado reduzido inicialmente
//
//        //https://stackoverflow.com/questions/30699302/android-design-support-library-expandable-floating-action-buttonfab-menu
//
//        // https://medium.com/nerd-for-tech/how-to-add-extended-floating-action-button-in-android-android-studio-java-481cc9b3cdcb
//        // https://www.youtube.com/watch?v=AIMZBwORt1k&t=548s
//
//        mSharedPreferences = getSharedPreferences("MyAppName" , MODE_PRIVATE);
//        verifyNotLogged();
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){ //tres pontinhos
//
//        isAllFabsVisible=false;
//        changeStateFabsAndTexts();
//
//        switch (item.getItemId()) {
//            case R.id.action_add:
//
//                return true;
//            case R.id.action_delete_all:
//                //mTaskViewModel.deleteAll();
//                Toast.makeText(this, "All Cupoms have been deleted.", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//}

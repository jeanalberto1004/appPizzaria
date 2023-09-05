package br.com.inf3cm.priceresearch;

// 5

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.pizzaria.R;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";


    public ProductAdapter mProductAdapter;

    TextView mTextViewFullName, mTextViewTotalValue;

    ExtendedFloatingActionButton mFloatingActionButtonAction;
    FloatingActionButton mFloatingActionButtonLogout;

    TextView mTextViewAddProduct, mTextViewLogout, mTextViewFinalize;

    Boolean isAllFabsVisible; //para verificar se os sub FABs são visíveis ou não

    SharedPreferences mSharedPreferences;

    ActivityResultLauncher<Intent>  mActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                Bundle mBundleResponse = result.getData().getExtras();
                int vId = mBundleResponse.getInt("EXTRA_ID", -1);

                String mName = mBundleResponse.getString("EXTRA_NAME");

                float vPrice = mBundleResponse.getFloat("EXTRA_PRICE", -1.0f);

                String mMessage = "CRUD message"; //Aunt Little Mary


    };

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder mViewHolder = (RecyclerView.ViewHolder) view.getTag();
            int vPosition = mViewHolder.getAdapterPosition();
            Intent mIntent = new Intent(getApplicationContext(), ProductAddEditActivity.class);
            //Intent mIntent = new Intent(ProductActivity.this, ProductAddEditActivity.class);
            mIntent.putExtra("EXTRA_ID", mProductAdapter.getProductFrom(vPosition).getId());
            mIntent.putExtra("EXTRA_NAME", mProductAdapter.getProductFrom(vPosition).getName());
            mIntent.putExtra("EXTRA_PRICE", mProductAdapter.getProductFrom(vPosition).getPrice());
            mActivityResultLauncher.launch(mIntent);
        }
    };








    private void setupRecyclerViewAdapter(){
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView_cupons);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        //aqui é o segredo
        // (1)   mProductAdapter = new ProductAdapter(getApplicationContext(), ProductDao.listAllProducts(getApplicationContext()) );
        // (2)   mProductAdapter = new ProductAdapter(getApplicationContext(), ProductDao.listAllProducts(getApplicationContext()) , mTextViewTotalValue ); //aqui é o segredo   - nv2 na classe ProductAdapter
        // em (2) informamos textview para o valor total; poderiam ser varios outros objetos
        mProductAdapter = new ProductAdapter(getApplicationContext(), ProductDao.listAllProducts(getApplicationContext()) , mTextViewTotalValue ); //aqui é o segredo   - nv2 na classe ProductAdapter

        mRecyclerView.setAdapter(mProductAdapter);

        //tem mais código aqui veja TaskActivity.java do app Gerenciamento de tarefas Youtube
        mProductAdapter.setProductList(ProductDao.listAllProducts(getApplicationContext())); //resultado do select

        mProductAdapter.setOnItemClickListener(onItemClickListener); // ABANDONAR ESTA ABORDAGEM, usando Helper para Swipe ABAIXO MYSWIPEHELPER

        //mProductAdapter.setOnLongClickListener(onLongItemClickListener);



        /**
        MySwipeHelper swipeHelper = new MySwipeHelper(getApplicationContext() , mRecyclerView , 200) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {
                buffer.add(new MyButton(getApplicationContext(),
                        "Delete" ,
                        30,
                        0,
                        Color.parseColor("#FF3C30") ,
                        new MyButtonClickListener() {
                        @Override
                        public void onClick(int position) {
                            String mName = mProductAdapter.getProductAt(position).getName();
//                            String mName = mProductAdapter.getProductAt(viewHolder.getAdapterPosition()).getName();
                            ProductDao.deleteProduct( mProductAdapter.getProductAt(position), getApplicationContext());
//                            ProductDao.deleteProduct( mProductAdapter.getProductAt(viewHolder.getAdapterPosition()), getApplicationContext());
                            mTextViewTotalValue.setText("R$ 0,00");
                            Toast.makeText(getApplicationContext(), "Deleted " + mName, Toast.LENGTH_SHORT).show();
                            mProductAdapter.setProductList(ProductDao.listAllProducts(getApplicationContext()));
                        }
                } )) ;

                buffer.add(new MyButton(getApplicationContext(),
                        "Update" ,
                        30,
                        R.drawable.ic_update,
                        Color.parseColor("#FF9502") ,
                        new MyButtonClickListener() {
                        @Override
                        public void onClick(int position) {

                            vSelectedPosition = position;
                            Product mProduct = mProductAdapter.getProductAt(position);

//                            Intent mIntent = new Intent(getApplicationContext(), ProductAddEditActivity.class);
                            Intent mIntent = new Intent(ProductActivity.this, ProductAddEditActivity.class);
                            mIntent.putExtra("EXTRA_ID", mProduct.getId());

                            mIntent.putExtra("EXTRA_NAME", mProduct.getName());
                            mIntent.putExtra("EXTRA_PRICE", mProduct.getPrice());
                            mIntent.putExtra("EXTRA_PERCEPTION_PRICE", mProduct.getRating());
                            mIntent.putExtra("EXTRA_AMOUNT_CONSUMPTION", mProduct.getAmountConsumption());
                            mIntent.putExtra("EXTRA_CONSUMPTION_CYCLE", mProduct.getConsumptionCycle());
                            mActivityResultLauncher.launch(mIntent);
                            //Toast.makeText(getApplicationContext(), "Update Click " + position, Toast.LENGTH_SHORT).show();
                        }
                } )) ;
            }
        };
*/







                                    Product mProduct = mProductAdapter.getProductFrom(position);

//                            Intent mIntent = new Intent(getApplicationContext(), ProductAddEditActivity.class);
                                    Intent mIntent = new Intent(ProductActivity.this, ProductAddEditActivity.class);
                                    mIntent.putExtra("EXTRA_ID", mProduct.getId());

                                    mIntent.putExtra("EXTRA_NAME", mProduct.getName());
                                    mIntent.putExtra("EXTRA_PRICE", mProduct.getPrice());
                                    mActivityResultLauncher.launch(mIntent);
                                    //Toast.makeText(getApplicationContext(), "Update Click " + position, Toast.LENGTH_SHORT).show();
                                }
                            } ;
              ;

    private void showLogin(){
        Intent mIntent = new Intent(getApplicationContext(), Login.class);
        startActivity(mIntent);
        finish();
    }

    private void verifyNotLogged(){
        if(mSharedPreferences.getString("logged", "false").equals("false")){
            showLogin(); // invertida o ordem atencao
        } else {
            //https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
            mTextViewFullName.setText(mSharedPreferences.getString("fullName", ""));
            setupRecyclerViewAdapter();
        }
    }



    private void exitAppLogoff(){
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("logged" , "");
        mEditor.putString("email" , "");
        mEditor.putString("fullName", "");
        mEditor.apply();
        showLogin();
    }

    //ClickMyButtonLogout  ClickMyButtonLogoff
    public class ClickMyButtonLogout implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            exitAppLogoff();
        }
    }

    private void changeStateFabsAndTexts(){
        if(!isAllFabsVisible){
            mFloatingActionButtonLogout.show();
            mTextViewAddProduct.setVisibility(View.VISIBLE);
            mTextViewLogout.setVisibility(View.VISIBLE);
            mTextViewFinalize.setVisibility(View.VISIBLE);
            mFloatingActionButtonAction.extend();
            isAllFabsVisible=true;
        } else {
            mFloatingActionButtonLogout.hide();
            mTextViewAddProduct.setVisibility(View.GONE);
            mTextViewLogout.setVisibility(View.GONE);
            mTextViewFinalize.setVisibility(View.GONE);
            mFloatingActionButtonAction.shrink();
            isAllFabsVisible=false;
        }
    }

    public class ClickMyButtonAction implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            changeStateFabsAndTexts();
        }
    }

    private void showAddProduct(){
        changeStateFabsAndTexts();
        Intent mIntent = new Intent(ProductActivity.this, ProductAddEditActivity.class);
        mActivityResultLauncher.launch(mIntent);
    }

    public class ClickMyButtonAdd implements View.OnClickListener{ //androidmanifest
        @Override
        public void onClick(View view) {
            showAddProduct();
        }
    }

    private void finalizeProductList(){
        mProductAdapter.getProductsToBuy(); //26-06-2023
//https://stackoverflow.com/questions/34296149/creating-a-pdf-file-in-android-programmatically-and-writing-in-it
//criar no banco uma tabela com a lista - utilize data e horario

        Toast.makeText(getApplicationContext(), "Vai finalizar", Toast.LENGTH_SHORT).show();
        //logica para obter os itens com quantidade e gerar lista
    }

    public class ClickMyButtonFinalizeList implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            finalizeProductList();
        }
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.lista_cupom);
//
//        mTextViewFullName = findViewById(R.id.textView_cupons);
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
//        mFloatingActionButtonAddProduct = findViewById(R.id.fab_add_product);
//        mFloatingActionButtonAddProduct.setOnClickListener(new ClickMyButtonAdd());
//
//        mFloatingActionButtonFinalizeList = findViewById(R.id.fab_finalize_product_list);
//        mFloatingActionButtonFinalizeList.setOnClickListener(new ClickMyButtonFinalizeList());
//
//        mTextViewAddProduct = findViewById(R.id.textView_add_product);
//        mTextViewLogout = findViewById(R.id.textView_logout_product);
//        mTextViewFinalize = findViewById(R.id.textView_finalize_product_list);
//
//        mTextViewTotalValue = findViewById(R.id.textView_total_value);
//
//        mFloatingActionButtonLogout.setVisibility(View.GONE);
//        mFloatingActionButtonAddProduct.setVisibility(View.GONE);
//        mFloatingActionButtonFinalizeList.setVisibility(View.GONE);
//
//        mTextViewLogout.setVisibility(View.GONE);
//        mTextViewAddProduct.setVisibility(View.GONE);
//        mTextViewFinalize.setVisibility(View.GONE);
//
//        isAllFabsVisible = false;
//
//        mFloatingActionButtonAction.shrink(); //estado reduzido inicialmente
//
//        mSharedPreferences = getSharedPreferences("MyAppName" , MODE_PRIVATE);
//        verifyNotLogged();
//    }







/**

 O código fornecido é uma classe de atividade Android chamada "ProductActivity" em um pacote chamado "br.com.profdigital.priceresearch". Parece fazer parte de um aplicativo Android relacionado à pesquisa e gerenciamento de produtos. Aqui está uma análise do código:

 O código importa várias classes e bibliotecas do Android necessárias para a atividade.

 A classe ProductActivity estende a classe AppCompatActivity, que fornece compatibilidade com versões mais antigas do Android.

 A classe contém várias variáveis membros, incluindo TextViews, instâncias de FloatingActionButton, um RecyclerView, um ProductAdapter e uma instância de SharedPreferences.

 O método onCreate é o ponto de entrada para a atividade. Ele define o layout, inicializa os elementos da interface do usuário e registra ouvintes de clique para os botões.

 O método onCreate também chama o método verifyNotLogged, que verifica se o usuário está logado verificando as preferências compartilhadas. Se o usuário não estiver logado, redireciona para a LoginActivity. Se o usuário estiver logado, configura o adaptador do RecyclerView e preenche a lista de produtos.

 O método verifyNotLogged verifica se o usuário está logado verificando a chave "logged" nas preferências compartilhadas. Se o usuário estiver logado, define o nome completo em um TextView e configura o adaptador do RecyclerView. Se o usuário não estiver logado, chama o método showLogin.

 O método showLogin inicia a LoginActivity e finaliza a ProductActivity.

 O método exitAppLogoff limpa as preferências compartilhadas e chama o método showLogin para fazer logout do usuário.

 Existem várias classes internas definidas na classe ProductActivity, incluindo ClickMyButtonLogout, ClickMyButtonAction, ClickMyButtonAdd e ClickMyButtonFinalizeList. Essas classes implementam a interface OnClickListener e lidam com eventos de clique nos botões.

 O método setupRecyclerViewAdapter inicializa o RecyclerView, define seu gerenciador de layout e o associa ao ProductAdapter. Também configura um ouvinte de clique para cada item no RecyclerView.

 O código também inclui uma classe aninhada chamada MySwipeHelper, que estende a classe RecyclerView.ItemDecoration e fornece ações de deslize para os itens do RecyclerView.

 O onItemClickListener lida com eventos de clique nos itens do RecyclerView. Ele inicia a ProductAddEditActivity com os detalhes do produto selecionado.

 O método onActivityResult recebe o resultado da ProductAddEditActivity e realiza as ações apropriadas com base no resultado.

 Em geral, a classe ProductActivity lida com a exibição de dados de produtos, autenticação do usuário e interações do usuário em um aplicativo Android.


 */

/**
 * A classe ProductActivity é responsável por lidar com a tela principal do aplicativo relacionado a produtos. Veja abaixo os comentários de cada método e funcionalidade desta classe.
 *
 * Método onItemClickListener:
 * Objetivo: Implementa um OnClickListener para os itens do RecyclerView.
 * Quando um item é clicado, os detalhes do produto são passados para a ProductAddEditActivity para edição ou visualização.
 *
 * Método setupRecyclerViewAdapter():
 * Objetivo: Configura o RecyclerView para exibir a lista de produtos.
 * Cria e configura um novo adaptador ProductAdapter, atribuindo-o ao RecyclerView.
 *
 * Método verifyNotLogged():
 * Objetivo: Verifica se o usuário está logado ou não.
 * Se o usuário não estiver logado, redireciona para a tela de login (LoginActivity).
 * Se o usuário estiver logado, mostra o nome completo do usuário no TextViewFullName e configura o RecyclerView para exibir a lista de produtos.
 *
 * Método showLogin():
 * Objetivo: Navega para a tela de login (LoginActivity) para autenticar o usuário.
 *
 * Método exitAppLogoff():
 * Objetivo: Faz logout do usuário, limpando as preferências compartilhadas e redirecionando para a tela de login.
 *
 * Classe interna (sub classe) ClickMyButtonLogout:
 * Objetivo: Implementa um OnClickListener para o botão de logout.
 * Chama o método exitAppLogoff() quando o botão de logout é clicado.
 *
 * Método showAddProduct():
 * Objetivo: Navega para a tela de adicionar/editar produtos (ProductAddEditActivity).
 *
 * Classe interna (sub classe) ClickMyButtonAdd:
 * Objetivo: Implementa um OnClickListener para o botão de adicionar produtos.
 * Chama o método showAddProduct() quando o botão de adicionar produtos é clicado.
 *
 * Método finalizeProductList():
 * Objetivo: Finaliza a lista de produtos (não está totalmente implementado).
 *
 * Classe interna (sub classe) ClickMyButtonFinalizeList:
 * Objetivo: Implementa um OnClickListener para o botão de finalizar lista (não está totalmente implementado).
 *
 * Método onCreate(Bundle savedInstanceState):
 * Objetivo: Inicializa a atividade da lista de produtos.
 * Configura a interface do usuário (layouts, botões, etc.).
 * Configura o RecyclerView e o adaptador usando o método setupRecyclerViewAdapter().
 * Verifica se o usuário está logado (verifyNotLogged()).
 * Define o comportamento do botão de logout, adicionar produtos e finalizar lista por meio das classes internas ClickMyButtonLogout, ClickMyButtonAdd e ClickMyButtonFinalizeList.
 * Configura a barra de pesquisa (SearchView) e seus comportamentos.
 *
 * Método onCreateOptionsMenu(Menu menu):
 * Objetivo: Cria o menu de opções na barra de ação (action bar).
 * Infla o layout do menu main_menu usando MenuInflater.
 * Configura a barra de pesquisa (SearchView) e seus comportamentos para filtrar a lista de produtos.
 *
 * Método onOptionsItemSelected(MenuItem item):
 * Objetivo: Responde às ações do menu de opções.
 * Implementa a lógica para as ações de adicionar produtos, fazer logout e excluir todos os produtos.
 *
 * A classe ProductActivity é responsável por exibir a lista de produtos, permitir adicionar ou editar produtos, finalizar a lista e fazer logout. Ela se comunica com outras classes, como ProductAdapter, ProductDao e ProductAddEditActivity, para gerenciar a lista de produtos e permitir a interação do usuário. Além disso, ela utiliza a classe SharedPreferences para verificar o status de login do usuário e apresentar a interface adequada com base nesse status. O usuário pode pesquisar produtos na lista por meio da barra de pesquisa (SearchView).
 */

    private void setupRecyclerViewAdapter() {
    }
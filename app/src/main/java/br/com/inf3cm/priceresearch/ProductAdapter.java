package br.com.inf3cm.priceresearch;

// 3.2

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.pizzaria.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    public static final String TAG = "Product Adapter";

    private View.OnClickListener mOnItemClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    private Context mContext;
    private List<Product> mProductList;


    //    public ProductAdapter(Context context, List<Product> productList) { //nv abaixo para receber o TextView da compra total
    public ProductAdapter(Context context, List<Product> productList, TextView textViewTotalPrice) { //nv2
        //faca com generate constructor

        mContext = context;
        mProductList = productList;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        // widgets do layout card_item_list.xml
        private final ImageView mImageViewProduct;
        private final TextView mTextViewItemProductDescription;  //productName
        private final TextView mTextViewItemProductPrice;


        private ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewItemProductDescription = itemView.findViewById(R.id.textView_item_product_name_rev2);
            mTextViewItemProductPrice = itemView.findViewById(R.id.textView_item_product_price_rev2);

            mImageViewProduct = itemView.findViewById(R.id.image_item_product_list_rev1);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
            // itemView.setOnLongClickListener(mOnLongClickListener);

        }
    }

    @NonNull //  PROCURE NO CODIGO JA FOI CRIADO
    @Override  // esse método carrega um item da lista
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup mViewGroupParent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext); //nv
        View mItemView = mLayoutInflater.inflate(R.layout.card_cupom, mViewGroupParent, false);
        return new ProductViewHolder(mItemView);
    }

    @Override        // vinculo dos objetos da tela com o objeto do java
    public void onBindViewHolder(@NonNull ProductViewHolder mProductViewHolder, int vPosition) {
        Product mProductCurrent = mProductList.get(vPosition);
        mProductViewHolder.mTextViewItemProductDescription.setText(mProductCurrent.getName());

        // MARCOS DIVULGUE PARA OS ALUNOS
        // https://pt.stackoverflow.com/questions/30701/formata%C3%A7%C3%A3o-de-um-double-em-java
        // https://pt.stackoverflow.com/questions/219211/qual-a-forma-correta-de-usar-os-tipos-float-double-e-decimal
        // https://github.com/maniero/SOpt/blob/master/Conceptual.md

//        mProductViewHolder.mTextViewItemProductPrice.setText(String.valueOf(mProductCurrent.getPrice()));
        String mStringPrice = String.format("%.2f", mProductCurrent.getPrice());
        mProductViewHolder.mTextViewItemProductPrice.setText(mStringPrice);


        // AQUI NO onBindViewHolder (ex. ProductViewHolder) - TEMOS resposta de muitos erros

        //mProductViewHolder.mImageViewProduct.setImageDrawable(mContext.getResources().getDrawable(mProductCurrent.getImage()));

        //Glide.with().load().into()    imagem - pesquise
        // implementation no build.gradle (Module)  >> implementation 'com.github.bumptech.glide:glide:4.15.1'
//        Glide.with(mContext).load(mProductCurrent.getImage()).into(mProductViewHolder.mImageViewProduct); //6:44


    }

    @Override
    public int getItemCount() {
        if (mProductList != null) {
            return mProductList.size();
        } else {
            return 0;
        }
    }

    public void setProductList(List<Product> mProducts) {
        mProductList = mProducts;

        notifyDataSetChanged();
    }

    public Product getProductFrom(int vPosition) { //getProductAt

        return mProductList.get(vPosition);
    }

    public void setOnItemClickListener(View.OnClickListener mItemClickListener) {
        mOnItemClickListener = mItemClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener mItemLongClickListener) {
        mOnLongClickListener = mItemLongClickListener;
    }


};


/**

No desenvolvimento Android, um Adapter é uma ponte entre um conjunto de dados e um componente de interface do usuário que pode exibir esses dados, como uma RecyclerView ou uma ListView.

O Adapter é responsável por criar as visualizações individuais dos itens e preenchê-las com os dados. Ele também gerencia a reciclagem das visualizações, isto é, reutiliza as visualizações que saíram da tela quando novos itens entram, para economizar memória e melhorar o desempenho.

No contexto do código fornecido, ProductAdapter é um tipo específico de Adapter que foi projetado para exibir uma lista de produtos em uma RecyclerView. Ele estende RecyclerView.Adapter e define uma classe interna ProductViewHolder que estende RecyclerView.ViewHolder. O ViewHolder é uma classe que contém as referências às visualizações individuais que compõem um único item na lista.

ProductAdapter também implementa a interface Filterable, o que significa que pode fornecer um Filter que pode ser usado para filtrar os dados com base em algum critério. Nesse caso, ele fornece um Filter que pode filtrar a lista de produtos com base no nome do produto.

Além disso, ProductAdapter inclui vários métodos para configurar a funcionalidade de clique nos itens, atualizar a lista de produtos que está exibindo, etc. Em resumo, o ProductAdapter é uma classe de controle central que gerencia a exibição de uma lista de produtos em uma RecyclerView.


Esse é um exemplo de um Adapter de RecyclerView no Android para uma lista de Produtos (Product).

Os Adapters de RecyclerView são usados para fornecer dados à RecyclerView, que atua como uma interface entre o conjunto de dados e a RecyclerView. Em outras palavras, o Adapter ajuda a exibir os dados na RecyclerView.

Os principais componentes desta classe são:

ProductAdapter: Esta é a classe do adapter que estende o RecyclerView.Adapter com uma classe interna ProductViewHolder que estende o RecyclerView.ViewHolder. O ViewHolder fornece referência direta para todas as views que serão usadas para exibir um item da lista.

ProductViewHolder: Esta é a classe interna que representa o ViewHolder para um item de produto. Ela contém as visualizações necessárias para exibir o produto, como descrição, preço, botões para adicionar ou remover quantidade, uma imagem do produto, etc. Ela também tem métodos para atualizar a quantidade total de um produto e para responder aos cliques nos botões de adicionar e remover.

onCreateViewHolder: Este método é chamado quando o RecyclerView precisa de um novo ViewHolder do tipo especificado para representar um item.

onBindViewHolder: Este método é chamado pelo RecyclerView para exibir os dados na posição especificada. Aqui, atualiza-se o conteúdo das views do ViewHolder para refletir o item na posição dada.

getItemCount: Este método retorna o número total de itens na lista de dados mantida neste Adapter.

setProductList: Este método permite atualizar a lista de produtos que o adapter está exibindo.

getProductAt: Este método retorna um produto na posição especificada da lista de produtos.

setOnItemClickListener, setOnLongClickListener: Estes métodos permitem definir listeners de clique e clique longo para os itens.

getFilter: Este método retorna o filtro utilizado para filtrar os produtos com base numa sequência de caracteres.

applyProductFilter: Este é um objeto Filter usado para filtrar a lista de produtos.

Este código faz um bom trabalho na aderência às boas práticas do Android, separando claramente as responsabilidades e facilitando a manutenção. No entanto, há algumas áreas que podem ser melhoradas.

Por exemplo, o código poderia ser simplificado e tornar-se mais eficiente utilizando DiffUtil para lidar com as atualizações dos dados na RecyclerView. Além disso, a lógica de negócio, como cálculo do preço total, idealmente não deveria estar no Adapter, mas em uma classe separada de modelo ou repositório. Além disso, o método onBindViewHolder tem um comentário indicando que uma imagem deve ser carregada com a biblioteca Glide, mas o código para isso está comentado.



 Este código representa uma classe ProductAdapter em Java para Android. Essa classe é usada para fornecer uma visualização e interação com um conjunto de dados em uma RecyclerView. Nesse caso, os dados são objetos Product.

 Aqui estão algumas partes do código que vale a pena notar:

 Inicialização e Configuração: Na função construtora ProductAdapter, o contexto, a lista de produtos e o TextView para exibir o preço total da compra são inicializados.

 ViewHolder: A classe interna ProductViewHolder define o layout de cada item em RecyclerView. Ele possui referências para vários componentes de interface do usuário, como TextViews, Button e ImageView. Ele também possui métodos internos que são disparados quando os botões de adicionar e remover produtos são clicados, atualizando o preço total e a quantidade de produtos.

 Métodos de RecyclerView.Adapter: Esta classe sobrescreve onCreateViewHolder, onBindViewHolder e getItemCount para definir como os dados são apresentados e manipulados na RecyclerView.

 Filtro: A classe implementa a interface Filterable, o que significa que pode fornecer um filtro que pode ser usado para filtrar a lista de produtos com base em critérios definidos. Nesse caso, ele fornece um filtro que pode filtrar a lista de produtos com base no nome do produto.

 Outros Métodos: Existem métodos para atualizar a lista de produtos, recuperar um produto em uma determinada posição e configurar os manipuladores de clique.

 A classe está bem estruturada e a lógica é clara. No entanto, aqui estão algumas sugestões para melhorias:

 A lógica de negócio, como o cálculo do preço total, deve idealmente estar em uma classe separada de negócio ou em um ViewModel, em vez de no Adapter.
 A quantidade de produtos é manipulada no ViewHolder, mas poderia ser melhor colocá-la no próprio objeto do produto.
 Parte do código está comentada, como o uso de Glide para carregar imagens e o método setPriceColor, pode ser uma boa ideia removê-lo se não for necessário, para melhorar a clareza do código.
 O método onBindViewHolder é responsável por associar dados com o ViewHolder. No entanto, ele também tenta modificar a aparência do TextView com base na avaliação do produto, o que é uma responsabilidade de visualização e poderia ser movido para o ViewHolder ou para um método separado.
 A criação de novas instâncias de classes anônimas para cada ViewHolder (ex. new View.OnClickListener()) pode não ser eficiente se a lista for muito grande, já que cada instância possui uma referência implícita à classe externa e pode levar a vazamentos de memória se não for manuseada corretamente. Uma alternativa seria implementar essas interfaces na própria classe ViewHolder e reutilizá-las.





 */
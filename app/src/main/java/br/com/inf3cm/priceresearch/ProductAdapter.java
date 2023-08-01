package br.com.inf3cm.priceresearch;

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

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    public static final String TAG = "Class Product Adapter";

    // criar interface para clique e clique longo
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    private Context mContext;

    // criar os objetos de listagem = select
    private List<Product> mProductList;
    private List<Product> mProductListFull;

    private TextView mTextViewTotalPrice;

    // gerar o construtor dessa classe para instanciar (new) em outra classe


    public ProductAdapter(Context mContext, List<Product> mProductList, TextView mTextViewTotalPrice) {
        this.mContext = mContext;
        this.mProductList = mProductList;
        this.mTextViewTotalPrice = mTextViewTotalPrice;
    }

    // a regra de negocio abaixo está em local errado
    // isso NAO DEVE SER FEITO AQUI, porém, para fins didaticos
    // irei fazer para explicar a metodologia de programacao
    // conhecida como codigo limpo (CLEAN CODE)
    public String setPriceColor(double vStarRating){
        if(vStarRating < 3){
            return "#FF0000";
        } else {
            return "#00FF00";
        }

    }





    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View mItemView = mLayoutInflater.inflate(R.layout.card_item_product_list , parent , false);

        return new ProductViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        // vincular = ligar os obejetos da tela com dados e os objetos em java
        Product mProductCurrent = mProductList.get(position);  //current = item utilizado
        holder.mTextViewItemProductName.setText(mProductCurrent.getName());
        holder.mTextViewItemProductPrice.setText(  "R$"  +    mProductCurrent.getPrice());
        // precisa formatar corretmente o preco
        holder.mRatingBarItemProductRatingStar.setRating(mProductCurrent.getRating());
        // trocar a cor do preco
        holder.mTextViewItemProductPrice.setTextColor(Color.parseColor(setPriceColor(mProductCurrent.getRating())));
        holder.mButtonProductQuantity.setText("" + mProductCurrent.getUnit());



    }

    @Override
    public int getItemCount() {
        return 0;
    } //AQUI MARCOS

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        // definir os elementos da tela/layout CARD_ITEM_PRODUCT_LIST    CardView
        private final ImageView mImageViewItemProduct;
        private final TextView mTextViewItemProductName;
        private final TextView mTextViewItemProductPrice;
        private final RatingBar mRatingBarItemProductRatingStar;
        private final Button mButtonProductRemove;
        private final Button mButtonProductQuantity;
        private final Button mButtonProductAdd;

        // variaveis para controlar na memória a quantidade de produto e total da compra
        double vTotalPrice = 0.0;
        int vQuantity = 0;

        // regra de negocio - totalizar o valor dos itens que vou comprar
        // e mostrar para o utilizador do app
        private void showTotalPrice(){
            vTotalPrice = 0.0;
            // repeticao
            for( int i=0 ; i <= mProductList.size()-1 ; i++  ) {
                // soma dos valores
                vTotalPrice = vTotalPrice + mProductList.get(i).getPrice() * mProductList.get(i).getUnit();
            }
            mTextViewTotalPrice.setText("R$ "  + vTotalPrice  );
            // precisa formatar para moeda = currency



        }



        // implementar o escutador/ouvinte/listener do clique no botao de adicionar a quant. de produto
        // para isso criar um classe
        public class ClickMyButtonAdd implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                vQuantity = mProductList.get(getAdapterPosition()).getUnit() + 1;
                mButtonProductQuantity.setText( ""  + vQuantity  );
                showTotalPrice();
            }
        }
        public class ClickMyButtonRemove implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                vQuantity = mProductList.get(getAdapterPosition()).getUnit() - 1;
                if(vQuantity < 0) {
                    vQuantity=0;
                }
                mButtonProductQuantity.setText( ""  + vQuantity  );
                showTotalPrice();
            }
        }



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewItemProductName = itemView.findViewById(R.id.textView_item_product_name_rev2);
            mTextViewItemProductPrice = itemView.findViewById(R.id.textView_item_product_price_rev2);
            mRatingBarItemProductRatingStar = itemView.findViewById(R.id.ratingBar_item_product_price_perception_rev2);
            mImageViewItemProduct = itemView.findViewById(R.id.image_item_product_list_rev2);

            mButtonProductAdd = itemView.findViewById(R.id.button_product_add_rev2);
            mButtonProductRemove = itemView.findViewById(R.id.button_product_remove_rev2);
            mButtonProductQuantity = itemView.findViewById(R.id.button_product_quantity_rev2);

            //definir o escutador/ouvinte/listener do clique nos botoes
            mButtonProductAdd.setOnClickListener(new ClickMyButtonAdd());
            mButtonProductRemove.setOnClickListener(new ClickMyButtonRemove());

            // definir o escutador/ouvinte/listener do clique no item = LAYTOUT DO CARD
            itemView.setOnClickListener(mOnClickListener);
            itemView.setOnLongClickListener(mOnLongClickListener);

        }
    }
}

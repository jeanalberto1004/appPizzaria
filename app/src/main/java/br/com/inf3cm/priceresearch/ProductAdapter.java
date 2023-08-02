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

import com.google.android.material.transition.Hold;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
        String mStringPrice = String.format("% .2f" , mProductCurrent.getPrice());
        holder.mTextViewItemProductPrice.setText(  mStringPrice);
        holder.mTextViewItemProductPrice.setTextColor( Color.parseColor(setPriceColor(mProductCurrent.getRating())));
        holder.mTextViewItemProductPrice.setText(  "R$"  +    mProductCurrent.getPrice());
        // precisa formatar corretmente o preco
        holder.mRatingBarItemProductRatingStar.setRating(mProductCurrent.getRating());
        // trocar a cor do preco
        holder.mTextViewItemProductPrice.setTextColor(Color.parseColor(setPriceColor(mProductCurrent.getRating())));
        holder.mButtonProductQuantity.setText("" + mProductCurrent.getUnit());
        //Glide.with().load().into();imagem


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
            NumberFormat mNumberFormat = NumberFormat.getCurrencyInstance(new Locale("pt" , "BR"));
            String mStringValue = mNumberFormat.format(vTotalPrice);
            mTextViewTotalPrice.setText(mStringValue);


        }



        // implementar o escutador/ouvinte/listener do clique no botao de adicionar a quant. de produto
        // para isso criar um classe
        public class ClickMyButtonAdd implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                vQuantity = mProductList.get(getAdapterPosition()).getUnit() + 1;
                if(vQuantity >=10){
                    mButtonProductQuantity.setTextSize(12f);
                }else{
                    mButtonProductQuantity.setTextSize(18f);
                }


                mButtonProductQuantity.setText( ""  + vQuantity  );
                mProductList.get(getAdapterPosition()).setUnit(vQuantity);
                showTotalPrice(); //ira mudar
            }
        }
        public class ClickMyButtonRemove implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                if(vQuantity > 0) {
                    vQuantity = mProductList.get(getAdapterPosition()).getUnit() - 1;
                if(vQuantity >= 10 ){
                  mButtonProductQuantity.setTextSize(12f);
                }else{
                    mButtonProductQuantity.setTextSize(18f);
                    }
                if (vQuantity <0){ //bug de correção
                    vQuantity = 0;
                }
                    mButtonProductQuantity.setText( ""  + vQuantity  );
                    mProductList.get(getAdapterPosition()).setUnit(vQuantity);
                showTotalPrice();//ira mudar
                }

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

            itemView.setTag(this);
            // definir o escutador/ouvinte/listener do clique no item = LAYTOUT DO CARD
            itemView.setOnClickListener(mOnClickListener);
            itemView.setOnLongClickListener(mOnLongClickListener);

        }
    }
}

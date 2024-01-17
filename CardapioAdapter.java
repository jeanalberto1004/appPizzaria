package br.com.inf3cm.priceresearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.tcc.pizzaria.R;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.cardapioViewHolder> {

    public static final String TAG = "cardapio adapter";

    private Context mContext;
    private List<Cardapio> mcardapioList;

    public CardapioAdapter(Context context, List<Cardapio> cardapioList) {
        mContext = context;
        mcardapioList = cardapioList;
    }



    public class cardapioViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewprecoCardapio;
        private final TextView mTextViewnomeCardapio;
        private final TextView mTextViewdescricaoCardapio;


        private cardapioViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewnomeCardapio = itemView.findViewById(R.id.nome_cardapio);
            mTextViewdescricaoCardapio = itemView.findViewById(R.id.descricao_cardapio);
            mTextViewprecoCardapio = itemView.findViewById(R.id.preco_cardapio);
            itemView.setTag(this);

        }

    }


    @NonNull
    @Override
    public cardapioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View mItemView = mInflater.inflate(R.layout.card_cardapio, parent, false);
        return new cardapioViewHolder(mItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull cardapioViewHolder holder, int position) {
        Cardapio mcardapioCurrent = mcardapioList.get(position);

        holder.mTextViewnomeCardapio.setText(mcardapioCurrent.getNome());
        holder.mTextViewdescricaoCardapio.setText(mcardapioCurrent.getDescricao());
        holder.mTextViewprecoCardapio.setText("" + mcardapioCurrent.getPreco());

    }


    @Override
    public int getItemCount() {
        if (mcardapioList != null) {
            return mcardapioList.size();
        } else {
            return 0;
        }
    }

    public void setCardapioList(List<Cardapio> mcardapios) {
        mcardapioList = mcardapios;
        notifyDataSetChanged();
    }

    public Cardapio getcardapioAt(int vPosition) {
        return mcardapioList.get(vPosition);
    }

}

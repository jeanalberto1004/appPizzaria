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

public class CupomAdapter extends RecyclerView.Adapter<CupomAdapter.CupomViewHolder> {

    public static final String TAG = "cupom adapter";

    private Context mContext;
    private List<Cupom> mCupomList;


    public CupomAdapter(Context context,  List<Cupom> cupomList) {
        mContext = context;
        mCupomList = cupomList;
    }

    public class CupomViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewprecoCupom;
        private final TextView mTextViewnomeCupom;
        private final TextView mTextViewnumerodoCupom;
        private final TextView mTextViewvalidadeCupom;

        private CupomViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewnomeCupom = itemView.findViewById(R.id.nome_cupom);
            mTextViewnumerodoCupom = itemView.findViewById(R.id.numero_cupom);
            mTextViewprecoCupom = itemView.findViewById(R.id.preco_cupom);
            mTextViewvalidadeCupom = itemView.findViewById(R.id.data_validade2);
            itemView.setTag(this);

        }

    }


    @NonNull
    @Override
    public CupomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View mItemView = mInflater.inflate(R.layout.card_cupom, parent, false);
        return new CupomViewHolder(mItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CupomViewHolder holder, int position) {
        Cupom mCupomCurrent = mCupomList.get(position);

        holder.mTextViewnomeCupom.setText(mCupomCurrent.getNome());
        holder.mTextViewnumerodoCupom.setText(mCupomCurrent.getNumero());
        holder.mTextViewprecoCupom.setText("" + mCupomCurrent.getPreco());
        holder.mTextViewvalidadeCupom.setText(mCupomCurrent.getStatus());

    }


    @Override
    public int getItemCount() {
        if (mCupomList != null) {
            return mCupomList.size();
        } else {
            return 0;
        }
    }

    public void setCupomList(List<Cupom> mCupoms) {
        mCupomList = mCupoms;
        notifyDataSetChanged();
    }

    public Cupom getCupomAt(int vPosition) {
        return mCupomList.get(vPosition);
    }

}

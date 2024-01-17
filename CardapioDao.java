package br.com.inf3cm.priceresearch;

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CardapioDao {



// 2

        private static final String TAG = "Cardapio Dao";


        public static List<Cardapio> listAllCardapios(Context mContext){

            // Listagem de produtos
            List<Cardapio> mProductList = null;
            String mSql;

            try{
                mSql = "Select id, nome,descricao, preco, status from produto order by nome ASC";
                PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
                ResultSet mResultSet = mPreparedStatement.executeQuery();
                mProductList = new ArrayList<Cardapio>();
                while(mResultSet.next()){
                    mProductList.add(new Cardapio(
                            mResultSet.getLong(1),
                            mResultSet.getString(2),
                            mResultSet.getString(3),
                            mResultSet.getString(4),
                            mResultSet.getString(5)


                    ));
                }

            }catch(Exception mException){
                Log.e(TAG, mException.getMessage());
                mException.printStackTrace();
            }


            return mProductList;

        }


    }



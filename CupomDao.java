package br.com.inf3cm.priceresearch;

import android.content.Context;
import android.util.Log;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CupomDao {

// 2

    private static final String TAG = "Cupom Dao";


    public static List<Cupom> listAllCupoms(Context mContext){

        // Listagem de produtos
        List<Cupom> mProductList = null;
        String mSql;

        try{
            mSql = "Select id, nome,numero, preco, status from Cupom order by nome ASC";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            mProductList = new ArrayList<Cupom>();
            while(mResultSet.next()){
                mProductList.add(new Cupom(
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

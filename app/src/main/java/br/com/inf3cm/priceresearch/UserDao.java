package br.com.inf3cm.priceresearch;


// 2

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String TAG = "UserDao";

    public static int insertUser(User mUser, Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "INSERT Users (Telefone, Cep, Senha, Email, Nome, Logradrouro, Cidade, Bairro, NumeroResid, Complemento, Status ) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ? )";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getmTelefone());
            mPreparedStatement.setString(2, mUser.getmCep());
            mPreparedStatement.setString(3, mUser.getmSenha());
            mPreparedStatement.setString(4, mUser.getmEmail());
            mPreparedStatement.setString(5, mUser.getmNome());
            mPreparedStatement.setString(6, mUser.getmLogradrouro());
            mPreparedStatement.setString(7, mUser.getmCidade());
            mPreparedStatement.setString(8, mUser.getmBairro());
//            mPreparedStatement.setLong(9, mUser.getmNumeroResid());
            mPreparedStatement.setString(10, mUser.getmComplemento());
            mPreparedStatement.setString(11, mUser.getmStatus());

            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1

        }catch (Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;

    }
    public static int updateUser(User mUser, Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "UPDATE users SET telefone=?, cep=?, senha=?, email=?, nome=?, logradouro=?, cidade=?, bairro=?, numeroresid=? , complemento=? , status=?, WHERE id=?";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setString(1, mUser.getmTelefone());
            mPreparedStatement.setString(2, mUser.getmCep());
            mPreparedStatement.setString(3, mUser.getmSenha());
            mPreparedStatement.setString(4, mUser.getmEmail());
            mPreparedStatement.setString(5, mUser.getmNome());
            mPreparedStatement.setString(6, mUser.getmLogradrouro());
            mPreparedStatement.setString(7, mUser.getmCidade());
            mPreparedStatement.setString(8, mUser.getmBairro());
//            mPreparedStatement.setInt(9, mUser.getmNumeroResid());
            mPreparedStatement.setString(10, mUser.getmComplemento());
            mPreparedStatement.setString(11, mUser.getmStatus());
            mPreparedStatement.setLong(12, mUser.getmId());
            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1

        }catch (Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }
        return vResponse;
    }

    public static int deleteUser(User mUsers, Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "Delete from users where id=?";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setInt(1, mUsers.getmId());
            vResponse = mPreparedStatement.executeUpdate();

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;

    }
    public static String authenticateUser(User mUser, Context mContext){
        String mResponse = "";
        String mSql;
        try{

            //https://alvinalexander.com/blog/post/jdbc/jdbc-preparedstatement-select-like/

            mSql = "SELECT id, nome, email, senha FROM Login WHERE senha like ? and email like ?";
            //mSql = "SELECT id, fullName, email, password FROM users WHERE password = ? and email = ?";

            //https://pt.stackoverflow.com/questions/369624/statement-ou-preparedstatement-por-qual-motivo-evitar-usar-o-statement

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getmSenha());
            mPreparedStatement.setString(2, mUser.getmEmail());
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            while(mResultSet.next()){
                mResponse = mResultSet.getString(2); // veja o objeto 'mSql'
            }

        } catch(Exception mException){
            mResponse = "Exception";
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return mResponse;
    }


}
package br.com.inf3cm.priceresearch;

// 2

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static final String TAG = "CRUD table User";

    public static int insertUser(User mUser , Context mContext){
        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "INSERT Users ( fullname , username , email , password , createdate,  apikey , reset_password_otp , reset_password_created_at ) VALUES ( ? , ? , ? , ? ,?,?,?,?)";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getFullName());
            mPreparedStatement.setString(2, mUser.getUserName());
            mPreparedStatement.setString(3, mUser.getEmail());
            mPreparedStatement.setString(4, mUser.getPassword());
            mPreparedStatement.setLong(5, mUser.getCreateDate());
            mPreparedStatement.setString(6, mUser.getApiKey());
            mPreparedStatement.setString(7, mUser.getmResetPasswordOtp());
            mPreparedStatement.setLong(8, mUser.getmResetPasswordCreatedAt());



            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e( TAG , e.getMessage());
        }


        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
    }

    public static int updateUser(User mUser , Context mContext){
        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "UPDATE Users SET fullname=? , username=? , email=? , password=? , createdata=? , apikey=? , reset_password_otp=? , reset_password_created_at=?  WHERE id=?";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mUser.getFullName());
            mPreparedStatement.setString(2, mUser.getUserName());
            mPreparedStatement.setString(3, mUser.getEmail());
            mPreparedStatement.setString(4, mUser.getPassword());
            mPreparedStatement.setLong(5, mUser.getCreateDate());
            mPreparedStatement.setString(6, mUser.getApiKey());
            mPreparedStatement.setString(7, mUser.getmResetPasswordOtp());
            mPreparedStatement.setLong(8, mUser.getmResetPasswordCreatedAt());


            mPreparedStatement.setInt(5, mUser.getId());

            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e( TAG , e.getMessage());
        }


        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
    }

    public static int deleteUser(User mUser , Context mContext){
        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "DELETE FROM Users WHERE id=?";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);


            mPreparedStatement.setInt(1, mUser.getId());

            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e( TAG , e.getMessage());
        }


        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
    }

    public static int deleteAllUser( Context mContext){
        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
        String mSql;
        try {
            mSql = "DELETE FROM Users ";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);



            vResponse = mPreparedStatement.executeUpdate();

        } catch (Exception e) {
            Log.e( TAG , e.getMessage());
        }


        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
    }

    public  static  List<User> ListAllUsers (Context mContext){
        List<User> mUserList = null;
        String mSql;
        try {
            mSql = "SELECT id , fullname , username , password , email , createdate , apikey , reset_password_otp , reset_password_created_at FROM users ORDER BY name ASC";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            mUserList = new ArrayList<User>();
            while (mResultSet.next()){
                mUserList.add(new User(
                        mResultSet.getInt(1),
                        mResultSet.getString(2),
                        mResultSet.getString(3),
                        mResultSet.getString(4),
                        mResultSet.getString(5),
                        mResultSet.getLong(6),
                        mResultSet.getString(7),
                        mResultSet.getString(8),
                        mResultSet.getLong(9)

                ));
            }
        } catch (Exception e) {
            Log.e(TAG , e.getMessage());
        }
        return mUserList;
    }

    public static List<User> ListAllUserByStatus( int vStatus , Context mContext) {
        List<User> mUserList = null;
        String mSql;
        try{
            mSql= "SELECT id, name, price, rating, status, image, amountConsumption, consumptionCycle FROM users WHERE status =" + vStatus + "ORDER BY name ASC";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            mUserList = new ArrayList<User>();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return null;
    }


    public static String authenticateUser(  User mUser ,  Context mContext){

        String vResponse = "";
        String mSql = "SELECT id , fullname , username , email , password FROM Users WHERE password LIKE ? AND email LIKE ?";
        try{
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1 , mUser.getPassword());
            mPreparedStatement.setString(2 , mUser.getEmail());

            ResultSet mResultSet = mPreparedStatement.executeQuery();


            while(mResultSet.next()){
                vResponse = mResultSet.getString(2); //fullname
            }

        } catch (Exception e){
            Log.e(TAG , e.getMessage());
            e.printStackTrace();
        }

        return vResponse;
    }



}









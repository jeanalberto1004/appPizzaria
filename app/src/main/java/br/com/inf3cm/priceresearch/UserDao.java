//package br.com.inf3cm.priceresearch;
//
//// 2
//
//import android.content.Context;
//import android.util.Log;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDao {
//
//    public static final String TAG = "CRUD table User";
//
//    public static int insertUser(User mUser , Context mContext){
//        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
//        String mSql;
//        try {
//            mSql = "INSERT Users ( fullname , username , email , password ) VALUES ( ? , ? , ? , ? )";
//
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1, mUser.getFullName());
//            mPreparedStatement.setString(2, mUser.getUserName());
//            mPreparedStatement.setString(3, mUser.getEmail());
//            mPreparedStatement.setString(4, mUser.getPassword());
//
//
//            vResponse = mPreparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            Log.e( TAG , e.getMessage());
//        }
//
//
//        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
//    }
//
//    public static int updateUser(User mUser , Context mContext){
//        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
//        String mSql;
//        try {
//            mSql = "UPDATE Users SET fullname=? , username=? , email=? , password=? WHERE id=?";
//
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1, mUser.getFullName());
//            mPreparedStatement.setString(2, mUser.getUserName());
//            mPreparedStatement.setString(3, mUser.getEmail());
//            mPreparedStatement.setString(4, mUser.getPassword());
//
//            mPreparedStatement.setInt(5, mUser.getId());
//
//            vResponse = mPreparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            Log.e( TAG , e.getMessage());
//        }
//
//
//        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
//    }
//
//    public static int deleteUser(User mUser , Context mContext){
//        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
//        String mSql;
//        try {
//            mSql = "DELETE FROM Users WHERE id=?";
//
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//
//            mPreparedStatement.setInt(1, mUser.getId());
//
//            vResponse = mPreparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            Log.e( TAG , e.getMessage());
//        }
//
//
//        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
//    }
//
//    public static int deleteAllUser( Context mContext){
//        int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
//        String mSql;
//        try {
//            mSql = "DELETE FROM Users ";
//
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//
//
//            vResponse = mPreparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            Log.e( TAG , e.getMessage());
//        }
//
//
//        return vResponse; // 0 nao fez insert     1 fez insert com sucesso
//    }
//
//    public static String authenticateUser(  User mUser ,  Context mContext){
//
//        String vResponse = "";
//        String mSql = "SELECT id , fullname , username , email , password FROM Users WHERE password LIKE ? AND email LIKE ?";
//        try{
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1 , mUser.getPassword());
//            mPreparedStatement.setString(2 , mUser.getEmail());
//
//            ResultSet mResultSet = mPreparedStatement.executeQuery();
//
//
//            while(mResultSet.next()){
//               vResponse = mResultSet.getString(2); //fullname
//            }
//
//        } catch (Exception e){
//            Log.e(TAG , e.getMessage());
//            e.printStackTrace();
//        }
//
//        return vResponse;
//    }
//
//
//
//}
//
//
//
//
//
//
//
//
//

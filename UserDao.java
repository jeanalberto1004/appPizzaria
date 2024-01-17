package br.com.inf3cm.priceresearch;


// 2

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    private static final String TAG = "UserDao";
    public static int insertUser(User mUser, Context mContext) {
        int vResponse = 0;
        String mSql;

        try {
            mSql = "INSERT INTO login (email , senha , status) values ( ? , ? , 1) ";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setString(1, mUser.getmEmail());
            mPreparedStatement.setString(2, mUser.getmSenha());

            // Execute the first INSERT statement
            vResponse = mPreparedStatement.executeUpdate();

            // Close the PreparedStatement
            mPreparedStatement.close(); // Close it here

            // Create a new PreparedStatement for the SELECT query
            mSql = "Select max(id) from login";
            PreparedStatement selectStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            ResultSet mResultSet = selectStatement.executeQuery(); // Execute the SELECT query

            if (mResultSet.next()) {
                int mIdLogin = mResultSet.getInt(1);

                // Now, create a new PreparedStatement for the second INSERT statement
                mSql = "INSERT INTO CLIENTE (BAIRRO, CEP, CIDADE, COMPLEMENTO, " +
                        "LOGRADOURO, NOME, NUMRESID, STATUS, " +
                        "TELEFONE, LOGIN_ID) VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, 1, ?, ?)";

                PreparedStatement secondInsertStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
                secondInsertStatement.setString(1, mUser.getmBairro());
                secondInsertStatement.setString(2, mUser.getmCep());
                secondInsertStatement.setString(3, mUser.getmCidade());
                secondInsertStatement.setString(4, mUser.getmComplemento());
                secondInsertStatement.setString(5, mUser.getmLogradrouro());
                secondInsertStatement.setString(6, mUser.getmNome());
                secondInsertStatement.setString(7, mUser.getmNumeroResid());
                secondInsertStatement.setString(8, mUser.getmTelefone());
                secondInsertStatement.setInt(9, mIdLogin);

                vResponse = secondInsertStatement.executeUpdate(); // Execute the second INSERT statement

                // Close the second PreparedStatement
                secondInsertStatement.close();
            }

            // Close the ResultSet and the selectStatement
            mResultSet.close();
            selectStatement.close();

        } catch (Exception mException) {
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;
    }

//    public static int insertUser(User mUser, Context mContext){
//
//        int vResponse = 0;
//        String mSql;
//
//     try{
//
//            mSql = "INSERT INTO login (email , senha , status) values ( ? , ? , 1) ";
//
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            mPreparedStatement.setString(1, mUser.getmEmail());
//            mPreparedStatement.setString(2, mUser.getmSenha());
//         PreparedStatement mPreparedStatemen = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//            mSql = "Select max(id) from login";
//            ResultSet mResultSet;
//            mResultSet = mPreparedStatemen.executeQuery(mSql);
//            int mIdLogin = mResultSet.getInt("id");
//
//            mSql = " insert into CLIENTE (BAIRRO, CEP, CIDADE, COMPLEMENTO,  " +
//                    "LOGRADOURO, NOME, NUMRESID, STATUS, " +
//                    "TELEFONE, LOGIN_ID) VALUES " +
//                    "VALUES (?, ?, ?,?, ?, ?,?, 1,?,?)";
//
//            mPreparedStatement.setString(1, mUser.getmTelefone());
//            mPreparedStatement.setString(2, mUser.getmCep());
//            mPreparedStatement.setString(3, mUser.getmNome());
//            mPreparedStatement.setString(4, mUser.getmLogradrouro());
//            mPreparedStatement.setString(5, mUser.getmCidade());
//            mPreparedStatement.setString(6, mUser.getmBairro());
//            mPreparedStatement.setString(7, mUser.getmNumeroResid());
//            mPreparedStatement.setString(8, mUser.getmComplemento());
//            mPreparedStatement.setInt(9, mIdLogin);
//
//            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1
//
//        }catch (Exception mException){
//            Log.e(TAG, mException.getMessage());
//            mException.printStackTrace();
//        }
//
//        return vResponse;
//
//    }
//
//    public static int updateUser(User mUser, Context mContext){
//
//        int vResponse = 0;
//        String mSql;
//
//        try{
//            mSql = "UPDATE users SET telefone=?, cep=?, senha=?, email=?, nome=?, logradouro=?, cidade=?, bairro=?, numeroresid=? , complemento=? , WHERE id=?";
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//            mPreparedStatement.setString(1, mUser.getmTelefone());
//            mPreparedStatement.setString(2, mUser.getmCep());
//            mPreparedStatement.setString(3, mUser.getmSenha());
//            mPreparedStatement.setString(4, mUser.getmEmail());
//            mPreparedStatement.setString(5, mUser.getmNome());
//            mPreparedStatement.setString(6, mUser.getmLogradrouro());
//            mPreparedStatement.setString(7, mUser.getmCidade());
//            mPreparedStatement.setString(8, mUser.getmBairro());
//            mPreparedStatement.setString(9, mUser.getmNumeroResid());
//            mPreparedStatement.setString(10, mUser.getmComplemento());
//            mPreparedStatement.setLong(11, mUser.getmId());
//            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1
//
//        }catch (Exception mException){
//            Log.e(TAG, mException.getMessage());
//            mException.printStackTrace();
//        }
//        return vResponse;
//    }
//
//    public static int deleteUser(User mUsers, Context mContext){
//
//        int vResponse = 0;
//        String mSql;
//
//        try{
//            mSql = "Delete from users where id=?";
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//            mPreparedStatement.setLong(1, mUsers.getmId());
//            vResponse = mPreparedStatement.executeUpdate();
//
//        }catch(Exception mException){
//            Log.e(TAG, mException.getMessage());
//            mException.printStackTrace();
//        }
//
//        return vResponse;
//
//    }
    public static String authenticateUser(User mUser, Context mContext){
        String mResponse = "";
        String mSql;
        try{

            //https://alvinalexander.com/blog/post/jdbc/jdbc-preparedstatement-select-like/

            mSql = "SELECT id, email, senha FROM Login WHERE senha like ? and email like ?";
            //mSql = "SELECT id, fullName, email, password FROM users WHERE password = ? and email = ?";

//            mSql = "select cliente.id, nome, login.email, login.senha from cliente inner join login on cliente.id = login.id where login.senha like ? and login.email like ?";

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
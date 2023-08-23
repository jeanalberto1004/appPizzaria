package br.com.inf3cm.priceresearch;

// 2

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

     public static final String TAG = "CRUD table Product";

     public static int insertProduct(Product mProduct , Context mContext){
         int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
         String mSql;
         try {
             mSql = "INSERT INTRO products (name , price , status , image ) VALUES ( ? , ? , ? , ?  )";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

             mPreparedStatement.setString(1, mProduct.getName());
             mPreparedStatement.setDouble(2, mProduct.getPrice());
             mPreparedStatement.setInt(3, mProduct.getStatus());
             mPreparedStatement.setLong(4, mProduct.getImage());

             vResponse = mPreparedStatement.executeUpdate();

         } catch (Exception e) {
             Log.e( TAG , e.getMessage());
         }


         return vResponse; // 0 nao fez insert     1 fez insert com sucesso
     }

     public static int updateProduct(Product mProduct , Context mContext){
         int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
         String mSql;
         try {
             mSql = "UPDATE products SET name=? , price=?  , status=? , image=? , WHERE id=?";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

             mPreparedStatement.setString(1, mProduct.getName());
             mPreparedStatement.setDouble(2, mProduct.getPrice());
             mPreparedStatement.setInt(3, mProduct.getStatus());
             mPreparedStatement.setLong(4, mProduct.getImage());
             mPreparedStatement.setInt(5, mProduct.getId());

             vResponse = mPreparedStatement.executeUpdate();

         } catch (Exception e) {
             Log.e( TAG , e.getMessage());
         }


         return vResponse; // 0 nao fez insert     1 fez insert com sucesso
     }

     public static int deleteProduct(Product mProduct , Context mContext){
         int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
         String mSql;
         try {
             mSql = "DELETE FROM products WHERE id=?";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);


             mPreparedStatement.setInt(1, mProduct.getId());

             vResponse = mPreparedStatement.executeUpdate();

         } catch (Exception e) {
             Log.e( TAG , e.getMessage());
         }


         return vResponse; // 0 nao fez insert     1 fez insert com sucesso
     }

     public static int deleteAllProduct( Context mContext){
         int vResponse = 0; // variavel de resposta com valor 0 = erro ao inserir
         String mSql;
         try {
             mSql = "DELETE FROM products ";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);



             vResponse = mPreparedStatement.executeUpdate();

         } catch (Exception e) {
             Log.e( TAG , e.getMessage());
         }


         return vResponse; // 0 nao fez insert     1 fez insert com sucesso
     }

     public static List<Product> listAllProducts(Context mContext){
         // objeto para representar a lista
         List<Product> mProductList = null;
         String mSql;
         try{
           mSql  = "SELECT id , name , price , status , image  FROM products ORDER BY name ASC";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             // objeto para receber o resultado do conjunto de dados que foi selecionado
             // esse objeto tem o nome de RESULTSET (em outras lingugens de programacao DATASET)
             ResultSet mResultSet = mPreparedStatement.executeQuery();
             // esse conjuto está em memoria. Preciso preparar para exibir na tela essa listagem
             mProductList = new ArrayList<Product>(); // array = dinamica = vai mudar
             while(mResultSet.next()){
                 mProductList.add(new Product(
                         mResultSet.getInt(1),
                         mResultSet.getString(2),
                         mResultSet.getDouble(3),
                         mResultSet.getInt(4),
                         mResultSet.getLong(5)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

     public static List<Product> listAllProductsByStatus( int vStatus ,  Context mContext){
         // objeto para representar a lista
         List<Product> mProductList = null;
         String mSql = "SELECT id , name , price , status , image  FROM products  WHERE status=" + vStatus + "  ORDER BY name";
         try{
             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             // objeto para receber o resultado do conjunto de dados que foi selecionado
             // esse objeto tem o nome de RESULTSET (em outras lingugens de programacao DATASET)
             ResultSet mResultSet = mPreparedStatement.executeQuery();
             // esse conjuto está em memoria. Preciso preparar para exibir na tela essa listagem
             mProductList = new ArrayList<Product>(); // array = dinamica = vai mudar
             while(mResultSet.next()){
                 mProductList.add(new Product(
                         mResultSet.getInt(1),
                         mResultSet.getString(2),
                         mResultSet.getDouble(3),
                         mResultSet.getInt(4),
                         mResultSet.getLong(5)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

     public static List<Product> listAllProductsByPrice( double vPrice ,  Context mContext){
         // objeto para representar a lista
         List<Product> mProductList = null;
         String mSql = "SELECT id , name , price , status , image  FROM products  WHERE price=" + vPrice + "  ORDER BY name";
         try{
             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             // objeto para receber o resultado do conjunto de dados que foi selecionado
             // esse objeto tem o nome de RESULTSET (em outras lingugens de programacao DATASET)
             ResultSet mResultSet = mPreparedStatement.executeQuery();
             // esse conjuto está em memoria. Preciso preparar para exibir na tela essa listagem
             mProductList = new ArrayList<Product>(); // array = dinamica = vai mudar
             while(mResultSet.next()){
                 mProductList.add(new Product(
                         mResultSet.getInt(1),
                         mResultSet.getString(2),
                         mResultSet.getDouble(3),
                         mResultSet.getInt(4),
                         mResultSet.getLong(5)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

     public static List<Product> searchProductsByName( String mName ,  Context mContext){
         // objeto para representar a lista
         List<Product> mProductList = null;
         String mSql = "SELECT id , name , price , status , image  FROM products  WHERE name LIKE '%" + mName + "%'  ORDER BY name";
         try{
             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             // objeto para receber o resultado do conjunto de dados que foi selecionado
             // esse objeto tem o nome de RESULTSET (em outras lingugens de programacao DATASET)
             ResultSet mResultSet = mPreparedStatement.executeQuery();
             // esse conjuto está em memoria. Preciso preparar para exibir na tela essa listagem
             mProductList = new ArrayList<Product>(); // array = dinamica = vai mudar
             while(mResultSet.next()){
                 mProductList.add(new Product(
                         mResultSet.getInt(1),
                         mResultSet.getString(2),
                         mResultSet.getDouble(3),
                         mResultSet.getInt(4),
                         mResultSet.getLong(5)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

}










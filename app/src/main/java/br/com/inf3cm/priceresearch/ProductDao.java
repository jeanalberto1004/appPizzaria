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
             mSql = "INSERT products (name , price , rating , status , image , amountConsumption , consumptionCycle) VALUES ( ? , ? , ? , ? , ? , ? , ? )";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

             mPreparedStatement.setString(1, mProduct.getName());
             mPreparedStatement.setDouble(2, mProduct.getPrice());
             mPreparedStatement.setFloat(3, mProduct.getRating());
             mPreparedStatement.setInt(4, mProduct.getStatus());
             mPreparedStatement.setInt(5, mProduct.getImage());
             mPreparedStatement.setInt(6, mProduct.getAmountConsumption());
             mPreparedStatement.setInt(7, mProduct.getConsumptionCycle());

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
             mSql = "UPDATE products SET name=? , price=? , rating=? , status=? , image=? , amountConsumption=? , consumptionCycle=? WHERE id=?";

             PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

             mPreparedStatement.setString(1, mProduct.getName());
             mPreparedStatement.setDouble(2, mProduct.getPrice());
             mPreparedStatement.setFloat(3, mProduct.getRating());
             mPreparedStatement.setInt(4, mProduct.getStatus());
             mPreparedStatement.setInt(5, mProduct.getImage());
             mPreparedStatement.setInt(6, mProduct.getAmountConsumption());
             mPreparedStatement.setInt(7, mProduct.getConsumptionCycle());
             mPreparedStatement.setInt(8, mProduct.getId());

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
         String mSql = "SELECT id , name , price , rating , status , image , amountConsumption , consumptionCycle FROM products ORDER BY name";
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
                         mResultSet.getInt(5),
                         mResultSet.getInt(6),
                         mResultSet.getInt(7),
                         mResultSet.getInt(8)

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
         String mSql = "SELECT id , name , price , rating , status , image , amountConsumption , consumptionCycle FROM products  WHERE status=" + vStatus + "  ORDER BY name";
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
                         mResultSet.getInt(5),
                         mResultSet.getInt(6),
                         mResultSet.getInt(7),
                         mResultSet.getInt(8)

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
         String mSql = "SELECT id , name , price , rating , status , image , amountConsumption , consumptionCycle FROM products  WHERE price=" + vPrice + "  ORDER BY name";
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
                         mResultSet.getInt(5),
                         mResultSet.getInt(6),
                         mResultSet.getInt(7),
                         mResultSet.getInt(8)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

     public static List<Product> listAllProductsByRating( float vRating ,  Context mContext){
         // objeto para representar a lista
         List<Product> mProductList = null;
         String mSql = "SELECT id , name , price , rating , status , image , amountConsumption , consumptionCycle FROM products  WHERE rating=" + vRating + "  ORDER BY name";
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
                         mResultSet.getInt(5),
                         mResultSet.getInt(6),
                         mResultSet.getInt(7),
                         mResultSet.getInt(8)

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
         String mSql = "SELECT id , name , price , rating , status , image , amountConsumption , consumptionCycle FROM products  WHERE name LIKE '%" + mName + "%'  ORDER BY name";
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
                         mResultSet.getInt(5),
                         mResultSet.getInt(6),
                         mResultSet.getInt(7),
                         mResultSet.getInt(8)

                 ));
             }

         } catch (Exception e){
             Log.e(TAG , e.getMessage());
         }

         return mProductList;
     }

}










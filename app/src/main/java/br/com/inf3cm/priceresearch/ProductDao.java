package br.com.inf3cm.priceresearch;

// 2

import android.content.Context;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String TAG = "CupomDao";

    public static int insertProduct(Product mProduct, Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "INSERT products (name, price, status, image,) VALUES (?, ?, ?, ?)";

            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);

            mPreparedStatement.setString(1, mProduct.getName());
            mPreparedStatement.setDouble(2, mProduct.getPrice());
            mPreparedStatement.setInt(4, mProduct.getStatus());
            mPreparedStatement.setInt(5, mProduct.getImage());
            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1

        }catch (Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;


    }

    public static int updateProduct(Product mProduct, Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "UPDATE products SET name=?, price=?, status=?, image=?, WHERE id=?";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setString(1, mProduct.getName());
            mPreparedStatement.setDouble(2, mProduct.getPrice());
            mPreparedStatement.setInt(4, mProduct.getStatus());
            mPreparedStatement.setInt(5, mProduct.getImage());
            mPreparedStatement.setInt(8, mProduct.getId());
            vResponse = mPreparedStatement.executeUpdate(); // executou com sucesso será 1

        }catch (Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;

    }

    public static int deleteProduct(Product mProduct, Context mContext){

        //causando lentidao no app

        int vResponse = 0;
        String mSql;

        try{
            mSql = "Delete from products where id=?";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setInt(1, mProduct.getId());
            vResponse = mPreparedStatement.executeUpdate();

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;

    }

    public static int deleteAllProducts(Context mContext){

        int vResponse = 0;
        String mSql;

        try{
            mSql = "Delete from products";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            vResponse = mPreparedStatement.executeUpdate();

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return vResponse;

    }

    public static List<Product> listAllProducts(Context mContext){

        // Listagem de produtos
        List<Product> mProductList = null;
        String mSql = "Select id, name, price,  status, image from products order by name ASC";

//        try{
////            mSql = "Select id, name, price, rating, status, image, amountConsumption, consumptionCycle from products order by name ASC";
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            ResultSet mResultSet = mPreparedStatement.executeQuery();
//
// NOVA ABORDAGEM ABAIXO

        try (PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             ResultSet mResultSet = mPreparedStatement.executeQuery()) {

            /*
            No exemplo acima, PreparedStatement e ResultSet são declarados dentro da instrução try. Assim, eles serão fechados automaticamente ao final do block try, mesmo se uma exceção for lançada. Isso ajuda a evitar vazamentos de recursos e torna o código mais limpo, pois você não precisa explicitamente chamar o método close() em um bloco finally.
            */

            mProductList = new ArrayList<Product>();
            while(mResultSet.next()){
                mProductList.add(new Product(
                        mResultSet.getInt(1),
                        mResultSet.getString(2),
                        mResultSet.getDouble(3), // price
                        mResultSet.getInt(4),
                        0
                ));
            }

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return mProductList;

    }

    public static List<Product> listAllProductsByStatus(int vStatus, Context mContext){

        // Listagem de produtos
        List<Product> mProductList = null;
        String mSql = "SELECT id, name, price, status, image,FROM products WHERE status=" + vStatus + " ORDER BY name ASC";

        //SQL INJECTION

//        try{
////            mSql = "Select id, name, price, rating, status, image, amountConsumption, consumptionCycle from products order by name ASC";
//            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
//
//            ResultSet mResultSet = mPreparedStatement.executeQuery();
//
// NOVA ABORDAGEM ABAIXO

        try (PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
             ResultSet mResultSet = mPreparedStatement.executeQuery()) {

            /*
            No exemplo acima, PreparedStatement e ResultSet são declarados dentro da instrução try. Assim, eles serão fechados automaticamente ao final do block try, mesmo se uma exceção for lançada. Isso ajuda a evitar vazamentos de recursos e torna o código mais limpo, pois você não precisa explicitamente chamar o método close() em um bloco finally.
            */

            mProductList = new ArrayList<Product>();
            while(mResultSet.next()){
                mProductList.add(new Product(
                        mResultSet.getInt(1),
                        mResultSet.getString(2),
                        mResultSet.getDouble(3), // price
                        mResultSet.getInt(4),
                        0
                ));
            }

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return mProductList;

    }

    public static List<Product> listAllProductsByRating(float vRating, Context mContext){

        // Listagem de produtos
        List<Product> mProductList = null;
        String mSql = "SELECT id, name, price, status, image,  FROM products WHERE rating = ? ORDER BY name ASC";

        try {
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            mPreparedStatement.setFloat(1, vRating);
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            /*
            No exemplo acima, PreparedStatement e ResultSet são declarados dentro da instrução try. Assim, eles serão fechados automaticamente ao final do block try, mesmo se uma exceção for lançada. Isso ajuda a evitar vazamentos de recursos e torna o código mais limpo, pois você não precisa explicitamente chamar o método close() em um bloco finally.
            */

            mProductList = new ArrayList<Product>();
            while(mResultSet.next()){
                mProductList.add(new Product(
                        mResultSet.getInt(1),
                        mResultSet.getString(2),
                        mResultSet.getDouble(3), // price
                        mResultSet.getInt(5),
                        0
                ));
            }

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return mProductList;

    }

    public static List<Product> searchProductsByName(String mStringName, Context mContext){

        // Listagem de produtos
        List<Product> mProductList = null;
        String mSql;

        try{
            mSql = "Select id, name, price, rating, status, image,  amountConsumption, consumptionCycle from products where name like '%" + mStringName + "%' order by name ASC";
            PreparedStatement mPreparedStatement = MSSQLConnectionHelper.getConnection(mContext).prepareStatement(mSql);
            ResultSet mResultSet = mPreparedStatement.executeQuery();
            mProductList = new ArrayList<Product>();
            while(mResultSet.next()){
                mProductList.add(new Product(
                        mResultSet.getInt(1),
                        mResultSet.getString(2),
                        mResultSet.getDouble(3),
                        mResultSet.getInt(4),
                        0
                ));
            }

        }catch(Exception mException){
            Log.e(TAG, mException.getMessage());
            mException.printStackTrace();
        }

        return mProductList;

    }

}


/*

Cada método nesta classe é responsável por uma operação específica de CRUD (Criar, Ler, Atualizar, Deletar) no banco de dados.

Por favor, note que esta classe está usando java.sql.PreparedStatement para executar comandos SQL, o que é bom para prevenir injeções SQL e melhorar o desempenho. No entanto, na string SQL do método searchProductsByName, os argumentos estão sendo concatenados diretamente à string SQL. Isto pode potencialmente permitir uma injeção SQL. É recomendado usar parâmetros também neste caso.



Em geral, a classe ProductDao está bem organizada e segue boas práticas comuns em Java para interação com o banco de dados. No entanto, existem algumas áreas que poderiam ser melhoradas para melhorar a segurança, a robustez e a eficiência do código.

Injeção SQL: No método searchProductsByName, o valor de mStringName é concatenado diretamente na consulta SQL. Isso pode permitir um ataque de injeção SQL. Em vez disso, você deve usar o método setString do objeto PreparedStatement para proteger contra a injeção de SQL.

Gerenciamento de exceções: Atualmente, todas as exceções são capturadas e registradas, mas depois ignoradas. Dependendo do seu caso de uso, você pode querer lidar com diferentes exceções de maneiras diferentes. Por exemplo, se uma exceção ocorre durante a inserção de um produto, você pode querer lançar a exceção para que ela possa ser tratada em um nível superior de sua aplicação.

Fechamento de recursos: O código atual não fecha explicitamente PreparedStatement e ResultSet. É uma prática recomendada fechar esses recursos em um bloco finally para garantir que sejam fechados mesmo se ocorrer uma exceção. No entanto, a partir do Java 7, você pode usar o try-with-resources que fecha automaticamente esses recursos.

Retorno de métodos: Para os métodos de inserção, atualização e exclusão, o código retorna o número de linhas afetadas. Isso pode ser útil para confirmar se a operação foi bem-sucedida ou não. No entanto, para a operação de inserção, pode ser mais útil retornar o ID do novo produto inserido.

Uso de contexto: A classe DAO geralmente não deve ter nenhuma dependência em classes Android como Context. O DAO deve apenas lidar com a lógica de banco de dados, enquanto as classes Android deveriam ser usadas nas camadas de serviço ou UI. Se a conexão de banco de dados necessitar de um contexto, você pode considerar usar um singleton para gerenciar a conexão de banco de dados.

Essas são algumas melhorias possíveis para este código. No entanto, a qualidade do código também depende muito dos requisitos do seu projeto específico e das convenções de codificação da sua equipe.






 */
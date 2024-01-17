package br.com.inf3cm.priceresearch;

// 1

import java.util.Date;

public class Cupom {

    public static final String TAG = "cupom table";

    private long mId;
    private String nome;
    private String numero;
    private java.sql.Date data_gerado;
    private java.sql.Date data_validade;
    private java.sql.Date data_uso;
    private String preco;
    private String status;
//    private Cliente cliente;


    public Cupom(long id, String nome, String numero, java.sql.Date data_uso, java.sql.Date data_validade, String preco, String string) {
        mId = id;
        this.nome = nome;
        this.numero = numero;
        this.data_gerado = data_gerado;
        this.data_validade = data_validade;
        this.data_uso = data_uso;
        this.preco = preco;
        this.status = status;
    }
    public Cupom(String nome, String numero, java.sql.Date data_validade, java.sql.Date data_uso, String preco, String status) {
        this.nome = nome;
        this.numero = numero;
        this.data_gerado = data_gerado;
        this.data_validade = data_validade;
        this.data_uso = data_uso;
        this.preco = preco;
        this.status = status;
    }

    public Cupom(long id,String nome, String numero, String preco, String status) {
        mId = id;
        this.nome = nome;
        this.numero = numero;
        this.preco = preco;
        this.status = status;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getData_gerado() {
        return data_gerado;
    }

    public void setData_gerado(java.sql.Date data_gerado) {
        this.data_gerado = data_gerado;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(java.sql.Date data_validade) {
        this.data_validade = data_validade;
    }

    public Date getData_uso() {
        return data_uso;
    }

    public void setData_uso(java.sql.Date data_uso) {
        this.data_uso = data_uso;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getValidade() {
        return data_validade;
}

}


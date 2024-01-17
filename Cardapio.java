package br.com.inf3cm.priceresearch;

// 1

import java.sql.Date;

public class Cardapio {

    public static final String TAG = "cardapio table";

    private long mId;
    private String nome;
    private String preco;
    private String status;
    private String descricao;


    public Cardapio(long id, String nome, String preco, String status, String descricao) {
        mId = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.status = status;
    }
    public Cardapio(String nome, String preco, String status, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.status = status;
        this.descricao = descricao;
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

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


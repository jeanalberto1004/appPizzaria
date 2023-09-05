package br.com.inf3cm.priceresearch;

// 1

public class Product {

    private static final String TAG = "cupom";
    private int mId;
    private String mName;
    private double mPrice;

    public int getId() {
        return mId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    private int mStatus;

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }


    public Product(String name, double price,  int status, int image) {
        mName = name;
        mPrice = price;
        mStatus = status;
        mImage = image;
    }

    public Product(int id, String name, double price, int status, int image) {
        mId = id;
        mName = name;
        mPrice = price;
        mStatus = status;
    }


    private int mImage;

    public int getImage() {
        return mImage;
    }



    public void setImage(int image) {
        mImage = image;
    }



}


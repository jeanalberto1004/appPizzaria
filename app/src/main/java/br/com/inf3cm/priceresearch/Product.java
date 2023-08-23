package br.com.inf3cm.priceresearch;

public class Product {
 public static final String TAG = "Product table";

 private int mId;
 private  String mName;
 private double mPrice;

    public Product(String mName, double mPrice, int mStatus, long mImage) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mStatus = mStatus;
        this.mImage = mImage;
    }

    public Product(int mId, String mName, double mPrice, int mStatus, long mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mStatus = mStatus;
        this.mImage = mImage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public long getImage() {
        return mImage;
    }

    public void setImage(long mImage) {
        this.mImage = mImage;
    }

    private int mStatus;
 private long mImage;

}
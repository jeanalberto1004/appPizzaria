package br.com.inf3cm.priceresearch;

public class Product {

    private int mUnit;

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        mUnit = unit;
    }
// essa classe representa a entidade/tabela PRODUTO

    //criar uma marcação para ajudar a encontrar erros
    public static final String TAG = "Product Entity";

    // declarar as colunas da minha tabela
    private int mId;
    private String mName;
    private double mPrice;
    private int mStatus;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPrice=" + mPrice +
                ", mStatus=" + mStatus +
                ", mRating=" + mRating +
                ", mImage=" + mImage +
                ", mAmountConsumption=" + mAmountConsumption +
                ", mConsumptionCycle=" + mConsumptionCycle +
                '}';
    }

    public Product(int id, String name, double price, int status, float rating, int image, int amountConsumption, int consumptionCycle) {
        mId = id;
        mName = name;
        mPrice = price;
        mStatus = status;
        mRating = rating;
        mImage = image;
        mAmountConsumption = amountConsumption;
        mConsumptionCycle = consumptionCycle;
    }

    public Product(String name, double price, int status, float rating, int image, int amountConsumption, int consumptionCycle) {
        mName = name;
        mPrice = price;
        mStatus = status;
        mRating = rating;
        mImage = image;
        mAmountConsumption = amountConsumption;
        mConsumptionCycle = consumptionCycle;
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

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public int getAmountConsumption() {
        return mAmountConsumption;
    }

    public void setAmountConsumption(int amountConsumption) {
        mAmountConsumption = amountConsumption;
    }

    public int getConsumptionCycle() {
        return mConsumptionCycle;
    }

    public void setConsumptionCycle(int consumptionCycle) {
        mConsumptionCycle = consumptionCycle;
    }

    private float mRating;
    private int mImage;
    private int mAmountConsumption;
    private int mConsumptionCycle;


}

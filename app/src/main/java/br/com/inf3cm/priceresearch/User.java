package br.com.inf3cm.priceresearch;

public class User {

    public static final String TAG = "User Entity";

    //psfs

    private int mId;
    private String mFullName;
    private String mUserName;
    private String mPassword;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public long getmCreateDate() {
        return mCreateDate;
    }

    public void setmCreateDate(long mCreateDate) {
        this.mCreateDate = mCreateDate;
    }

    public String getmApiKey() {
        return mApiKey;
    }

    public void setmApiKey(String mApiKey) {
        this.mApiKey = mApiKey;
    }

    public String getmResetPasswordOtp() {
        return mResetPasswordOtp;
    }

    public void setmResetPasswordOtp(String mResetPasswordOtp) {
        this.mResetPasswordOtp = mResetPasswordOtp;
    }

    public long getmResetPasswordCreatedAt() {
        return mResetPasswordCreatedAt;
    }

    public void setmResetPasswordCreatedAt(long mResetPasswordCreatedAt) {
        this.mResetPasswordCreatedAt = mResetPasswordCreatedAt;
    }

    private String mEmail;
    private long mCreateDate;
    private String mApiKey;
    private String mResetPasswordOtp;
    private long mResetPasswordCreatedAt;

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mFullName='" + mFullName + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mCreateDate=" + mCreateDate +
                ", mApiKey='" + mApiKey + '\'' +
                ", mResetPasswordOtp='" + mResetPasswordOtp + '\'' +
                ", mResetPasswordCreatedAt=" + mResetPasswordCreatedAt +
                '}';
    }

    public User(String fullName, String userName, String password, String email, long createDate, String apiKey, String resetPasswordOtp, long resetPasswordCreatedAt) {
        mFullName = fullName;
        mUserName = userName;
        mPassword = password;
        mEmail = email;
        mCreateDate = createDate;
        mApiKey = apiKey;
        mResetPasswordOtp = resetPasswordOtp;
        mResetPasswordCreatedAt = resetPasswordCreatedAt;
    }

    public User(int id, String fullName, String userName, String password, String email, long createDate, String apiKey, String resetPasswordOtp, long resetPasswordCreatedAt) {
        mId = id;
        mFullName = fullName;
        mUserName = userName;
        mPassword = password;
        mEmail = email;
        mCreateDate = createDate;
        mApiKey = apiKey;
        mResetPasswordOtp = resetPasswordOtp;
        mResetPasswordCreatedAt = resetPasswordCreatedAt;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public long getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(long createDate) {
        mCreateDate = createDate;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public String getResetPasswordOtp() {
        return mResetPasswordOtp;
    }

    public void setResetPasswordOtp(String resetPasswordOtp) {
        mResetPasswordOtp = resetPasswordOtp;
    }

    public long getResetPasswordCreatedAt() {
        return mResetPasswordCreatedAt;
    }

    public void setResetPasswordCreatedAt(long resetPasswordCreatedAt) {
        mResetPasswordCreatedAt = resetPasswordCreatedAt;
    }



}

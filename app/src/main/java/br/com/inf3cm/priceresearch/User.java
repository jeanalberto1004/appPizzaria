package br.com.inf3cm.priceresearch;

public class User {

    private static final String TAG = "User";


    private long mId;
    private String mTelefone;

    public User(String mTelefone, String mCep, String mSenha, String mEmail, String mNome, String mLogradrouro, String mCidade, String mBairro, String mNumeroResid, long mCreateDate) {
        this.mId = mId;
        this.mTelefone = mTelefone;
        this.mCep = mCep;
        this.mSenha = mSenha;
        this.mEmail = mEmail;
        this.mNome = mNome;
        this.mLogradrouro = mLogradrouro;
        this.mCidade = mCidade;
        this.mBairro = mBairro;
        this.mNumeroResid = mNumeroResid;
        this.mComplemento = mComplemento;
        this.mStatus = mStatus;
        this.mCreateDate = mCreateDate;
        this.mApiKey = mApiKey;
        this.mResetSenhaOtp = mResetSenhaOtp;
        this.mResetSenhaCreatedAt = mResetSenhaCreatedAt;
    }

    private String mCep;

    public User(String mTelefone, String mCep) {
        this.mTelefone = mTelefone;
        this.mCep = mCep;
        this.mSenha = mSenha;
        this.mEmail = mEmail;
        this.mNome = mNome;
        this.mLogradrouro = mLogradrouro;
        this.mCidade = mCidade;
        this.mBairro = mBairro;
        this.mComplemento = mComplemento;
        this.mNumeroResid = mNumeroResid;
        this.mStatus = mStatus;
        this.mCreateDate = mCreateDate;
        this.mApiKey = mApiKey;
        this.mResetSenhaOtp = mResetSenhaOtp;
        this.mResetSenhaCreatedAt = mResetSenhaCreatedAt;
    }

    private String mSenha;
    private String mEmail;
    private String mNome;
    private String mLogradrouro;
    private String mCidade;
    private String mBairro;
    private String mNumeroResid;
    private String mComplemento;
    private String mStatus;
    private long mCreateDate;
    private String mApiKey;
    private String mResetSenhaOtp;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmTelefone() {
        return mTelefone;
    }

    public void setmTelefone(String mTelefone) {
        this.mTelefone = mTelefone;
    }

    public String getmCep() {
        return mCep;
    }

    public void setmCep(String mCep) {
        this.mCep = mCep;
    }

    public String getmSenha() {
        return mSenha;
    }

    public void setmSenha(String mSenha) {
        this.mSenha = mSenha;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public String getmLogradrouro() {
        return mLogradrouro;
    }

    public void setmLogradrouro(String mLogradrouro) {
        this.mLogradrouro = mLogradrouro;
    }

    public String getmCidade() {
        return mCidade;
    }

    public void setmCidade(String mCidade) {
        this.mCidade = mCidade;
    }

    public String getmBairro() {
        return mBairro;
    }

    public void setmBairro(String mBairro) {
        this.mBairro = mBairro;
    }

    public String getmNumeroResid() {
        return mNumeroResid;
    }

    public void setmNumeroResid(String mNumeroResid) {
        this.mNumeroResid = mNumeroResid;
    }

    public String getmComplemento() {
        return mComplemento;
    }

    public void setmComplemento(String mComplemento) {
        this.mComplemento = mComplemento;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
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

    public String getmResetSenhaOtp() {
        return mResetSenhaOtp;
    }

    public void setmResetSenhaOtp(String mResetSenhaOtp) {
        this.mResetSenhaOtp = mResetSenhaOtp;
    }

    public long getmResetSenhaCreatedAt() {
        return mResetSenhaCreatedAt;
    }

    public void setmResetSenhaCreatedAt(long mResetSenhaCreatedAt) {
        this.mResetSenhaCreatedAt = mResetSenhaCreatedAt;
    }

    private long mResetSenhaCreatedAt;
}

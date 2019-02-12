package ianmagallan.com.transformers_ianmagallan.Contract;

public interface BasicErrors {
    void onFailure(String mErrorMessage);
    void onServerError(int mCode, String mErrorMessage);
}

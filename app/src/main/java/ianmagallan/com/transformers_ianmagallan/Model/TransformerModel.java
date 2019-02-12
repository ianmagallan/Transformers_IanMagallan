package ianmagallan.com.transformers_ianmagallan.Model;

import ianmagallan.com.transformers_ianmagallan.Contract.TransformerContract;
import ianmagallan.com.transformers_ianmagallan.Services.Api;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Model class that handle all the calls to the API and returns its result to the listeners that are passed by parameter.
public class TransformerModel implements TransformerContract {

    private Api mApi;

    public TransformerModel(Api mApi){
        this.mApi = mApi;
    }

    @Override
    public void getTransformerList(onReceivedList mListener) {
        this.mApi.getTransformers().enqueue(new Callback<TransformerList>() {
            @Override
            public void onResponse(Call<TransformerList> call, Response<TransformerList> response) {
                if(response.isSuccessful()){
                    mListener.onFinished(response.body());
                    return;
                }
                mListener.onServerError(response.code(), response.message());
            }

            @Override
            public void onFailure(Call<TransformerList> call, Throwable t) {
                mListener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void createTransformer(Transformer mTransformer, onReceivedTransformer mListener) {
        this.mApi.postTransformer(mTransformer).enqueue(new Callback<Transformer>() {
            @Override
            public void onResponse(Call<Transformer> call, Response<Transformer> response) {
                if(response.isSuccessful()){
                    mListener.onFinished(response.body());
                    return;
                }
                mListener.onServerError(response.code(), response.message());
            }

            @Override
            public void onFailure(Call<Transformer> call, Throwable t) {
                mListener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void modifyTransformer(Transformer mTransformer, onReceivedTransformer mListener) {
        this.mApi.putTransformer(mTransformer).enqueue(new Callback<Transformer>() {
            @Override
            public void onResponse(Call<Transformer> call, Response<Transformer> response) {
                if(response.isSuccessful()){
                    mListener.onFinished(response.body());
                    return;
                }
                mListener.onServerError(response.code(), response.message());
            }

            @Override
            public void onFailure(Call<Transformer> call, Throwable t) {
                mListener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void deleteTransformer(String mId, onDeleteSuccess mListener) {
        this.mApi.deleteTransformer(mId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    mListener.onFinished();
                    return;
                }
                mListener.onServerError(response.code(), response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onFailure(t.getMessage());
            }
        });
    }
}

package ianmagallan.com.transformers_ianmagallan.Services;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("transformers")
    Call<TransformerList> getTransformers();

    @POST("transformers")
    Call<Transformer> postTransformer(@Body Transformer mTransformer);

    @PUT("transformers")
    Call<Transformer> putTransformer(@Body Transformer mTransformer);

    @DELETE("transformers/{transformer_id}")
    Call<ResponseBody> deleteTransformer(@Path(value = "transformer_id", encoded = true) String mTransformerId);
}

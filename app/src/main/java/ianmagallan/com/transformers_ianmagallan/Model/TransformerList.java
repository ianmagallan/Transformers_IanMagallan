package ianmagallan.com.transformers_ianmagallan.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//List of transformers to adapt it to the server response.
public class TransformerList {
    @SerializedName("transformers")
    private List<Transformer> mTransformers;
    public List<Transformer> getTransformers(){
        return mTransformers;
    }
}

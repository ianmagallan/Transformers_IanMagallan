package ianmagallan.com.transformers_ianmagallan.Contract;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerList;

public interface TransformerContract  {
    interface onFailedListener {
        void onServerError(int code, String message);
        void onFailure(String message);
    }

    interface onReceivedList extends onFailedListener{
        void onFinished(TransformerList mTransformerList);
    }

    interface onReceivedTransformer extends onFailedListener {
        void onFinished(Transformer mTransformer);
    }

    interface onDeleteSuccess extends onFailedListener {
        void onFinished();
    }

    void getTransformerList(onReceivedList mListener);
    void createTransformer(Transformer mTransformer, onReceivedTransformer mListener);
    void modifyTransformer(Transformer mTransformer, onReceivedTransformer mListener);
    void deleteTransformer(String mId, onDeleteSuccess mListener);

}

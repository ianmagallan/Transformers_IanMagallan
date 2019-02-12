package ianmagallan.com.transformers_ianmagallan.Contract;

import android.widget.ImageView;

import java.util.List;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;

public interface TransormerListContract {
    interface View {
        void setTitle(String mTitle);
        void setOverall(String mOverall);
        ImageView getTeamImage();
    }

    interface Presenter {
        void onBindTransformerRowAtPosition(int position, TransormerListContract.View mView);
        int getTransformerRowCount();
        Transformer getTransformer(int mIndex);
        void addTransformers(List<Transformer> mTransformerList);
    }

    interface OnClickItemCallback {
        void onSelectedTransformer(Transformer t);
    }
}

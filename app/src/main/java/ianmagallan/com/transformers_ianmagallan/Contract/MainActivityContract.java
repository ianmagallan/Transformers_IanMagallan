package ianmagallan.com.transformers_ianmagallan.Contract;

import java.util.ArrayList;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;

public class MainActivityContract {
    public interface View extends BasicErrors {
        void onTransformerListResponse(ArrayList<Transformer> mTransformerList);
    }

    public interface Presenter {
        void getTransformerList();
        void setView(MainActivityContract.View mView);
        String fight();
    }
}

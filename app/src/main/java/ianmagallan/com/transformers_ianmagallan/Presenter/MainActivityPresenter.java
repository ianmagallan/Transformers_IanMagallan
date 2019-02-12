package ianmagallan.com.transformers_ianmagallan.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ianmagallan.com.transformers_ianmagallan.Contract.MainActivityContract;
import ianmagallan.com.transformers_ianmagallan.Contract.TransformerContract;
import ianmagallan.com.transformers_ianmagallan.Helpers.GameLogic;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerList;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerModel;
import ianmagallan.com.transformers_ianmagallan.Services.Api;


//Presenter of MainActivity which contains all the logic of the MainActivity.
public class MainActivityPresenter implements MainActivityContract.Presenter, TransformerContract.onReceivedList {
    private TransformerModel mTransformerModel;
    private MainActivityContract.View mView;
    private ArrayList<Transformer> mTransformersList;

    public MainActivityPresenter(Api mApi){
        this.mTransformerModel = new TransformerModel(mApi);
    }

    @Override
    public void setView(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getTransformerList() {
        this.mTransformerModel.getTransformerList(this);
    }

    @Override
    public void onFinished(TransformerList mTransformerList) {
        ArrayList<Transformer> mArrayListTransformer = new ArrayList<>(mTransformerList.getTransformers());
        Collections.sort(mArrayListTransformer, new Comparator<Transformer>() {
            @Override
            public int compare(Transformer transformer, Transformer t1) {
                return Integer.compare(t1.getOverall(), transformer.getOverall());
            }
        });
        this.mTransformersList = mArrayListTransformer;

        this.mView.onTransformerListResponse(mArrayListTransformer);
    }

    @Override
    public void onServerError(int code, String message) {
        this.mView.onServerError(code, message);
    }

    @Override
    public void onFailure(String message) {
        this.mView.onFailure(message);
    }

    @Override
    public String fight() {
        GameLogic game = new GameLogic(this.mTransformersList);
        game.initGame();
        return game.getOutput();
    }
}

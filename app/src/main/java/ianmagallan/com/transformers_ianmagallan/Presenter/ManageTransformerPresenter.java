package ianmagallan.com.transformers_ianmagallan.Presenter;

import ianmagallan.com.transformers_ianmagallan.Contract.ManageTransformerContract;
import ianmagallan.com.transformers_ianmagallan.Contract.TransformerContract;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerModel;
import ianmagallan.com.transformers_ianmagallan.Services.Api;


//Presenter of the "Manage" screen that allows to modify, remove or create a Transformer.
public class ManageTransformerPresenter implements ManageTransformerContract.Presenter, TransformerContract.onReceivedTransformer, TransformerContract.onDeleteSuccess {

    private TransformerModel mTransformerModel;
    private ManageTransformerContract.View mView;
    private Transformer mSelectedTransformer;

    public ManageTransformerPresenter(Api mApi){
        this.mTransformerModel = new TransformerModel(mApi);
    }

    @Override
    public void setView(ManageTransformerContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void addUpdateTransformer(String mName, int mStrength, int mIntelligence, int mSpeed, int mEndurance, int mRank, int mCourage, int mFirepower, int mSkill, char mTeam) {
        if(mName.length() == 0){
            this.mView.onWrongFormat("Name is mandatory");
            return;
        }
        Transformer t = this.mSelectedTransformer != null ? mSelectedTransformer : new Transformer();
        t.setName(mName);
        t.setStrength(mStrength);
        t.setIntelligence(mIntelligence);
        t.setSpeed(mSpeed);
        t.setEndurance(mEndurance);
        t.setRank(mRank);
        t.setCourage(mCourage);
        t.setFirepower(mFirepower);
        t.setSkill(mSkill);
        t.setTeam(mTeam);

        if(this.mSelectedTransformer != null){
            mTransformerModel.modifyTransformer(t, this);
            return;
        }

        mTransformerModel.createTransformer(t, this);
    }

    @Override
    public void loadExistingTransformer(Transformer t) {
        this.mSelectedTransformer = t;

        mView.onTransformerLoaded(
                t.getName(),
                t.getStrength(),
                t.getIntelligence(),
                t.getSpeed(),
                t.getEndurance(),
                t.getRank(),
                t.getCourage(),
                t.getFirepower(),
                t.getSkill(),
                t.getTeam()
        );

        mView.onUpdateCounters(
                String.valueOf(t.getStrength()),
                String.valueOf(t.getIntelligence()),
                String.valueOf(t.getSpeed()),
                String.valueOf(t.getEndurance()),
                String.valueOf(t.getRank()),
                String.valueOf(t.getCourage()),
                String.valueOf(t.getFirepower()),
                String.valueOf(t.getSkill())
        );
    }

    @Override
    public void deleteSelectedTransformer() {
        //There is no such transformer. Which means that it's in creation mode.
        if(mSelectedTransformer == null){
            mView.onDeletedTransformer();
            return;
        }

        //Delete existing transformer.
        mTransformerModel.deleteTransformer(this.mSelectedTransformer.getId(), this);
    }

    @Override
    public void clearSelectedTransformer() {
        this.mSelectedTransformer = null;
    }


    @Override
    public void onServerError(int code, String message) {
        mView.onServerError(code, message);
    }

    @Override
    public void onFailure(String message) {
        mView.onFailure(message);
    }

    @Override
    public void onFinished(Transformer mTransformer) {
        mView.onAddedTransformerResponse();
    }

    //When deleted successfully.
    @Override
    public void onFinished() {
        mView.onDeletedTransformer();
    }


}

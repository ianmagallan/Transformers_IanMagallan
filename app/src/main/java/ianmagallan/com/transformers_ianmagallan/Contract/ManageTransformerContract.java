package ianmagallan.com.transformers_ianmagallan.Contract;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;

public interface ManageTransformerContract {
    interface View extends BasicErrors {
        void onAddedTransformerResponse();
        void onWrongFormat(String message);
        void onTransformerLoaded(String mName, int mStrength, int mIntelligence, int mSpeed, int mEndurance, int mRank, int mCourage, int mFirepower, int mSkill, char mTeam);
        void onUpdateCounters(String mStrength, String mIntelligence, String mSpeed, String mEndurance, String mRank, String mCourage, String mFirepower, String mSkill);
        void onDeletedTransformer();
    }

    interface Presenter {
        void setView(ManageTransformerContract.View mView);
        void addUpdateTransformer(String mName, int mStrength, int mIntelligence, int mSpeed, int mEndurance, int mRank, int mCourage, int mFirepower, int mSkill, char mTeam);
        void loadExistingTransformer(Transformer t);
        void deleteSelectedTransformer();
        void clearSelectedTransformer();
    }
}

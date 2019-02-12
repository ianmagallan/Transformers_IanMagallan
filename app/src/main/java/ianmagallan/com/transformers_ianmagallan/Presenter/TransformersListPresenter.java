package ianmagallan.com.transformers_ianmagallan.Presenter;

import com.squareup.picasso.Picasso;

import java.util.List;

import ianmagallan.com.transformers_ianmagallan.Contract.TransormerListContract;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.R;


//Presenter that handles the logic of the RecyclerView.
public class TransformersListPresenter implements TransormerListContract.Presenter {
    private List<Transformer> mTransformerList;

    public TransformersListPresenter(List<Transformer> mTransformerList){
        this.mTransformerList = mTransformerList;
    }

    @Override
    public void onBindTransformerRowAtPosition(int position, TransormerListContract.View mView) {
        Transformer mTransformer = mTransformerList.get(position);
        mView.setTitle(mTransformer.getName());
        mView.setOverall(String.valueOf(mTransformer.getOverall()));
        Picasso.get()
                .load(mTransformer.getTeamIcon())
                .placeholder(R.drawable.progress_animation)
                .into(mView.getTeamImage());
    }

    @Override
    public int getTransformerRowCount() {
        return mTransformerList.size();
    }

    @Override
    public Transformer getTransformer(int mIndex) {
        return mTransformerList.get(mIndex);
    }

    @Override
    public void addTransformers(List<Transformer> mTransformerList) {
        this.mTransformerList.clear();
        this.mTransformerList.addAll(mTransformerList);
    }
}

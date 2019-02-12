package ianmagallan.com.transformers_ianmagallan.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ianmagallan.com.transformers_ianmagallan.Contract.TransormerListContract;
import ianmagallan.com.transformers_ianmagallan.R;

//RecyclerAdapter class.
public class TransformersRecyclerAdapter extends RecyclerView.Adapter<TransformersListViewHolder> {

    private TransormerListContract.Presenter mPresenter;
    private TransormerListContract.OnClickItemCallback mCallback;


    public TransformersRecyclerAdapter(TransormerListContract.Presenter mPresenter, TransormerListContract.OnClickItemCallback mCallback){
        this.mPresenter = mPresenter;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public TransformersListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TransformersListViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transformer_list_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransformersListViewHolder transformersListViewHolder, int i) {
        mPresenter.onBindTransformerRowAtPosition(i, transformersListViewHolder);
        transformersListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onSelectedTransformer(mPresenter.getTransformer(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPresenter.getTransformerRowCount();
    }
}

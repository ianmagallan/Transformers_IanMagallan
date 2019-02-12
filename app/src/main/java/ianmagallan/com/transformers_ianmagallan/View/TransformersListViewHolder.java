package ianmagallan.com.transformers_ianmagallan.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ianmagallan.com.transformers_ianmagallan.Contract.TransormerListContract;
import ianmagallan.com.transformers_ianmagallan.R;

//ViewHolder class.
public class TransformersListViewHolder extends RecyclerView.ViewHolder implements TransormerListContract.View {

    private TextView tv_name;
    private TextView tv_overall;
    private ImageView iv_team;

    public TransformersListViewHolder(View itemView){
        super(itemView);
        tv_name = itemView.findViewById(R.id.transformer_list_row_tv_name);
        tv_overall = itemView.findViewById(R.id.transformer_list_row_tv_overall_score);
        iv_team = itemView.findViewById(R.id.transformer_list_row_iv_team);
    }

    @Override
    public void setTitle(String mTitle) {
        tv_name.setText(mTitle);
    }

    @Override
    public void setOverall(String mOverall) {
        tv_overall.setText(mOverall);
    }

    @Override
    public ImageView getTeamImage() {
        return iv_team;
    }


}

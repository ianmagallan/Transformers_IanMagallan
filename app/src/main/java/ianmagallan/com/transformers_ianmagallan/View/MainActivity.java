package ianmagallan.com.transformers_ianmagallan.View;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ianmagallan.com.transformers_ianmagallan.App;
import ianmagallan.com.transformers_ianmagallan.Contract.MainActivityContract;
import ianmagallan.com.transformers_ianmagallan.Contract.TransormerListContract;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Presenter.MainActivityPresenter;
import ianmagallan.com.transformers_ianmagallan.Presenter.TransformersListPresenter;
import ianmagallan.com.transformers_ianmagallan.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, TransormerListContract.OnClickItemCallback {

    @Inject
    MainActivityPresenter mMainActivityPresenter;

    private TransformersListPresenter mTransformersListPresenter;

    private AlertDialog mDialog;

    @BindView(R.id.main_rv)
    RecyclerView rv_transformers;

    @BindView(R.id.main_button)
    Button main_button;

    private TransformersRecyclerAdapter ra_transformers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dependency injection initialization.
        ((App) getApplication()).getAppComponent().inject(this);
        mMainActivityPresenter.setView(this);

        //Butterknife initialization.
        ButterKnife.bind(this);

        mTransformersListPresenter = new TransformersListPresenter(new ArrayList<Transformer>());
        ra_transformers = new TransformersRecyclerAdapter(mTransformersListPresenter, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_transformers.setLayoutManager(mLayoutManager);
        rv_transformers.setItemAnimator(new DefaultItemAnimator());
        rv_transformers.setAdapter(ra_transformers);

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.generic_error_title))
                        .setMessage(mMainActivityPresenter.fight())
                        .setPositiveButton(getResources().getString(R.string.generic_ok), null);

                if(mDialog == null ) {
                    mDialog = builder.create();
                }

                if(!mDialog.isShowing()){
                    mDialog.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainActivityPresenter.getTransformerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                startActivity(new Intent(this, ManageTransformerActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTransformerListResponse(ArrayList<Transformer> mTransformerList) {
        mTransformersListPresenter.addTransformers(mTransformerList);
        ra_transformers.notifyDataSetChanged();
        Log.d("MainActivity", "Received Transformer response");
    }

    @Override
    public void onFailure(String mErrorMessage) {
        Log.e("MainActivity", mErrorMessage);
    }

    @Override
    public void onServerError(int mCode, String mErrorMessage) {
        Log.e("MainActivity", mErrorMessage);
    }

    //This is triggered when the user clicks a transformer in the list.
    @Override
    public void onSelectedTransformer(Transformer t) {
        //Go to 'edit view'.
        Log.d("Main", "Obtained transformer" + t.getName());
        Intent intent = new Intent(this, ManageTransformerActivity.class);
        intent.putExtra("Transformer", t);
        startActivity(intent);

    }
}

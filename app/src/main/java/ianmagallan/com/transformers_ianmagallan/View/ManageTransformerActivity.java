package ianmagallan.com.transformers_ianmagallan.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ianmagallan.com.transformers_ianmagallan.App;
import ianmagallan.com.transformers_ianmagallan.Contract.ManageTransformerContract;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Presenter.ManageTransformerPresenter;
import ianmagallan.com.transformers_ianmagallan.R;

public class ManageTransformerActivity extends AppCompatActivity implements ManageTransformerContract.View, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.add_et_name)
    EditText add_et_name;

    //Seekbars
    @BindView(R.id.add_sb_strength)
    SeekBar add_sb_strength;
    @BindView(R.id.add_sb_intelligence)
    SeekBar add_sb_intelligence;
    @BindView(R.id.add_sb_speed)
    SeekBar add_sb_speed;
    @BindView(R.id.add_sb_endurance)
    SeekBar add_sb_endurance;
    @BindView(R.id.add_sb_rank)
    SeekBar add_sb_rank;
    @BindView(R.id.add_sb_courage)
    SeekBar add_sb_courage;
    @BindView(R.id.add_sb_firepower)
    SeekBar add_sb_firepower;
    @BindView(R.id.add_sb_skill)
    SeekBar add_sb_skill;

    //Counters
    @BindView(R.id.add_tv_strength_counter)
    TextView add_tv_strength_counter;
    @BindView(R.id.add_tv_intelligence_counter)
    TextView add_tv_intelligence_counter;
    @BindView(R.id.add_tv_speed_counter)
    TextView add_tv_speed_counter;
    @BindView(R.id.add_tv_endurance_counter)
    TextView add_tv_endurance_counter;
    @BindView(R.id.add_tv_rank_counter)
    TextView add_tv_rank_counter;
    @BindView(R.id.add_tv_courage_counter)
    TextView add_tv_courage_counter;
    @BindView(R.id.add_tv_firepower_counter)
    TextView add_tv_firepower_counter;
    @BindView(R.id.add_tv_skill_counter)
    TextView add_tv_skill_counter;

    //Radio items
    @BindView(R.id.add_rg)
    RadioGroup add_rg_team;

    @Inject
    ManageTransformerPresenter mManageTransformerPresenter;

    private AlertDialog mDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        //Dependency injection initialization.
        ((App) getApplication()).getAppComponent().inject(this);
        mManageTransformerPresenter.setView(this);

        //Butterknife initialization.
        ButterKnife.bind(this);

        //Enable Support bar back button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Set Activity as delegate for seekbar changes.
        add_sb_strength.setOnSeekBarChangeListener(this);
        add_sb_intelligence.setOnSeekBarChangeListener(this);
        add_sb_speed.setOnSeekBarChangeListener(this);
        add_sb_endurance.setOnSeekBarChangeListener(this);
        add_sb_rank.setOnSeekBarChangeListener(this);
        add_sb_courage.setOnSeekBarChangeListener(this);
        add_sb_firepower.setOnSeekBarChangeListener(this);
        add_sb_skill.setOnSeekBarChangeListener(this);


        //Load values in case of editing.
        Intent intent = getIntent();
        if(intent != null && intent.getSerializableExtra("Transformer") != null){
            Transformer t = (Transformer)intent.getSerializableExtra("Transformer");
            Log.d("Add", "Obtained transformer");
            mManageTransformerPresenter.loadExistingTransformer(t);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save:

                char mSelectedTeam = (add_rg_team.getCheckedRadioButtonId() == R.id.add_rb_autobot ? 'A' : 'D');

                mManageTransformerPresenter.addUpdateTransformer(
                        add_et_name.getText().toString(),
                        add_sb_strength.getProgress(),
                        add_sb_intelligence.getProgress(),
                        add_sb_speed.getProgress(),
                        add_sb_endurance.getProgress(),
                        add_sb_rank.getProgress(),
                        add_sb_courage.getProgress(),
                        add_sb_firepower.getProgress(),
                        add_sb_skill.getProgress(),
                        mSelectedTeam
                );
                return true;

            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageTransformerActivity.this);
                builder.setTitle(getResources().getString(R.string.generic_delete))
                        .setMessage(getResources().getString(R.string.dialog_delete_message))
                        .setPositiveButton(getResources().getString(R.string.generic_affirmative), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mManageTransformerPresenter.deleteSelectedTransformer();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.generic_negative), null);

                if(mDialog == null ) {
                    mDialog = builder.create();
                }

                if(!mDialog.isShowing()){
                    mDialog.show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        mManageTransformerPresenter.clearSelectedTransformer();
        super.onBackPressed();
    }

    @Override
    public void onAddedTransformerResponse() {
        onBackPressed();
    }

    @Override
    public void onWrongFormat(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageTransformerActivity.this);
        builder.setTitle(getResources().getString(R.string.generic_error_title))
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.generic_ok), null);

        if(mDialog == null ) {
            mDialog = builder.create();
        }

        if(!mDialog.isShowing()){
            mDialog.show();
        }
    }

    @Override
    public void onTransformerLoaded(String mName, int mStrength, int mIntelligence, int mSpeed, int mEndurance, int mRank, int mCourage, int mFirepower, int mSkill, char mTeam) {
        add_et_name.setText(mName);
        add_sb_strength.setProgress(mStrength);
        add_sb_intelligence.setProgress(mIntelligence);
        add_sb_speed.setProgress(mSpeed);
        add_sb_endurance.setProgress(mEndurance);
        add_sb_rank.setProgress(mRank);
        add_sb_courage.setProgress(mCourage);
        add_sb_firepower.setProgress(mFirepower);
        add_sb_skill.setProgress(mSkill);
        add_rg_team.check((mTeam == 'A' ? R.id.add_rb_autobot : R.id.add_rb_decepticon));
    }

    @Override
    public void onUpdateCounters(String mStrength, String mIntelligence, String mSpeed, String mEndurance, String mRank, String mCourage, String mFirepower, String mSkill) {
        add_tv_strength_counter.setText(mStrength);
        add_tv_intelligence_counter.setText(mIntelligence);
        add_tv_speed_counter.setText(mSpeed);
        add_tv_endurance_counter.setText(mEndurance);
        add_tv_rank_counter.setText(mRank);
        add_tv_courage_counter.setText(mCourage);
        add_tv_firepower_counter.setText(mFirepower);
        add_tv_skill_counter.setText(mSkill);
    }

    @Override
    public void onDeletedTransformer() {
        onBackPressed();
    }


    @Override
    public void onFailure(String mErrorMessage) {
        Log.e("Add", "Failure" + mErrorMessage);
    }

    @Override
    public void onServerError(int mCode, String mErrorMessage) {
        if(mCode == 400){
            AlertDialog.Builder builder = new AlertDialog.Builder(ManageTransformerActivity.this);
            builder.setTitle(getResources().getString(R.string.generic_error_title))
                    .setMessage(getResources().getString(R.string.error_attributes))
                    .setPositiveButton(getResources().getString(R.string.generic_ok), null);
            if(mDialog == null ) {
                mDialog = builder.create();
            }

            if(!mDialog.isShowing()){
                mDialog.show();
            }
            Log.e("Add", getResources().getString(R.string.server_error_message) + " " + mErrorMessage);
        }
    }

    //Seekbar methods.
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch(seekBar.getId()){
            case R.id.add_sb_strength:
                add_tv_strength_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_intelligence:
                add_tv_intelligence_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_speed:
                add_tv_speed_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_endurance:
                add_tv_endurance_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_rank:
                add_tv_rank_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_courage:
                add_tv_courage_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_firepower:
                add_tv_firepower_counter.setText(String.valueOf(i));
                break;
            case R.id.add_sb_skill:
                add_tv_skill_counter.setText(String.valueOf(i));
                break;
            default:
                Log.e("Add", "Not found");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}

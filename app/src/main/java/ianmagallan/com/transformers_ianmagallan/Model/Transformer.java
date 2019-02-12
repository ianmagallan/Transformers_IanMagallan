package ianmagallan.com.transformers_ianmagallan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Object class of Transformer's attributes and it's @tags to be converted to an object for Retrofit.
public class Transformer implements Serializable {

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("strength")
    @Expose
    private int mStrength;

    @SerializedName("intelligence")
    @Expose
    private int mIntelligence;

    @SerializedName("speed")
    @Expose
    private int mSpeed;

    @SerializedName("endurance")
    @Expose
    private int mEndurance;

    @SerializedName("rank")
    @Expose
    private int mRank;

    @SerializedName("courage")
    @Expose
    private int mCourage;

    @SerializedName("firepower")
    @Expose
    private int mFirepower;

    @SerializedName("skill")
    @Expose
    private int mSkill;

    @SerializedName("team")
    @Expose
    private char mTeam;

    @SerializedName("team_icon")
    @Expose
    private String mTeamIcon;

    private boolean mIsDefeated;

    public Transformer(){
        this.mIsDefeated = false;
    }

    public Transformer(String mName, int mStrength, int mIntelligence, int mSpeed, int mEndurance, int mRank, int mCourage, int mFirepower, int mSkill, char mTeam){
        this.mName = mName;
        this.mStrength = mStrength;
        this.mIntelligence = mIntelligence;
        this.mSpeed = mSpeed;
        this.mEndurance = mEndurance;
        this.mRank = mRank;
        this.mCourage = mCourage;
        this.mFirepower = mFirepower;
        this.mSkill = mSkill;
        this.mTeam = mTeam;
        this.mIsDefeated = false;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTeamIcon() {
        return mTeamIcon;
    }

    public int getStrength() {
        return mStrength;
    }

    public void setStrength(int mStrength) {
        this.mStrength = mStrength;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public void setIntelligence(int mIntelligence) {
        this.mIntelligence = mIntelligence;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int mSpeed) {
        this.mSpeed = mSpeed;
    }

    public int getEndurance() {
        return mEndurance;
    }

    public void setEndurance(int mEndurance) {
        this.mEndurance = mEndurance;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int mRank) {
        this.mRank = mRank;
    }

    public int getCourage() {
        return mCourage;
    }

    public void setCourage(int mCourage) {
        this.mCourage = mCourage;
    }

    public int getFirepower() {
        return mFirepower;
    }

    public void setFirepower(int mFirepower) {
        this.mFirepower = mFirepower;
    }

    public int getSkill() {
        return mSkill;
    }

    public void setSkill(int mSkill) {
        this.mSkill = mSkill;
    }

    public char getTeam() {
        return mTeam;
    }

    public void setTeam(char mTeam) {
        this.mTeam = mTeam;
    }

    public int getOverall(){
        return (mStrength + mIntelligence + mSpeed + mEndurance + mFirepower);
    }

    public void setDefeated(boolean defeated){
        this.mIsDefeated = defeated;
    }

    public boolean getDefeated(){
        return this.mIsDefeated;
    }
}

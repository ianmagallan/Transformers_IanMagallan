package ianmagallan.com.transformers_ianmagallan.Helpers;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ianmagallan.com.transformers_ianmagallan.Model.Transformer;


/**
 * Manage all the fights logic.
 */
public class GameLogic {

    public enum BATTLE_RESULT {
        FIRST_WINS,
        SECOND_WINS,
        TIE,
        ALL_DESTROYED
    }

    public enum WINNING_TEAM {
        AUTOBOT,
        DECEPTICON,
        TIE
    }

    private final String OPTIMUS = "Optimus Prime", PREDAKING = "Predaking";
    private final int SKILL_DIFFERENCE = 3, COURAGE_DIFFERENCE = 4, STRENGTH_DIFFERENCE = 3;
    private List<Transformer> mTransformersList;
    private List<Transformer> mAutobots;
    private List<Transformer> mDecepticons;
    private int mBattleCounter;

    public GameLogic(List<Transformer> mTransformersList){
        this.mTransformersList = mTransformersList;
    }

    //Initialize the game and performs all the battles and change the status of defeated Transformers to 'defeated'.
    public void initGame(){

        mBattleCounter = 0;
        mAutobots = new ArrayList<>();
        mDecepticons = new ArrayList<>();
        boolean endGame = false;

        //Separate teams.
        for(Transformer mTransformer : mTransformersList){
            mTransformer.setDefeated(false);
            if(mTransformer.getTeam() == 'D'){
                mDecepticons.add(mTransformer);
            } else {
                mAutobots.add(mTransformer);
            }
        }

        for(int i = 0; i < mAutobots.size() && i < mDecepticons.size() && !endGame; i++){
            Transformer autobot = mAutobots.get(i);
            Transformer decepticon = mDecepticons.get(i);
            mBattleCounter ++;

            switch (battleResult(autobot,decepticon)){
                case FIRST_WINS:
                    decepticon.setDefeated(true);
                    mDecepticons.set(i, decepticon);
                    break;
                case SECOND_WINS:
                    autobot.setDefeated(true);
                    mAutobots.set(i, autobot);
                    break;
                case TIE:
                    autobot.setDefeated(true);
                    decepticon.setDefeated(true);
                    mAutobots.set(i, autobot);
                    mDecepticons.set(i, decepticon);
                    break;
                case ALL_DESTROYED:
                    endGame = true;
                    mAutobots = setAllDefeated(mAutobots);
                    mDecepticons = setAllDefeated(mDecepticons);
                    break;
            }

        }

    }

    //Returns an state which returns who won the battle(First, Second, Tie or both destroyed).
    public BATTLE_RESULT battleResult(Transformer autobot, Transformer decepticon){
        String autobotName = autobot.getName();
        String decepticonName = decepticon.getName();
        int courageDifference = autobot.getCourage() - decepticon.getCourage();
        int strengthDifference = autobot.getStrength() - decepticon.getStrength();
        int skillDifference = autobot.getSkill() - decepticon.getSkill();


        //When a match between Optimus and Predaking
        if((autobotName.equals(OPTIMUS) || autobotName.equals(PREDAKING))
                && (decepticonName.equals(OPTIMUS)|| decepticonName.equals(PREDAKING))){
            return BATTLE_RESULT.ALL_DESTROYED;
        }


        //When Autobot wins
        if(autobotName.equals(OPTIMUS) || autobotName.equals(PREDAKING) ||
                (Math.abs(skillDifference) >= SKILL_DIFFERENCE && skillDifference > 0) || //More skilled
                (Math.abs(courageDifference) >= COURAGE_DIFFERENCE && courageDifference > 0) || //More courage.
                (Math.abs(strengthDifference) >= STRENGTH_DIFFERENCE && strengthDifference > 0) //More strength
        ){
            return BATTLE_RESULT.FIRST_WINS;
        }

        //When Decepticon wins
        if(decepticonName.equals(OPTIMUS) || decepticonName.equals(PREDAKING) ||
                (Math.abs(skillDifference) >= SKILL_DIFFERENCE && skillDifference < 0) || //More skilled
                (Math.abs(courageDifference) >= COURAGE_DIFFERENCE && courageDifference < 0) || //More courage.
                (Math.abs(strengthDifference) >= STRENGTH_DIFFERENCE && strengthDifference < 0) //More strength
        ){

            return BATTLE_RESULT.SECOND_WINS;
        }

        if(autobot.getOverall() > decepticon.getOverall()){
            return BATTLE_RESULT.FIRST_WINS;
        } else if(decepticon.getOverall() > autobot.getOverall()){
            return BATTLE_RESULT.SECOND_WINS;
        }


        return BATTLE_RESULT.TIE;
    }


    //Set all defeated(i.e. when Optimus fights against Predaking).
    private List<Transformer> setAllDefeated(List<Transformer> mTeam){
        for(int i = 0; i < mTeam.size() ; i++){
            Transformer t = mTeam.get(i);
            t.setDefeated(true);
            mTeam.set(i, t);
        }
        return mTeam;
    }

    //Get a list separated by comma of the survivors of a given team.
    private String getSurvivors(List<Transformer> mTeam){
        List<String> mSurvivors = new ArrayList<>();
        for(Transformer t : mTeam){
            if(!t.getDefeated()){
                mSurvivors.add(t.getName());
            }
        }
        return TextUtils.join(", ", mSurvivors);
    }

    //Returns what team is the winner considering the performed battles.
    private WINNING_TEAM getWinner(){
        int autobotsScore = 0;
        int decepticonsScore = 0;
        for(int i = 0; i < mAutobots.size() && i < mDecepticons.size(); i++){
            if(mAutobots.get(i).getDefeated()){
                decepticonsScore ++;
            }
            if(mDecepticons.get(i).getDefeated()){
                autobotsScore ++;
            }
        }
        if(autobotsScore > decepticonsScore){
            return WINNING_TEAM.AUTOBOT;
        } else if(decepticonsScore > autobotsScore){
            return WINNING_TEAM.DECEPTICON;
        } else {
            return WINNING_TEAM.TIE;
        }
    }

    //Returns a string output of the result of the battle.
    public String getOutput(){
        WINNING_TEAM winningTeam = getWinner();
        if(winningTeam == WINNING_TEAM.TIE){
            return mBattleCounter + " battles\nThere is a tie.\nDecepticon survivors: " + getSurvivors(mDecepticons) + "\nAutobot survivors: " + getSurvivors(mAutobots);
        }
        String autobotsText = "(Autobots): " + getSurvivors(mAutobots);
        String decepticonsText = "(Decepticons): " + getSurvivors(mDecepticons);

        return mBattleCounter + " battles \nWinning team " + (winningTeam == WINNING_TEAM.AUTOBOT ? autobotsText : decepticonsText) + "\nSurvivors from the losing team " + (winningTeam == WINNING_TEAM.AUTOBOT ? decepticonsText : autobotsText);
    }
}

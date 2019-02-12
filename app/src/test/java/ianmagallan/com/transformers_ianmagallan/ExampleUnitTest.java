package ianmagallan.com.transformers_ianmagallan;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import ianmagallan.com.transformers_ianmagallan.Helpers.GameLogic;
import ianmagallan.com.transformers_ianmagallan.Model.Transformer;
import ianmagallan.com.transformers_ianmagallan.Model.TransformerList;
import ianmagallan.com.transformers_ianmagallan.Presenter.MainActivityPresenter;
import ianmagallan.com.transformers_ianmagallan.Services.Api;
import ianmagallan.com.transformers_ianmagallan.View.MainActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void decepticon_wins_courage(){
        Transformer d1 = new Transformer("Soundwave", 8, 9, 2, 6, 7, 5, 6, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 6, 6, 7, 9, 5, 2, 9, 7, 'A');
        //Transformer a2 = new Transformer("Hubcap", 4,4, 4 ,4 ,4 ,4 ,4 ,4, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);
       // mTransformerList.add(a2);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.SECOND_WINS, result);
    }

    @Test
    public void decepticon_defeat_courage(){
        Transformer d1 = new Transformer("Soundwave", 5, 9, 2, 6, 7, 2, 6, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 5, 6, 7, 9, 5, 6, 9, 7, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.FIRST_WINS, result);
    }

    @Test
    public void decepticon_wins_skill(){
        Transformer d1 = new Transformer("Soundwave", 5, 9, 2, 6, 7, 5, 6, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 5, 7, 7, 9, 5, 6, 9, 7, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.SECOND_WINS, result);
    }


    @Test
    public void decepticon_lose_skill(){
        Transformer d1 = new Transformer("Soundwave", 5, 9, 2, 6, 7, 5, 6, 7, 'D');
        Transformer a1 = new Transformer("Bluestreak", 5, 7, 7, 9, 5, 6, 9, 10, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.FIRST_WINS, result);
    }

    @Test
    public void decepticon_win_strength(){
        Transformer d1 = new Transformer("Soundwave", 8, 9, 5, 7, 7, 5, 7, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 5, 7, 7, 9, 5, 6, 9, 10, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.SECOND_WINS, result);
    }

    @Test
    public void tie_overall(){
        Transformer d1 = new Transformer("Soundwave", 8, 9, 5, 9, 5, 6, 9, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 8, 9, 5, 9, 5, 6, 9, 10, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.TIE, result);
    }


    @Test
    public void decepticon_win_by_name(){
        Transformer d1 = new Transformer("Optimus Prime", 8, 9, 5, 9, 5, 6, 9, 10, 'D');
        Transformer a1 = new Transformer("Bluestreak", 8, 9, 5, 9, 5, 6, 9, 10, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.SECOND_WINS, result);
    }


    @Test
    public void both_lose_by_name(){
        Transformer d1 = new Transformer("Optimus Prime", 8, 9, 5, 9, 5, 6, 9, 10, 'D');
        Transformer a1 = new Transformer("Predaking", 8, 9, 5, 9, 5, 6, 9, 10, 'A');

        List<Transformer> mTransformerList = new ArrayList<>();
        mTransformerList.add(d1);
        mTransformerList.add(a1);

        GameLogic gameLogic = new GameLogic(mTransformerList);
        gameLogic.initGame();
        GameLogic.BATTLE_RESULT result = gameLogic.battleResult(a1, d1);

        assertEquals(GameLogic.BATTLE_RESULT.ALL_DESTROYED, result);
    }


    @Test
    public void get_transformers(){
        Api mockedApi = Mockito.mock(Api.class);
        TransformerList mTransformerObject = Mockito.mock(TransformerList.class);
        MainActivity mView = Mockito.mock(MainActivity.class);
        List<Transformer> mTransformerList = new ArrayList<>();

        Mockito.when(mTransformerObject.getTransformers()).thenReturn(mTransformerList);

        MainActivityPresenter mPresenter = new MainActivityPresenter(mockedApi);
        mPresenter.setView(mView);


        mPresenter.onFinished(mTransformerObject);

        Mockito.verify(mView, Mockito.times(1)).onTransformerListResponse(ArgumentMatchers.any());
    }







}
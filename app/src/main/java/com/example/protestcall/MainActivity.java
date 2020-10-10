package com.example.protestcall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =  "Main Activity";

    private  static final int PLAY_SOUND_TASK_INTERVAL_millis = 15 * 1000;
    private  static final int PLAY_SOUND_CROSS_MOBILES_TIMING_millis = 2;

    private boolean mIsMainActionOn = false;
    private TimerTask mPlaySoundTimerTask;
    private Timer mMainActionTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMainAction();
    }

    public void toggleMainAction(View view) {
        if (!mIsMainActionOn) {
            startMainAction();
        }
        else
        {
            stopMainAction();
        }

        mIsMainActionOn = !mIsMainActionOn;
    }

    private void stopMainAction() {
        Log.d(TAG, "stopMainAction");
        mMainActionTimer.cancel();
    }

    private void startMainAction() {
        Log.d(TAG, "startMainAction");
        //TODO - indicate sound is on, maybe by sound lines coming out of a megaphone
            mPlaySoundTimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "mPlaySoundTimerTask run");
                playSound();
            }
        };

        mMainActionTimer = new Timer();
        long delayTime = (long)calculateDelayTimeToSyncWithOtherMobiles();
        mMainActionTimer.scheduleAtFixedRate(mPlaySoundTimerTask, 0, PLAY_SOUND_TASK_INTERVAL_millis);
    }

    private void playSound() {
        Log.d(TAG, "a SHOUT out!");
    }

    private int calculateDelayTimeToSyncWithOtherMobiles() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Log.d(TAG, "time = " + timeInMillis);

        //<AMIT>
        int res = closestNumberAboveNMultiplierOfM(32, 4); //Expected: 32
        Log.d(TAG, "res = " + res);
        res = closestNumberAboveNMultiplierOfM(32, 5); //Expected:35
        Log.d(TAG, "res = " + res);
        res = closestNumberAboveNMultiplierOfM(32, 10); //Expected:40
        Log.d(TAG, "res = " + res);
        //</AMIT>

        return 0;
    }

    // function to find the number closest to n
    // and divisible by m
    static int closestNumberAboveNMultiplierOfM(int n, int m)
    {
        if (n % m == 0) {
            //curr num n is multiplication of m.
            return n;
        }

        // find the quotient
        int q = n / m;

        // 2nd possible closest number
        // Assumption: n, m > 0
        int result = m * (q + 1);

        Log.d(TAG, "closestNumberAboveNMultiplierOfM() result = " + result);
        return result;
    }

}
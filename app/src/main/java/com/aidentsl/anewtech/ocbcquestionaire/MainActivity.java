package com.aidentsl.anewtech.ocbcquestionaire;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.aidentsl.anewtech.ocbcquestionaire.utils.HideSetting;
import com.qihancloud.opensdk.base.TopBaseActivity;
import com.qihancloud.opensdk.beans.FuncConstant;
import com.qihancloud.opensdk.function.beans.SpeakOption;
import com.qihancloud.opensdk.function.unit.SpeechManager;
import com.qihancloud.opensdk.function.unit.SystemManager;

public class MainActivity extends TopBaseActivity {

    MainFragment mainFragment;

    public SystemManager systemManager;
    public SpeechManager speechManager;
    public SpeakOption speakOption;

    ImageView exitBtn;
    Boolean reveal = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //register(MainActivity.class);
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        systemManager = (SystemManager) getUnitManager(FuncConstant.SYSTEM_MANAGER);
        speechManager = (SpeechManager) getUnitManager(FuncConstant.SPEECH_MANAGER);
        speakOption = new SpeakOption();

        closeFloatBar();

        if (isNetworkAvailable())     //check if internet available or not
        {

        } else    //Not connected
        {
            Toast.makeText(
                    this,
                    "Internet Disconnected, needed for Sanbot speech to work.",
                    Toast.LENGTH_LONG
            ).show();
        }

        FragmentTransaction transaction;
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Main Fragment
        mainFragment = new MainFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainFragment, mainFragment, "Frag_Main");
        transaction.commit();

        exitBtn = findViewById(R.id.exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reveal == false) {
                    openFloatBar();
                    reveal = true;
                } else {
                    closeFloatBar();
                    reveal = false;
                }
            }
        });

    }

    public void closeFloatBar() {
        //Log.i(TAG, " 广播关闭floatbar");
        HideSetting.hideFloatBar(this, getClass().getName(), true);
    }

    public void openFloatBar() {
        //Log.i(TAG, " 广播打开floatbar");
        HideSetting.hideFloatBar(this, getClass().getName(), false);
    }

    @Override
    protected void onMainServiceConnected() {

    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}

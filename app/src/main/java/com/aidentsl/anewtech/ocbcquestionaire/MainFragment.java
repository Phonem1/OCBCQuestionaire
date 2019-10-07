package com.aidentsl.anewtech.ocbcquestionaire;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aidentsl.anewtech.ocbcquestionaire.utils.HideSetting;
import com.qihancloud.opensdk.function.beans.EmotionsType;

public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context mContext;
    ImageView yesBtn;

    CountDownTimer greetingsTimer;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute yor code

                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        //((MainActivity)mContext).speechManager.startSpeak("Hi there! Welcome to OCBC Bank. Do you know what is OCBC RoboInvest? Do you want to know more? ", ((MainActivity)mContext).speakOption);
                        ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.LAUGHTER) ;
                        ((MainActivity)mContext).speechManager.startSpeak("Hi there! Welcome to OCBC Bank. How much do you know about the human body, general health facts and statistics?", ((MainActivity)mContext).speakOption);
                    }

                }.start();
            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute yor code

                greetingsTimer = new CountDownTimer(20000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        //((MainActivity)mContext).speechManager.startSpeak("Hi there! Welcome to OCBC Bank. Do you know what is OCBC RoboInvest? Do you want to know more? ", ((MainActivity)mContext).speakOption);
                        ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.LAUGHTER) ;
                        ((MainActivity)mContext).speechManager.startSpeak("How much do you know about the human body, general health facts and statistics? Take the following quiz to find out and stand to win a limited edition mystery gift when you answer all questions correctly.", ((MainActivity)mContext).speakOption);
                        greetingsTimer.start();
                    }

                }.start();
            }
        }, 0);


        yesBtn = view.findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)mContext).speechManager.stopSpeak();
                greetingsTimer.cancel();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //Fragment videoFragment = new VideoFragment();
                //transaction.replace(R.id.mainFragment, videoFragment);
                //Fragment qFragment = new QFragment();
//                Fragment qFragment = new QFragmentV2();
                //Fragment qFragment = new VideoFragmentV2();
                //transaction.replace(R.id.mainFragment, qFragment);

                Fragment videoFragment = new VideoFragmentV2();
                transaction.replace(R.id.mainFragment, videoFragment);

                transaction.addToBackStack(null);
                transaction.commit();

                /*
                Fragment qFragment = new QFragment();
                transaction.replace(R.id.mainFragment, qFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            */


            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}

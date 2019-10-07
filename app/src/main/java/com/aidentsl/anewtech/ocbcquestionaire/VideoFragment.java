package com.aidentsl.anewtech.ocbcquestionaire;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import cn.jzvd.JzvdStd;


public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context mContext;
    JzvdStd vPlayer;

    ImageView skipBtn;
    CountDownTimer videoTimer;
    CountDownTimer startTimer;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        vPlayer = view.findViewById(R.id.vPlayer);
        vPlayer.setUp(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +"/ocbcvideo.mp4"
                , "OCBC RoboInvest" , vPlayer.SCREEN_WINDOW_NORMAL);
        vPlayer.startButton.setVisibility(View.GONE);
        vPlayer.progressBar.setVisibility(View.GONE);
        vPlayer.backButton.setVisibility(View.GONE);
        vPlayer.batteryLevel.setVisibility(View.GONE);

                startTimer = new CountDownTimer(21000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        //vPlayer.startWindowFullscreen();
                        vPlayer.startVideo();
                    }

                }.start();




                videoTimer = new CountDownTimer(58000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        vPlayer.releaseAllVideos();

                        videoTimer.cancel();
                        startTimer.cancel();

                        FragmentTransaction ft = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                        QFragment qFragment = new QFragment();

                        ft.add(R.id.container, qFragment);
                        ft.addToBackStack("Question");
                        ft.commit();

                    }

                }.start();

                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {

                        ((MainActivity)mContext).speechManager.startSpeak("Here is a short video on what is OCBC RoboInvest. After the video, there will be a short quiz. If you can answer all the questions correctly, you will get a starbucks gift card worth S$20. Celebrate and get your favourite Starbucks drink on us.", ((MainActivity)mContext).speakOption);

                    }

                }.start();

        skipBtn = view.findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPlayer.onAutoCompletion();
                vPlayer.releaseAllVideos();

                videoTimer.cancel();
                startTimer.cancel();


                FragmentTransaction ft = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                QFragment qFragment = new QFragment();

                ft.add(R.id.container, qFragment);
                ft.addToBackStack("Question");
                ft.commit();

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

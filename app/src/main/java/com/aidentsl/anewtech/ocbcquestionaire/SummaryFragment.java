package com.aidentsl.anewtech.ocbcquestionaire;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context mContext;
    Bundle bundle;
    ArrayList<Integer> answers;

    TextView score;
    TextView result;

    TextView q1checking;
    TextView q1answer;

    TextView q2checking;
    TextView q2answer;

    TextView q3checking;
    TextView q3answer;

    TextView q4checking;
    TextView q4answer;

    TextView q5checking;
    TextView q5answer;

    ImageView endBtn;

    int totalScore = 0;

    CountDownTimer idleTimer;

    public SummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
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
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        score = view.findViewById(R.id.score);
        result = view.findViewById(R.id.result);

        q1answer = view.findViewById(R.id.q1answer);
        q1checking = view.findViewById(R.id.q1checking);

        q2answer = view.findViewById(R.id.q2answer);
        q2checking = view.findViewById(R.id.q2checking);

        q3answer = view.findViewById(R.id.q3answer);
        q3checking = view.findViewById(R.id.q3checking);

        q4answer = view.findViewById(R.id.q4answer);
        q4checking = view.findViewById(R.id.q4checking);

        q5answer = view.findViewById(R.id.q5answer);
        q5checking = view.findViewById(R.id.q5checking);

        endBtn = view.findViewById(R.id.endBtn);

        bundle = this.getArguments();
        if (bundle != null) {
            answers = bundle.getIntegerArrayList("answer");
        }

        if(answers.get(0) != 3){
            q1answer.setTextColor(Color.RED);
            q1checking.setTextColor(Color.RED);

            q1checking.setText("Your answer is INCORRECT! ");
            q1answer.setText("The correct answer is : 3");
        }else{
            q1answer.setTextColor(Color.GREEN);
            q1checking.setTextColor(Color.GREEN);

            q1checking.setText("3, Your answer is CORRECT! ");
            q1answer.setVisibility(View.GONE);

            totalScore++;
        }

        if(answers.get(1) != 4){
            q2answer.setTextColor(Color.RED);
            q2checking.setTextColor(Color.RED);

            q2checking.setText("Your answer is INCORRECT! ");
            q2answer.setText("The correct answer is : 4");
        }else{
            q2answer.setTextColor(Color.GREEN);
            q2checking.setTextColor(Color.GREEN);

            q2checking.setText("4, Your answer is CORRECT! ");
            q2answer.setVisibility(View.GONE);

            totalScore++;
        }

        if(answers.get(2) != 3){
            q3answer.setTextColor(Color.RED);
            q3checking.setTextColor(Color.RED);

            q3checking.setText("Your answer is INCORRECT! ");
            q3answer.setText("The correct answer is : 3");

        }else{
            q3answer.setTextColor(Color.GREEN);
            q3checking.setTextColor(Color.GREEN);

            q3checking.setText("3, Your answer is CORRECT! ");
            q3answer.setVisibility(View.GONE);

            totalScore++;
        }

        if(answers.get(3) != 4){
            q4answer.setTextColor(Color.RED);
            q4checking.setTextColor(Color.RED);

            q4checking.setText("Your answer is INCORRECT! ");
            q4answer.setText("The correct answer is : 4");
        }else{
            q4answer.setTextColor(Color.GREEN);
            q4checking.setTextColor(Color.GREEN);

            q4checking.setText("4, Your answer is CORRECT! ");
            q4answer.setVisibility(View.GONE);

            totalScore++;
        }

        if(answers.get(4) != 2){
            q5answer.setTextColor(Color.RED);
            q5checking.setTextColor(Color.RED);

            q5checking.setText("Your answer is INCORRECT! ");
            q5answer.setText("The correct answer is : 2");
        }else{
            q5answer.setTextColor(Color.GREEN);
            q5checking.setTextColor(Color.GREEN);

            q5checking.setText("2, Your answer is CORRECT! ");
            q5answer.setVisibility(View.GONE);

            totalScore++;
        }

        score.setText("Your Score : " + totalScore + " / 5");

        if(totalScore >= 5) {
            result.setTextColor(Color.GREEN);
            result.setText("Congratulations! You won a S$20 Starbucks Gift Card!");

            ((MainActivity)mContext).speechManager.startSpeak("Congratulations! You won a S$20 Starbucks Gift Card!", ((MainActivity)mContext).speakOption);

        } else {
            result.setTextColor(Color.RED);
            result.setText("Don't fret! You can always queue up and try again!");

            ((MainActivity)mContext).speechManager.startSpeak("Don't fret! You can always queue up and try again!", ((MainActivity)mContext).speakOption);

        }

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalScore = 0;
                idleTimer.cancel();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute yor code

                idleTimer = new CountDownTimer(60000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        totalScore = 0;

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                    }

                }.start();
            }
        }, 0);

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

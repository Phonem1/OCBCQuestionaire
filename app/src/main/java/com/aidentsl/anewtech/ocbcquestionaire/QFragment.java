package com.aidentsl.anewtech.ocbcquestionaire;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView question;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;

    TextView Q5A1Replacement;
    TextView Q5A2Replacement;
    TextView Q5A3Replacement;

    TextView Q2A2Replacement;
    TextView Q2A3Replacement;

    int qNumber = 1;
    ArrayList<Integer> userAnswer;

    ImageView backBtn;

    Context mContext;

    public QFragment() {
        // Required empty public constructor
    }

    public static QFragment newInstance(String param1, String param2) {
        QFragment fragment = new QFragment();
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
        View view = inflater.inflate(R.layout.fragment_q, container, false);

        question = view.findViewById(R.id.question);
        //question.setText("Question 1: What is OCBC RoboInvest?");
        //((MainActivity)mContext).speechManager.startSpeak("Question 1: What is OCBC RoboInvest?", ((MainActivity)mContext).speakOption);
        question.setText("Question 1: ______ is the largest organ in the human body");
        ((MainActivity)mContext).speechManager.startSpeak("Question 1: the largest organ in the human body is?", ((MainActivity)mContext).speakOption);


//        answer1 = view.findViewById(R.id.answer1);
//        answer1.setText("You are talking to it now :)");

        answer1 = view.findViewById(R.id.answer1);
        answer1.setText("Lungs");

//        answer2 = view.findViewById(R.id.answer2);
//        answer2.setText("It is a platform run by Robots to help you invest");

        answer2 = view.findViewById(R.id.answer2);
        answer2.setText("Skin");

//        answer3 = view.findViewById(R.id.answer3);
//        answer3.setText("An automated investment platform that lets you choose from a range of portfolios and automatically rebalances your portfolio (with your approval) based on market conditions. \n");

        answer3 = view.findViewById(R.id.answer3);
        answer3.setText("Kidney");


//        answer4 = view.findViewById(R.id.answer4);
//        answer4.setText("");

        answer4 = view.findViewById(R.id.answer4);
        answer4.setText("Interstine");

        Q5A1Replacement = view.findViewById(R.id.Q5A1Replacement);
        Q2A2Replacement = view.findViewById(R.id.Q2A2Replacement);
        Q5A2Replacement = view.findViewById(R.id.Q5A2Replacement);
        Q2A3Replacement = view.findViewById(R.id.Q2A3Replacement);
        Q5A3Replacement = view.findViewById(R.id.Q5A3Replacement);

        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);

        Q2A2Replacement.setVisibility(View.GONE);
        Q2A3Replacement.setVisibility(View.GONE);
        Q5A1Replacement.setVisibility(View.GONE);
        Q5A2Replacement.setVisibility(View.GONE);
        Q5A3Replacement.setVisibility(View.GONE);

        userAnswer = new ArrayList<>();

        runQuestions();

        backBtn = view.findViewById(R.id.goBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.clear();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }

            }
        });

        return view;
    }

    public void runQuestions(){
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(1);
                qNumber++;

                changeQuestion();
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(2);
                qNumber++;

                changeQuestion();
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(3);
                qNumber++;

                changeQuestion();
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(4);
                qNumber++;

                changeQuestion();
            }
        });

        //REPLACEMENTS
        Q5A1Replacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(1);
                qNumber++;

                changeQuestion();
            }
        });

        Q5A2Replacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(2);
                qNumber++;

                changeQuestion();
            }
        });

        Q5A3Replacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(3);
                qNumber++;

                changeQuestion();
            }
        });

        Q2A2Replacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(2);
                qNumber++;

                changeQuestion();
            }
        });

        Q2A3Replacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer.add(3);
                qNumber++;

                changeQuestion();
            }
        });

    }

    public void changeQuestion() {

        if(qNumber == 2)
        {
            answer2.setVisibility(View.GONE);
            Q2A2Replacement.setVisibility(View.VISIBLE);

            answer3.setVisibility(View.GONE);
            Q2A3Replacement.setVisibility(View.VISIBLE);

            question.setText("Question 2: How many curated portfolios does OCBC RoboInvest offer?");
            ((MainActivity)mContext).speechManager.startSpeak("Question 2: How many curated portfolios does OCBC RoboInvest offer?", ((MainActivity)mContext).speakOption);

            answer1.setText("1");
            Q2A2Replacement.setText("5");
            Q2A3Replacement.setText("30");
            answer4.setText("28");

        } else if(qNumber == 3)
        {
            question.setText("Question 3: What do the portfolios consist of?");
            ((MainActivity)mContext).speechManager.startSpeak("Question 3: What do the portfolios consist of?", ((MainActivity)mContext).speakOption);

            answer1.setText("ETFs only");
            Q2A2Replacement.setText("Unit Trust");
            Q2A3Replacement.setText("ETFs and Equities");
            answer4.setText("Bonds");

        } else if(qNumber == 4)
        {
            question.setText("Question 4: What is the minimum investment for OCBC RoboInvest?");
            ((MainActivity)mContext).speechManager.startSpeak("Question 4: What is the minimum investment for OCBC RoboInvest?", ((MainActivity)mContext).speakOption);

            answer1.setText("SGD5,000");
            Q2A2Replacement.setText("SGD4,500");
            Q2A3Replacement.setText("SGD4,000");
            answer4.setText("SGD3,500");

        } else if(qNumber == 5)
        {
            answer1.setVisibility(View.GONE);
            Q5A1Replacement.setVisibility(View.VISIBLE);

            answer2.setVisibility(View.GONE);
            Q2A2Replacement.setVisibility(View.GONE);
            Q5A2Replacement.setVisibility(View.VISIBLE);
            Q2A3Replacement.setVisibility(View.GONE);
            Q5A3Replacement.setVisibility(View.VISIBLE);

            question.setText("Question 5: How do I manage my portfolio? ");
            ((MainActivity)mContext).speechManager.startSpeak("Question 5: How do I manage my portfolio?", ((MainActivity)mContext).speakOption);

            Q5A1Replacement.setText("Log into the platform daily and rebalance my portfolio by myself");
            Q5A2Replacement.setText("The platform will monitor market conditions and automatically rebalances my portfolio upon my approval");
            Q5A3Replacement.setText("Someone will call me monthly for a performance review");
            answer4.setText("");

        } else {
            //Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();

            FragmentTransaction ft = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            SummaryFragment sFragment = new SummaryFragment();

            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("answer", userAnswer );
            sFragment.setArguments(bundle);
            ft.add(R.id.container, sFragment);
            ft.addToBackStack("Summary");
            ft.commit();
        }
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

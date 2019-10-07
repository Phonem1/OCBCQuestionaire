package com.aidentsl.anewtech.ocbcquestionaire;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qihancloud.opensdk.function.beans.EmotionsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



public class QFragmentV2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<QueAns> qa = new ArrayList();

    private TextView Q;
    private TextView A1;
    private TextView A2;
    private TextView A3;
    private TextView A4;
    private ImageView back;

    private int qNumber = 0;
    private ArrayList<Integer> userAns = new ArrayList();
    private int cas = 0;
    private int ca = 0;

    Context mContext;

    private Handler mhandler;
    private Runnable r;

    public QFragmentV2() {
        // Required empty public constructor

        mhandler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
            }
        };

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QFragmentV2.
     */
    // TODO: Rename and change types and number of parameters
    public static QFragmentV2 newInstance(String param1, String param2) {
        QFragmentV2 fragment = new QFragmentV2();
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

        if(qa.size()==0)AddQueAns();
        startHandler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q2, container, false);

        Q = view.findViewById(R.id.Que);
        A1 = view.findViewById(R.id.AnsOne);
        A2 = view.findViewById(R.id.AnsTwo);
        A3 = view.findViewById(R.id.AnsThree);
        A4 = view.findViewById(R.id.AnsFour);
        back = view .findViewById(R.id.back);

        A1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ToCheckAnswers(v,1);
            }
        });

        A2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ToCheckAnswers(v,2);
            }
        });

        A3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ToCheckAnswers(v,3);
            }
        });

        A4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ToCheckAnswers(v,4);
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userAns.clear();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
            }
        });

        ca = ChangeQuestions(qNumber);

        return view;
    }

    private void ToCheckAnswers(View v, int i){
        onUserInteraction();

        boolean check = false;
        if(i == ca) {
            check = true;
        }

        String answer = "";
        String correntAns = "";

        switch (i){
            case 1:
                answer = A1.getText().toString();
                break;
            case 2:
                answer = A2.getText().toString();
                break;
            case 3:
                answer = A3.getText().toString();
                break;
            case 4:
                answer = A4.getText().toString();
                break;
        }

        if(!check){
            if(ca == 1){
                correntAns = A1.getText().toString();
            }
            if(ca == 2){
                correntAns = A2.getText().toString();
            }
            if(ca == 3){
                correntAns = A3.getText().toString();
            }
            if(ca == 4){
                correntAns = A4.getText().toString();
            }
        }

        final AlertDialog a = new AlertDialog.Builder(v.getContext()).create();
        a.setCancelable(false);
        a.setTitle("Correct Answer");
        if(check){
            ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.SMILE);
            ((MainActivity)mContext).speechManager.startSpeak("it's correct.");
            a.setMessage(answer + "\n" + "Your answer is correct.");
            cas = cas + 1;
        } else {
            ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.CRY);
            ((MainActivity)mContext).speechManager.startSpeak("Incorrect. The answer is " + correntAns);
            a.setMessage(answer + "\n" + "Your answer is incorrect. \nCorrect Answer is " + correntAns);
        }

        a.show();
        if(a.isShowing()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // execute yor code
                    new CountDownTimer(3000, 1000) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            a.dismiss();
                            qNumber = (qNumber+1);
                            ca = ChangeQuestions(qNumber);

                            if(qNumber == 8) {
                                ShowFinalScore();
                            }
                        }

                    }.start();
                }
            }, 0);
        }

    }

    private void ShowFinalScore(){
       // if(qNumber > 7) {

            String score;
            if(cas == 8){
                ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.LAUGHTER);
                ((MainActivity)mContext).speechManager.startSpeak("Congratulation! Perfect Score!");
                score = cas + "/8 \n Perfect Score!";
            } else {
                ((MainActivity)mContext).systemManager.showEmotion(EmotionsType.CRY);
                ((MainActivity)mContext).speechManager.startSpeak("Good Try!");
                score = cas + "/8 \n Good Try!";
            }

            final AlertDialog aa = new AlertDialog.Builder(this.getContext()).create();
            aa.setCancelable(false);
            aa.setTitle("Your score is");
            aa.setMessage(score + "");
            aa.show();
            if(aa.isShowing()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // execute yor code
                        new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {

                            }
                            public void onFinish() {
                                cas = 0;
                                ca = 0;
                                qa.clear();
                                aa.dismiss();
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                                    fm.popBackStack();
                                }
                            }

                        }.start();
                    }
                }, 0);
            }
      //  }
    }

    private int ChangeQuestions(int qn){
        if(qn < 8){
            QueAns local =  qa.get(qn);
            Q.setText(local.Question);
            A1.setText(local.AnswerOne);
            A2.setText(local.AnswerTwo);
            A3.setText(local.AnswerThree);
            A4.setText(local.AnswerFour);
            return  local.Answer;
        }
        return 0;
    }

    private void AddQueAns(){
        qa.add(new QueAns(1,"The ____is the largest organ in the human body", "Brains","Skin","Kidney","Intestine",2));
        qa.add(new QueAns(2,"How many percent of Singapore's population is obese?", "10%","20%","30%",">30%",4));
        qa.add(new QueAns(3,"Which of the following activity can increase your blood flow by 20%?", "Thinking","Laughing","Talking","Eating",2));
        qa.add(new QueAns(4,"Singapore is ranked the ____ healthiest country in the world.", "2nd","4th","8th","10th",2));
        qa.add(new QueAns(5,"What is the average lifespan of a Singaporean?", "70 years","78 years","83 years","88 years",3));
        qa.add(new QueAns(6,"Which of the following activities can help in boosting metabolism?", "Drinking water","Doing a high-intensity \n workout","Getting a good night's \nsleep","All of the above",4));
        qa.add(new QueAns(7,"Which of the following organ has the most active muscle?", "Eye","Brain","Heart","Lungs",1));
        qa.add(new QueAns(8,"Which of the following combination \ncan double the risk of early death?", "Smoking + \nhigh alcohol intake","Being physically inactive + \ntoo much sleep","Being physically inactive + \ntoo much sitting","All of the above",4));

    }

    public void stopHandler() {
        mhandler.removeCallbacks(r);
    }

    public void startHandler() {
        mhandler.postDelayed(r, 40 * 1000);

    }

    public void onUserInteraction() {
        stopHandler();
        startHandler();
    }

    @Override
    public void onPause() {
        stopHandler();
        super.onPause();
    }

    @Override
    public  void onResume() {
        super.onResume();
        startHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopHandler();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;

    }
    public class QueAns{
        int QuestionNum;
        String Question;
        String AnswerOne;
        String AnswerTwo;
        String AnswerThree;
        String AnswerFour;
        int Answer;
        public QueAns(int qn,String q, String a1, String a2, String a3, String a4, int ans){
            QuestionNum = qn;
            Question = q;
            AnswerOne = a1;
            AnswerTwo = a2;
            AnswerThree = a3;
            AnswerFour = a4;
            Answer = ans;
        }
    }

}


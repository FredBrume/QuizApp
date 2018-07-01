package com.pinetech.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTitle, questionLbl, next, prev;
    private AppCompatRadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private RadioGroup radioGroup;

    private List<String> options;
    private String title;
    private String question;


    private int pageCount = 1;
    private int quizScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        radioGroup = findViewById(R.id.radioGroup);
        questionTitle = findViewById(R.id.title);
        questionLbl = findViewById(R.id.question);
        radioButtonA = findViewById(R.id.radioA);
        radioButtonB = findViewById(R.id.radioB);
        radioButtonC = findViewById(R.id.radioC);
        radioButtonD = findViewById(R.id.radioD);

        initializeQuestion();

        next.setOnClickListener(this);
        prev.setOnClickListener(this);


    }


    private void displayQuestionDetails(String title, String question, List<String> options) {

        questionTitle.setText(title);
        questionLbl.setText(question);

        radioButtonA.setText(options.get(0));
        radioButtonB.setText(options.get(1));
        radioButtonC.setText(options.get(2));
        radioButtonD.setText(options.get(3));

    }

    private void initializeQuestion() {

        switch (pageCount) {
            case 1:

                title = getString(R.string.question1title);
                question = getString(R.string.question1);

                options = new ArrayList<>(4);

                options.add(getString(R.string.answerMario));
                options.add(getString(R.string.answerDonkeyKong));
                options.add(getString(R.string.answerBorderlands));
                options.add(getString(R.string.answerBioShock));

                displayQuestionDetails(title, question, options);

                break;

            case 2:

                title = getString(R.string.question2title);
                question = getString(R.string.question2);

                options = new ArrayList<>(4);

                options.add(getString(R.string.answer1989));
                options.add(getString(R.string.answer1979));
                options.add(getString(R.string.answer1999));
                options.add(getString(R.string.answer1899));


                displayQuestionDetails(title, question, options);

                break;
        }

    }

    private void checkQuestionAnswer() {

        int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonID != -1) {

            AppCompatRadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
            String selectedRadioButtonText = selectedRadioButton.getText().toString();

            switch (pageCount) {
                case 1:
                    if (selectedRadioButtonText.equals(getString(R.string.answerMario))) {
                        quizScore++;
                    }

                    pageCount++;
                    initializeQuestion();  //method call
                    break;

                case 2:

                    if (selectedRadioButtonText.equals(getString(R.string.answer1989))) {
                        quizScore++;
                    }

                    pageCount++;
                    initializeQuestion();
                    break;
                default:
            }
        }

    }

    @Override
    public void onClick(View view) {

        switch (getResources().getResourceEntryName(view.getId())) {

            case "next":
                checkQuestionAnswer();

                break;
            case "prev":
                break;

            default:
        }
    }
}

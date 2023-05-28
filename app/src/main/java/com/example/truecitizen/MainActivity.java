package com.example.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truecitizen.model.Question;

public class MainActivity extends AppCompatActivity {

    TextView questionTxtView;
    Button trueBtn,falseBtn,prevBtn,nextBtn;
    int currentQuestionIndex=0;

    private Question[] questions = new Question[]{
      new Question(R.string.question_amendments,false),
      new Question(R.string.question_constitution,true),
      new Question(R.string.question_declaration,true),
      new Question(R.string.question_independence_rights,true),
      new Question(R.string.question_religion,true),
      new Question(R.string.question_government,false),
      new Question(R.string.question_government_feds,false),
      new Question(R.string.question_government_senators,false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTxtView = findViewById(R.id.questionTextView);
        trueBtn = findViewById(R.id.trueBtn);
        falseBtn = findViewById(R.id.falseBtn);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);

        questionTxtView.setText(questions[currentQuestionIndex].getAnswerResId());

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
                updateQuestion();
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestionIndex > 0){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questions.length;
                    updateQuestion();
                }
            }
        });
    }

    private void checkAnswer(boolean userChoseCorrect){
        boolean answerIsCorrect = questions[currentQuestionIndex].isAnswerTrue();
        if(answerIsCorrect == userChoseCorrect){
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion() {
        questionTxtView.setText(questions[currentQuestionIndex].getAnswerResId());
    }
}
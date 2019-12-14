package mosfeqanik01.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<String> answers = new ArrayList<String>();
    TextView SumTextView;
    int locationOfCorrectAnswer;
    TextView resulTextView;
    int Score;
    int numberOfQuestions;
    TextView scoreTextView;
    Random rand = new Random();
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView timerTextView;
    Button playButton;
    ConstraintLayout gameLayout;

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resulTextView.setText("Correct");
            Score++;
        }else{
            resulTextView.setText("Wrong");
        }
        numberOfQuestions++;
        scoreTextView.setText(Score + "/" + numberOfQuestions);
        newQuestion();

    }
    public void newQuestion(){
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        locationOfCorrectAnswer = rand.nextInt(4) ;
        SumTextView.setText(a + " + " + b);
        answers.clear();
        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answers.add(String.valueOf(a+b));
            }else{
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == (a+b)){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(String.valueOf(wrongAnswer));
            }
        }
        button1.setText(answers.get(0));
        button2.setText(answers.get(1));
        button3.setText(answers.get(2));
        button4.setText(answers.get(3));
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.scoreTextView));
    }
    public void playAgain(View view){
        Score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Score + "/" + numberOfQuestions);
        playButton.setVisibility(View.INVISIBLE);
        resulTextView.setText("");
        newQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText((millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {
                resulTextView.setText("Done");
                playButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton= findViewById(R.id.goButton);
        SumTextView= findViewById(R.id.SumTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        resulTextView = findViewById(R.id.resulTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        playButton = findViewById(R.id.playButton);
        gameLayout =findViewById(R.id.gameLayout);
        newQuestion();
        timerTextView =findViewById(R.id.timerTextView);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}

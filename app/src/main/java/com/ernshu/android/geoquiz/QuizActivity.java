package com.ernshu.android.geoquiz;
//this acts as the controller.
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
// import java.security.PublicKey;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton; //remember to use lowercase for using special syntax commands.
    private Button mFalseButton;// step one declare the memory to hold button.
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index"; // added string to hold index of what question.

// Question[] is created from the class and pointer is aimed at new object created.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america,true),
            new Question(R.string.question_asia, true),
    };

private int mCurrentIndex = 0;

private void updateQuestion() {
    int question = mQuestionBank[mCurrentIndex].getTextResId();
    mQuestionTextView.setText(question);
}

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                int messageResId = 0;
                if (userPressedTrue == answerIsTrue){
                    messageResId = R.string.correct_toast;
                } else {
                    messageResId = R.string.incorrect_toast;
                }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //int Question = mQuestionBank[mCurrentIndex].getTextResId();
        //mQuestionTextView.setText(Question);


        mTrueButton = (Button) findViewById(R.id.true_button); //  step two make it equal to ID in memory



        mTrueButton.setOnClickListener(new View.OnClickListener() { // step three create producer or action.
            @Override
            public void onClick(View v)
            {
               /* Toast.makeText(QuizActivity.this,    // step four create a toast action inside the method.
                                R.string.incorrect_toast,
                                Toast.LENGTH_SHORT).show();    */
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               /* Toast.makeText(QuizActivity.this,
                       R.string.correct_toast,
                       Toast.LENGTH_SHORT).show(); */
                checkAnswer(false);
            }
        });
mNextButton = (ImageButton) findViewById(R.id.next_button); /* I need to find more information on what
        putting the information on parenthesis around Button means. */
mNextButton.setOnClickListener(new View.OnClickListener() {
   @Override
     public void onClick(View v) {
     mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
     // int question = mQuestionBank[mCurrentIndex].getTextResId();
    //  mQuestionTextView.setText(question);
       // check the state of savedInstance not equal to null get index.
       updateQuestion();
                                           }
                                       });
        updateQuestion();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
// adding tag in Cycle methods.

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

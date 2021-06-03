package com.example.mad12.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad12.R;
import com.example.mad12.model.entity.Question;
import com.example.mad12.model.entity.TestWrapper2;
import com.example.mad12.viewmodel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestingOnlineActivity extends AppCompatActivity {
    private TestViewModel testViewModel;
    TextView txtNext, txtBack, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    RadioGroup rdGroup;
    RadioButton rdA, rdB, rdC, rdD;
    TextView txtQuestion, txtQuestionOrder, txtQuestionID;
    private static int questionOrder;//Thứ tự câu hỏi
    private static List<Question> questionList;//Danh sách các câu hỏi
    private static List<Integer> keySelectedList;//Danh sách các đáp án lựa chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_online);
        initView();
        initVariable();
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        getDataBundle();
        //Event next question
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lưu lại đáp án được lựa chọn ở câu hỏi trước
                saveKeySelected();
                questionOrder++;
                if (questionOrder == 1) txtBack.setVisibility(View.VISIBLE);
                Log.d("AAAAAAAAAAA", "onClick: " + questionOrder + " A " + questionList.size());
                //Set dữ liệu câu hỏi tiếp theo
                setDataQuestion();
            }
        });
        //Event BACK   question
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lưu lại đáp án được lựa chọn ở câu hỏi trước
                saveKeySelected();
                questionOrder--;
                //Set dữ liệu câu hỏi tiếp theo
                setDataQuestion();
            }
        });
    }

    //Lưu lại đáp án được lựa chọn ở câu hỏi trước
    private void saveKeySelected() {
        //Nếu câu hỏi nằm trong giới hạn danh sách thì lưu trạng thái lựa chọn đáp án
        if (questionOrder >= 0 && questionOrder < questionList.size()) {
            if (rdA.isChecked()) keySelectedList.add(questionOrder, 1);
            if (rdB.isChecked()) keySelectedList.add(questionOrder, 2);
            if (rdC.isChecked()) keySelectedList.add(questionOrder, 3);
            if (rdD.isChecked()) keySelectedList.add(questionOrder, 4);
        }
    }

    //Bắt sự kiện khi click nút tạo giáo án
    private void showDialogSubmit() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_testingonline_submit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txtCancel = (TextView) dialog.findViewById(R.id.textview_dialogtestingonline_cancel);
        TextView txtOk = (TextView) dialog.findViewById(R.id.textview_dialogtestingonline_ok);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Submit test and check result test
                submitTest();
            }
        });

        //Tắt dialog khi click bên ngoài
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //Submit test and check result test
    private void submitTest() {
        int numberCorrect = 0;
        for (int i = 0; i < questionList.size(); i++) {
            int key = questionList.get(i).getResult();
            if (key == keySelectedList.get(i)) {
                numberCorrect++;
            }
        }
        Toast.makeText(this, numberCorrect + " correct", Toast.LENGTH_SHORT).show();
    }

    //Set dữ liệu câu hỏi lên giao diện
    private void setDataQuestion() {
        //Nếu câu hỏi là câu 1 và click back thì ẩn button back
        if (questionOrder < 0) {
            txtBack.setVisibility(View.INVISIBLE);
            questionOrder = 0;
        } else if (questionOrder >= questionList.size()) {
            //Nếu câu hỏi đã là câu cuối mà vẫn click-> Ẩn nút next+báo cáo nộp bài
            if (questionOrder == questionList.size()) {
                txtNext.setVisibility(View.INVISIBLE);
                questionOrder = questionList.size() - 1;
                showDialogSubmit();
            }
        } else {
            if (questionOrder == questionList.size() - 2) txtNext.setVisibility(View.VISIBLE);
            if (questionOrder == 1) txtBack.setVisibility(View.VISIBLE);
            try {
                txtQuestion.setText(questionList.get(questionOrder).getDescription());
                txtQuestionID.setText(questionList.get(questionOrder).getQuestionID() + "");
                txtQuestionOrder.setText(questionOrder + 1 + "");
                //Set dữ liệu các đáp án
                txtAnswerA.setText(questionList.get(questionOrder).getAnswer().get(0));
                txtAnswerB.setText(questionList.get(questionOrder).getAnswer().get(1));
                txtAnswerC.setText(questionList.get(questionOrder).getAnswer().get(2));
                txtAnswerD.setText(questionList.get(questionOrder).getAnswer().get(3));
                //Reset check radiogroup
                rdGroup.clearCheck();
            } catch (Exception e) {
                Log.d("setDataNextQuestion", "setDataNextQuestion: " + e);
            }

        }
    }

    private void getDataBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("com.example.mad12.view.adapter.TestOnlineAdapter");
        if (bundle != null) {
            String testID = bundle.getString("TestID");
            //Lấy danh sách các bài test trong kì thi
            getTestOnline(testID);
        }
    }

    //Get test from api
    private void getTestOnline(String testID) {
        testViewModel.getTestWrapper2(testID).observe(this, new Observer<List<TestWrapper2>>() {
            @Override
            public void onChanged(List<TestWrapper2> testWrapper2s) {
                try {
                    questionList.clear();
                    for (int i = 0; i < testWrapper2s.size(); i++) {
                        for (int j = 0; j < testWrapper2s.get(i).getQuestionList().size(); j++) {
                            questionList.add(testWrapper2s.get(i).getQuestionList().get(j));
                        }
                    }
                    //Set thông tin câu hỏi lên giao diện
                    setDataQuestion();
                } catch (Exception e) {
                    Log.d("GetTestOnline", "onChanged: " + e);
                }
            }
        });
    }

    private void initVariable() {
        questionOrder = 0;
        questionList = new ArrayList<>();
        keySelectedList = new ArrayList<>();
    }

    private void initView() {
        txtNext = (TextView) findViewById(R.id.textview_testingonline_next);
        txtBack = (TextView) findViewById(R.id.textview_testingonline_back);
        rdGroup = (RadioGroup) findViewById(R.id.radiogroup_testingonline);
        rdA = (RadioButton) findViewById(R.id.radiobutton_testingonline_A);
        rdB = (RadioButton) findViewById(R.id.radiobutton_testingonline_B);
        rdC = (RadioButton) findViewById(R.id.radiobutton_testingonline_C);
        rdD = (RadioButton) findViewById(R.id.radiobutton_testingonline_D);
        txtQuestion = (TextView) findViewById(R.id.textview_testingonline_question);
        txtQuestionOrder = (TextView) findViewById(R.id.textview_testingonline_questionorder);
        txtQuestionID = (TextView) findViewById(R.id.textview_testingonline_questioncode);
        txtAnswerA = (TextView) findViewById(R.id.textview_testingonline_answerA);
        txtAnswerB = (TextView) findViewById(R.id.textview_testingonline_answerB);
        txtAnswerC = (TextView) findViewById(R.id.textview_testingonline_answerC);
        txtAnswerD = (TextView) findViewById(R.id.textview_testingonline_answerD);
    }

}

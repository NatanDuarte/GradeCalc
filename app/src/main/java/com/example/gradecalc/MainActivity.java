package com.example.gradecalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gradecalc.model.Grade;

public class MainActivity extends AppCompatActivity {

    public static final String APP_TITLE_PT_BR = "Calculadora de médias do semestre";
    private EditText firstExamGradeField;
    private EditText firstExamWeightField;
    private EditText secondExamGradeField;
    private EditText secondExamWeightField;
    private EditText quizGradeField;
    private EditText quizWeightField;
    private EditText othersGradeField;
    private EditText othersWeightField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(APP_TITLE_PT_BR);

        initializeFields();
        handleCalculateButton();
    }

    private void handleCalculateButton() {
        Button calculateButton = findViewById(R.id.activity_main_button_calculate);
        calculateButton.setOnClickListener(view -> {
            if (noEmptyFields())
                calculateGrade();
        });
    }

    private void calculateGrade() {
        Grade grade = getGrade();
        double weightedMean = grade.weightedMean();

        @SuppressLint("DefaultLocale")
        String output = String.format("Nota: %.2f", weightedMean);

        TextView resultText = findViewById(R.id.activity_main_text_result);
        resultText.setText(output);
        thrownToast(weightedMean);
    }

    private boolean noEmptyFields() {
        return !(firstExamGradeField.getText().toString().isEmpty() ||
                firstExamWeightField.getText().toString().isEmpty() ||
                secondExamGradeField.getText().toString().isEmpty() ||
                secondExamWeightField.getText().toString().isEmpty() ||
                quizGradeField.getText().toString().isEmpty() ||
                quizWeightField.getText().toString().isEmpty() ||
                othersGradeField.getText().toString().isEmpty() ||
                othersWeightField.getText().toString().isEmpty());
    }

    private void thrownToast(double weightedSum) {
        String toastMessage;
        toastMessage = getString(weightedSum);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private String getString(double weightedSum) {
        String toastMessage;
        if (weightedSum >= 7) {
            toastMessage = "passou 🍕";
        } else if (weightedSum >= 5) {
            toastMessage = "em exame 😒";
        } else {
            toastMessage = "reprovado 😭";
        }
        return toastMessage;
    }

    @NonNull
    private Grade getGrade() {
        Double firstExamGrade = Double.parseDouble(firstExamGradeField.getText().toString());
        Double firstExamWeight = Double.parseDouble(firstExamWeightField.getText().toString());
        Double secondExamGrade = Double.parseDouble(secondExamGradeField.getText().toString());
        Double secondExamWeight = Double.parseDouble(secondExamWeightField.getText().toString());
        Double quizGrade = Double.parseDouble(quizGradeField.getText().toString());
        Double quizWeight = Double.parseDouble(quizWeightField.getText().toString());
        Double othersGrade = Double.parseDouble(othersGradeField.getText().toString());
        Double othersWeight = Double.parseDouble(othersWeightField.getText().toString());

        return new Grade(firstExamGrade, firstExamWeight, secondExamGrade, secondExamWeight,
                quizGrade, quizWeight, othersGrade, othersWeight);
    }

    private void initializeFields() {
        firstExamGradeField = findViewById(R.id.activity_main_exam_1_grade);
        firstExamWeightField = findViewById(R.id.activity_main_exam_1_weight);
        secondExamGradeField = findViewById(R.id.activity_main_exam_2_grade);
        secondExamWeightField = findViewById(R.id.activity_main_exam_2_weight);
        quizGradeField = findViewById(R.id.activity_main_quiz_grade);
        quizWeightField = findViewById(R.id.activity_main_quiz_weight);
        othersGradeField = findViewById(R.id.activity_main_others_grade);
        othersWeightField = findViewById(R.id.activity_main_others_weight);
    }
}
//Assignment name: ITIS/ITCS 5180 Mobile Application Development Homework 01
//File Name:BMICalculator
// Group no :21
//Names: Nia Ibrahim
//       Janhvi Chitnis
//       Abhishek Tanwer

package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edit_weight;
    private EditText edit_feet;
    private EditText edit_inches;
    private Button btn_calculate;
    private TextView text_resultBMI;
    private TextView text_result;
    private ConstraintLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");
        edit_weight = findViewById(R.id.edit_weight);
        edit_feet = findViewById(R.id.edit_feet);
        edit_inches = findViewById(R.id.edit_inches);
        btn_calculate = findViewById(R.id.btn_calculate);
        text_result = findViewById(R.id.text_result);
        text_resultBMI = findViewById(R.id.text_resultBMI);
        mCLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    hideKeyboad(view);
                    String temp_weight = edit_weight.getText().toString();
                    String temp_feet = edit_feet.getText().toString();
                    String temp_inches = edit_inches.getText().toString();
                    if (temp_feet == null || temp_feet.equals("")) {
                        edit_feet.setError("Please Insert Height in Feets");
                    }
                    if (temp_weight == null || temp_weight.equals("") || temp_weight.equals(".")){
                        edit_weight.setError("Please Insert Appropriate Weight");
                    }
                    if (temp_inches == null || temp_inches.equals("") || Float.parseFloat(temp_inches) > 12) {
                        edit_inches.setError("Please Insert Appropriate Inches");
                        throw new CustomException();
                    }

                    float temp_totalInches = Float.parseFloat(temp_inches) + (Float.parseFloat(temp_feet) * 12);
                    if(temp_totalInches==0.00){
                        throw new CustomException();
                    }
                    else {
                        Float calculated_bmi = (Float.parseFloat(temp_weight) / (temp_totalInches * temp_totalInches)) * 703;
                        Toast toast = Toast.makeText(getApplicationContext(), "BMI Calculated", Toast.LENGTH_SHORT);
                        toast.show();

                        DecimalFormat f = new DecimalFormat("##.00");
                        text_resultBMI.setText("Your BMI: " + f.format(calculated_bmi));
                        if (calculated_bmi <= 18.5) {
                            text_result.setText("You are Underweight");
                        } else if (calculated_bmi > 18.5 && calculated_bmi <= 24.9) {
                            text_result.setText("You are Normal Weight");
                        } else if (calculated_bmi > 24.9 && calculated_bmi <= 29.9) {
                            text_result.setText("You are Over Weight");
                        } else if (calculated_bmi >= 30) {
                            text_result.setText("You are Obese");
                        }
                    }

                }
                catch(CustomException ex) {
                    Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage("Invalid Input"), Toast.LENGTH_SHORT);
                    toast.show();
                    text_result.setText("");
                    text_resultBMI.setText("");
                }
                catch (Exception ex) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT);
                    toast.show();
                    text_result.setText("");
                    text_resultBMI.setText("");
                }
            }
        });
        mCLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboad(view);

            }
        });
    }

    public void hideKeyboad(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

package com.example.student.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int MAX_AMOUNT_OF_GRADES = 15;
    private static final int MIN_AMOUNT_OF_GRADES = 5;
    private Button gradesButton;
    private EditText nameET;
    private EditText surnameET;
    private EditText amountOfGradesET;
    private TextWatcher userDataTextWatcher;
    private TextView averageGradeTV;
    private Button gradeOKButton, gradeNotOKButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = (EditText) findViewById(R.id.nameET);
        surnameET = (EditText) findViewById(R.id.surnameET);
        amountOfGradesET = (EditText) findViewById(R.id.amountOfGradesET);
        userDataTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(correctData())
                    gradesButton.setVisibility(View.VISIBLE);
                else
                    gradesButton.setVisibility(View.INVISIBLE);
            }
        };



        nameET.addTextChangedListener(userDataTextWatcher);
        surnameET.addTextChangedListener(userDataTextWatcher);
        amountOfGradesET.addTextChangedListener(userDataTextWatcher);

        nameET.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (isEditTextEmpty(nameET))
                            shortToastMessage("Wprowadź imię");
                    }
                }
        );

        surnameET.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(isEditTextEmpty(surnameET)){
                            shortToastMessage("Wprowadź nazwisko");
                        }
                    }
                }
        );

        amountOfGradesET.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (isEditTextEmpty(amountOfGradesET))
                            shortToastMessage("Wprowadź liczbę ocen");
                        else if (isAmountOfGradesNotCorrect()) {
                            shortToastMessage("Liczba ocen musi być z przedziału <5,15>");
                        }
                    }
                }
        );



        gradesButton = (Button) findViewById(R.id.gradesButton);
        gradesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(correctData()) {
                            Intent gradesWindow = new Intent(MainActivity.this, SetGradesActivity.class);
                            gradesWindow.putExtra("amountOfGrades", Integer.parseInt(String.valueOf(amountOfGradesET.getText())));
                            startActivityForResult(gradesWindow, 1);
                        }

                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int result, Intent data) {
        super.onActivityResult(requestCode, result, data);

        gradeOKButton = (Button) findViewById(R.id.gradeOKButton);
        gradeNotOKButton = (Button) findViewById(R.id.gradeNotOKButton);
        averageGradeTV = (TextView) findViewById(R.id.averageGradeTV);

        if(result==RESULT_OK)
        {
            Bundle gradesData = data.getExtras();
            double averageGrade = gradesData.getDouble("averageGrade");
            nameET.setEnabled(false);
            surnameET.setEnabled(false);
            amountOfGradesET.setEnabled(false);
            gradesButton.setVisibility(View.INVISIBLE);
            averageGradeTV.setText("Twoja średnia to: "+maxTwoNumbersAfterDot(averageGrade));
            averageGradeTV.setVisibility(View.VISIBLE);
            if(averageGrade>=3) {
                gradeOKButton.setVisibility(View.VISIBLE);
                gradeOKButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                shortToastMessage("Gratulacje! Otrzymujesz zaliczenie!");
                                finish();
                            }
                        }
                );
            }
            else {

                gradeNotOKButton.setVisibility(View.VISIBLE);
                gradeNotOKButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                shortToastMessage("Wysyłam podanie o zaliczenie warunkowe");
                                finish();
                            }
                        }
                );
            }

        }
    }


    //metoda konweruje liczbę double na zaokrągloną w dół do 2 miejsc po przecinku
    private Double maxTwoNumbersAfterDot(double x) {
        x = Math.floor(x*100) / 100;
        return x;
    }


    //jeśli dane wpisane poprawnie metoda zwraca 'true', jeśli niepoprawnie 'false'
    private boolean correctData() {
        if(isEditTextEmpty(nameET))
            return false;
        else if(isEditTextEmpty(surnameET))
            return false;
        else if( isEditTextEmpty(amountOfGradesET))
            return false;
        else if( isAmountOfGradesNotCorrect() )
            return false;
        else
            return true;
    }

    //metody wyświetlają komunikaty toast o podanej treści w bieżącym oknie
    private void shortToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    //sprawdza, czy pole tekstowe nie jest puste
    private boolean isEditTextEmpty(EditText x) {
        return TextUtils.isEmpty(String.valueOf(x.getText()));
    }

    //sprawdza, czy wpisana liczba ocen mieści się założonym przedziale ('true' jeśli liczba niepoprawna)
    private boolean isAmountOfGradesNotCorrect(){
        return Integer.parseInt(String.valueOf(amountOfGradesET.getText())) < MIN_AMOUNT_OF_GRADES
                || Integer.parseInt(String.valueOf(amountOfGradesET.getText())) > MAX_AMOUNT_OF_GRADES;
    }

}

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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button gradesButton;
    private EditText nameET;
    private EditText surnameET;
    private EditText amountOfGradesET;
    private TextWatcher userDataTextWatcher;


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
                        else if (isAmountOfGradesCorrect()) {
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
                            startActivity(gradesWindow);
                        }

                    }
                }
        );

    }

    //jeśli dane wpisane poprawnie metoda zwraca TRUE, jeśli niepoprawnie FALSE
    private boolean correctData() {
        if(isEditTextEmpty(nameET))
            return false;
        else if(isEditTextEmpty(surnameET))
            return false;
        else if( isEditTextEmpty(amountOfGradesET))
            return false;
        else if( isAmountOfGradesCorrect() )
            return false;
        else
            return true;
    }

    //wyświetla komunikat toast o podanej treści w bieżącym oknie
    private void shortToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    //sprawdza
    private boolean isEditTextEmpty(EditText x) {
        return TextUtils.isEmpty(String.valueOf(x.getText()));
    }

    private boolean isAmountOfGradesCorrect(){
        return Integer.parseInt(String.valueOf(amountOfGradesET.getText())) < 5
                || Integer.parseInt(String.valueOf(amountOfGradesET.getText())) > 15;
    }

}

package com.example.student.test1;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class SetGradesActivity extends AppCompatActivity {

    ListView gradesListLV;
    private ArrayList<Grade> gradesData;
    Button readyButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_grades);
        gradesListLV = (ListView) findViewById(R.id.gradesList);
        readyButton = (Button) findViewById(R.id.readyButton);

        //stworzenie listy obiektów ocen
        final int amountOfGrades = getIntent().getIntExtra("amountOfGrades", 0);
        gradesData = new ArrayList<Grade>();
        for (int i = 0; i < amountOfGrades; i++) {
            gradesData.add(new Grade(i));
        }

        setGradeAdapter();
        readyButtonListener();
    }


    //załączenie adaptera
    private void setGradeAdapter() {
        GradesAdapter adapter = new GradesAdapter(this, gradesData);
        gradesListLV.setAdapter(adapter);
    }

    private void readyButtonListener() {
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyButtonClick();
            }
        });
    }

    //obsługa wciśnięcia przycisku "Gotowe":
    private void readyButtonClick() {
        if (allGradesHaveValue()) {
            Bundle gradesData = new Bundle();
            gradesData.putDouble("averageGrade", calculateAverageGrade());
            Intent gradesOK=new Intent();
            gradesOK.putExtras(gradesData);
            setResult(RESULT_OK, gradesOK);
            finish();
        }
        else
            shortToastMessage("Wprowadź wszystkie oceny");
    }


    public boolean allGradesHaveValue() {
        for (Grade grade : gradesData ) {
            if (!grade.hasValue())
                return false;
        }
        return true;

    }

    private double calculateAverageGrade() {
        int sum =0;
        for(Grade grade : gradesData)
            sum+=grade.getValue();
        return (double)sum/gradesData.size();
    }

    //wyświetla komunikat toast o podanej treści w bieżącym oknie
    private void shortToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }





}


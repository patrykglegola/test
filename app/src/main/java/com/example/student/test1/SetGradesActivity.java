package com.example.student.test1;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.IdRes;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class SetGradesActivity extends AppCompatActivity {

    ListView setGradesList;
    private ArrayList<Grade> gradesData;
    Button readyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_grades);
        setGradesList = (ListView) findViewById(R.id.gradesList);
        readyButton = (Button) findViewById(R.id.readyButton);

        final int amountOfGrades = getIntent().getIntExtra("amountOfGrades", 0);
        gradesData = new ArrayList<Grade>();
        for (int i = 0; i < amountOfGrades; i++) {
            gradesData.add(new Grade(i));
        }
        GradesAdapter adapter = new GradesAdapter(this, gradesData);
        setGradesList.setAdapter(adapter);

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allGradesHaveValue()) {
                    Bundle gradesData = new Bundle();
                    gradesData.putDouble("averageGrade", calculateAverageGrade(amountOfGrades));
                    Intent gradesOK=new Intent();
                    gradesOK.putExtras(gradesData);
                    setResult(RESULT_OK, gradesOK);
                    finish();
                }
                else
                    shortToastMessage("Wprowadź wszystkie oceny");
            }
        });
    }


    public boolean allGradesHaveValue() {
        for (Grade grade : gradesData ) {
            if (!grade.hasValue())
                return false;
        }
        return true;

    }

    private double calculateAverageGrade(int amountOfGrades) {
        int sum =0;
        for(Grade grade : gradesData)
            sum+=grade.getValue();
        return (double)sum/amountOfGrades;
    }

    //wyświetla komunikat toast o podanej treści w bieżącym oknie
    private void shortToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }





}


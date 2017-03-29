package com.example.student.test1;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

public class SetGradesActivity extends AppCompatActivity {

    ListView setGradesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_grades);
        setGradesList = (ListView) findViewById(R.id.gradesList);
        int amountOfGrades = getIntent().getIntExtra("amountOfGrades", 0);
        int[] grade = new int[amountOfGrades];


    }

}
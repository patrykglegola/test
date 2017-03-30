package com.example.student.test1;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.IdRes;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class SetGradesActivity extends AppCompatActivity {

    ListView setGradesList;
    private ArrayList<Grade> gradesData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_grades);
        setGradesList = (ListView) findViewById(R.id.gradesList);
        int amountOfGrades = getIntent().getIntExtra("amountOfGrades", 0);
        gradesData=new ArrayList<Grade>();
        for(int i=0;i<amountOfGrades;i++){
            gradesData.add(new Grade(i));
        }
        GradesAdapter adapter = new GradesAdapter(this, gradesData);
        setGradesList.setAdapter(adapter);


    }

}


package com.example.student.test1;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;


public class GradesAdapter extends ArrayAdapter<Grade> {
    private List<Grade> gradesList;
    private Activity context;

    public GradesAdapter(Activity context, List<Grade> gradesList ) {
        super(context, R.layout.grade_layout, gradesList);
        this.context = context;
        this.gradesList = gradesList;

    }



    @Override
    public View getView(final int position, View gradeView, ViewGroup parent) {

        View view = null;
        if(gradeView==null) {
            LayoutInflater gradeForm = context.getLayoutInflater();
            view = gradeForm.inflate(R.layout.grade_layout, parent, false);

            RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
            gradesRadioGroup.setTag(gradesList.get(position));
            gradesRadioGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                            Grade element = (Grade) group.getTag();
                            if(checkedId== R.id.grade2RB)
                                element.setValue(2);
                            else if (checkedId==R.id.grade3RB)
                                element.setValue(3);
                            else if (checkedId== R.id.grade4RB)
                                element.setValue(4);
                            else if (checkedId== R.id.grade5RB)
                                element.setValue(5);

                        }
                    }
            );
           // gradesRadioGroup.setTag(gradesList.get(position));
        }
        else {
            view = gradeView;
            RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
            gradesRadioGroup.setTag(gradesList.get(position));
        }
        TextView label = (TextView) view.findViewById(R.id.gradeLabel);
        label.setText("Ocena "+(position+1));
        RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
        int value = gradesList.get(position).getValue();
        if (value == 2) {
            gradesRadioGroup.check(R.id.grade2RB);
        } else if (value == 3) {
            gradesRadioGroup.check(R.id.grade3RB);
        } else if (value == 4) {
            gradesRadioGroup.check(R.id.grade4RB);
        } else if (value == 5) {
            gradesRadioGroup.check(R.id.grade5RB);
        } else
            gradesRadioGroup.check(-1);

        return view;
    }
}
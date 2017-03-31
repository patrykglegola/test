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
        //tworzenie nowego wiersza
        if(gradeView==null) {
            //utworzenie layout na podstawie pliku XML
            LayoutInflater gradeForm = context.getLayoutInflater();
            view = gradeForm.inflate(R.layout.grade_layout, parent, false);

            //obsługa radio buttonów:
            RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
            gradesRadioGroup.setTag(gradesList.get(position));
            setRadioValues(gradesRadioGroup, position);
            setRadioButtonListener(gradesRadioGroup);
        }
        else {
            view = gradeView;
            RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
            gradesRadioGroup.setTag(gradesList.get(position));
        }
        //ustawienie nazw ocen do widoku
        TextView label = (TextView) view.findViewById(R.id.gradeLabel);
        label.setText("Ocena "+(position+1));

        //zaznaczenie odpowiedniego przycisku na podstawie ocen
        RadioGroup gradesRadioGroup = (RadioGroup) view.findViewById(R.id.gradesRBGroup);
        setRadioValues(gradesRadioGroup, position);


        return view;
    }

    //obsługa zmiany zaznaczonego radio buttonu:
    private void setRadioButtonListener(RadioGroup gradesRadioGroup) {
        gradesRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        setGradeValuesFromRB(group, checkedId);

                    }
                }
        );
    }

    //metoda ustawia wartości ocen zależnie od wybranej konfiguracji radio buttonów
    private void setGradeValuesFromRB(RadioGroup group, @IdRes int checkedId) {
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

    //jeśli mamy wprowadzone dane o ocenach, metoda ustawia radio buttony w odpowiednich miejscach
    public void setRadioValues(RadioGroup group, int position){
        int value = gradesList.get(position).getValue();
        if (value == 2) {
            group.check(R.id.grade2RB);
        } else if (value == 3) {
            group.check(R.id.grade3RB);
        } else if (value == 4) {
            group.check(R.id.grade4RB);
        } else if (value == 5) {
            group.check(R.id.grade5RB);
        } else
            group.check(-1);

    }



}
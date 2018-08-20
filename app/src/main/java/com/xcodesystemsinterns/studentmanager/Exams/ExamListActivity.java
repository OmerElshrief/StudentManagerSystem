package com.xcodesystemsinterns.studentmanager.Exams;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.xcodesystemsinterns.studentmanager.Database.DataBaseHelper;
import com.xcodesystemsinterns.studentmanager.Adapters.ExamCursorAdapter;
import com.xcodesystemsinterns.studentmanager.R;

public class ExamListActivity extends AppCompatActivity {
    // Find ListView to populate
    ListView lvExams;
    DataBaseHelper dataBaseHelper;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        lvExams = findViewById(R.id.lv_exams);
        floatingActionButton = findViewById(R.id.fab_exam_list);
        dataBaseHelper = new DataBaseHelper(this);
        //addDummyExams();
        Cursor examCursor = dataBaseHelper.getAllExams();
        // Setup cursor adapter using cursor from last step
        ExamCursorAdapter examCursorAdapter = new ExamCursorAdapter(this,examCursor);
        // Attach cursor adapter to the ListView
        lvExams.setAdapter(examCursorAdapter);

    }

    void addDummyExams(){
        int classID = dataBaseHelper.addClass("Class1","This is a test");
        dataBaseHelper.addExam("Exam1","20-2",classID);
    }


    public void checkExam(View view) {
        CheckOffExam newFragment = CheckOffExam.newInstance(dataBaseHelper.getClassList(),this);
        newFragment.show(getSupportFragmentManager(),null);
    }
}

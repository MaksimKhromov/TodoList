package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_NOTE = "textNote";
    private static final String PRIORITY = "priority";
    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;
    private final DataBase dataBase = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivityV1.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        linearLayoutNotes = findViewById(R.id.linearLayaoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void showNotes() {
        linearLayoutNotes.removeAllViews();
        for (Note note : dataBase.getNotes()) {
            View view = getLayoutInflater().inflate(R.layout.note_item, linearLayoutNotes, false); //Параметры id шаблона для вставки, контейнер куда вкладываем
            TextView textViewNote = view.findViewById(R.id.textViewNote); // Получаем у View шаблона элемент текста (TextView)
            textViewNote.setText(note.getText());

            int colorResId;

            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;
                    break;
                default:
                    colorResId = android.R.color.holo_red_dark;
            }
            int color = ContextCompat.getColor(this, colorResId);
            textViewNote.setBackgroundColor(color);
            linearLayoutNotes.addView(view);
        }
    }

    public static Intent newIntent(Context context, String textNote, int priority) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TEXT_NOTE, textNote);
        intent.putExtra(PRIORITY, priority);
        return intent;
    }
}
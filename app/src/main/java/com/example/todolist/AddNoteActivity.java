package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private TextView textViewEnterNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;

    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_v1);
        initViews();
        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        viewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() { // Подписка на объект для отслеживание его изменений
            @Override
            public void onChanged(Boolean shouldClose) {
                if (shouldClose) {
                    finish();
                }
            }
        });
        findViewById(R.id.buttonAddNoteSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewEnterNote.getText().toString().isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Запишите заметку", Toast.LENGTH_SHORT).show();
                } else {
                    saveNote();
                }
            }
        });
        radioButtonLow.setChecked(true);
    }

    public void initViews() {
        textViewEnterNote = findViewById(R.id.editTextEnterNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
    }

    private void saveNote() {
        String textNote = textViewEnterNote.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(textNote, priority);
        viewModel.saveNote(note);

    }

    private int getPriority() {
        int priority;
        if (radioButtonLow.isChecked()) {
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }
}
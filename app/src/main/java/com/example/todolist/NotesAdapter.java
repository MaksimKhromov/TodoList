package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener; // Переменная для хранения ссылки на слушатель кликов (интерфейса) по заметкам

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) { // Метод для установки слушателя кликов, позволяет передать реализацию интерфейса
        this.onNoteClickListener = onNoteClickListener;
    }

    public ArrayList<Note> getNotes() { //Возвращает копию коллекции
        return new ArrayList<>(notes);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged(); // Используется для определения что были добавлены новые данные
    }

    @NonNull
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //Метод создает новый экземпляр ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate( // Загружаем XML разментку для элемента и создает View объект
                R.layout.note_item,
                parent,
                false);
        return new NotesViewHolder(view); // Возвращает новый экземпляр NotesViewHolder который хранит ссылки на View объекты
    }

    public void onBindViewHolder(NotesViewHolder viewHolder, int position) { //Этот метод связывает данные с элементами представления. Он вызывается для каждого элемента списка.

        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());

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
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);
        viewHolder.textViewNote.setBackgroundColor(color);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { //Устанавливаем обработчик кликов на весь элемент списка
            @Override
            public void onClick(View v) {
                if (onNoteClickListener != null) {
                    onNoteClickListener.onNoteClick(note); // Если слушатель установлен, вызываем его метод, и передаем текущуй элемент
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder { //  Вложенный класс NotesViewHolder, который наследуется от RecyclerView.ViewHolder. Он используется для кэширования ссылок на элементы интерфейса.
        private TextView textViewNote;// Поле для хранения ссылки на текстовое представление заметки.

        public NotesViewHolder(@NonNull View itemView) { //Конструктор, который принимает представление элемента списка и инициализирует ссылки на его элементы.
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }

    interface OnNoteClickListener { // Определяем интерфейс для обработки кликов по заметкам. Он содержит метод onNoteClick(Note note), который будет реализован в активности (MainActivity) для обработки событий нажатия.
        void onNoteClick(Note note);
    }
}

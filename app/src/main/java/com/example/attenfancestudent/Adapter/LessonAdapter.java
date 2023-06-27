package com.example.attenfancestudent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attenfancestudent.Activity.ListStudent;
import com.example.attenfancestudent.Activity.Listlesson;
import com.example.attenfancestudent.Model.Lesson;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ItemLesson> {
    Context scontext;
    ArrayList<Lesson> lessonlist;

    public LessonAdapter(Context scontext, ArrayList<Lesson> lessonlist) {
        this.scontext = scontext;
        this.lessonlist = lessonlist;
    }

    @NonNull
    @Override
    public ItemLesson onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lesson,null);
        ItemLesson itemLesson = new ItemLesson(v);
        return itemLesson;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLesson holder, int position) {
        Lesson lesson = lessonlist.get(position);
        holder.lessondate.setText(lesson.getDatelesson());
        holder.teacher_lesson.setText(lesson.getN_teacher());
        holder.time_start.setText(lesson.getStime());
        holder.time_end.setText(lesson.getEtime());
        holder.lesson_class.setText(lesson.getLclass());
    }

    @Override
    public int getItemCount() {
        return lessonlist.size();
    }

    public class ItemLesson extends RecyclerView.ViewHolder{
        public ImageView imagelesson;
        public TextView teacher_lesson,time_start,time_end,lesson_class, lessondate;

        public ItemLesson(@NonNull View itemView) {
            super(itemView);
            imagelesson = (ImageView) itemView.findViewById(R.id.img_lesson);
            lessondate = (TextView) itemView.findViewById(R.id.lesson_date);
            teacher_lesson = (TextView) itemView.findViewById(R.id.teacher_lesson);
            time_start = (TextView) itemView.findViewById(R.id.time_start);
            time_end = (TextView) itemView.findViewById(R.id.time_end);
            lesson_class = (TextView) itemView.findViewById(R.id.lesson_class);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(scontext, ListStudent.class);
                    intent.putExtra("classlesson",lessonlist.get(getPosition()).getLclass());
                    intent.putExtra("idlesson",lessonlist.get(getPosition()).getId_lesson());
                    intent.putExtra("datelesson",lessonlist.get(getPosition()).getDatelesson());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Checkconnect.Showmessage(scontext,lessonlist.get(getPosition()).getDatelesson());
                    scontext.startActivity(intent);
                }
            });
        }
    }
}

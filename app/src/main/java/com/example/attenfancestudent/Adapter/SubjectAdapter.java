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

import com.example.attenfancestudent.Activity.Listlesson;
import com.example.attenfancestudent.Activity.Login;
import com.example.attenfancestudent.Activity.MainActivity;
import com.example.attenfancestudent.Model.Subject;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{
    public static Context mcContext;
    public static ArrayList<Subject> subjectList;

    public SubjectAdapter(Context mcContext, ArrayList<Subject> subjectList) {
        this.mcContext = mcContext;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mcContext);
        view = inflater.inflate(R.layout.list_subject,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.namesubject.setText(subject.getSubject_name());
        holder.classsubject.setText(subject.getSubject_class());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namesubject,classsubject;
        ImageView imagesubject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namesubject =(TextView) itemView.findViewById(R.id.namesubject);
            classsubject=(TextView) itemView.findViewById(R.id.classsubject);
            imagesubject = (ImageView) itemView.findViewById(R.id.imagesubject);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcContext, Listlesson.class);
                    intent.putExtra("thongtinsubject",subjectList.get(getPosition()).getSubject_class());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Checkconnect.Showmessage(mcContext,subjectList.get(getPosition()).getSubject_class());
                    mcContext.startActivity(intent);
                }
            });
        }
    }
}

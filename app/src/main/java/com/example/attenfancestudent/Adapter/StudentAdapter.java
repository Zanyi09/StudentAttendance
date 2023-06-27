package com.example.attenfancestudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attenfancestudent.Model.Student;
import com.example.attenfancestudent.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ItemHolder> {
    Context context;
    ArrayList<Student> studentArrayList;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return  itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Student student = studentArrayList.get(position);
        holder.stname.setText(student.getSt_name());
        holder.stclass.setText(student.getSt_class());
        holder.status.setText(student.getStatus());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView studentimage;
        public TextView stname, stclass,status;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            studentimage = (ImageView) itemView.findViewById(R.id.imagestudent);
            stname = (TextView) itemView.findViewById(R.id.studentname);
            stclass = (TextView) itemView.findViewById(R.id.studentclass);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}

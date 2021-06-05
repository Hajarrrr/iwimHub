package com.example.iwimhub2;
/*
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
*/
import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.List;

public class AdapterShowStudents extends RecyclerView.Adapter<AdapterShowStudents.MyViewHolder> {
    private ShowStudents activity;
    private List<ModelStudents> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public AdapterShowStudents(ShowStudents activity , List<com.example.iwimhub2.ModelStudents> mList){
        this.activity = activity;
        this.mList = mList;
    }
/*
    public void updateData(int position){
        com.example.iwimhub2.ModelStudents item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("isStudent" , item.getId());
        bundle.putString("Username" , item.getTitle());
        bundle.putString("PhoneNumber" , item.getDesc());
        //Intent intent = new Intent(activity, ShowStudents.class);
        Intent intent = new Intent(activity, ShowStudents.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteData(int position){
        com.example.iwimhub2.ModelStudents item = mList.get(position);
        db.collection("Documents").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
*/
    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }
    //small item on the recycle view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent , false);
        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getUserName());
        holder.desc.setText(mList.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title , desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            desc = itemView.findViewById(R.id.desc_text);
        }
    }

}


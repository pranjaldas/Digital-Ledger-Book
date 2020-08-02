package com.android.example.baki_bohi.notes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Note;

import java.util.List;

public class NoteViewAdapter extends RecyclerView.Adapter<NoteViewAdapter.MyViewHolder> implements PopupMenu.OnMenuItemClickListener {


    private List<Note> noteList;
    private Context mContext;
    private Note clickedItem;

    public NoteViewAdapter(List<Note> noteList, Context mContext) {
        this.noteList = noteList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_itm, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Note item = noteList.get(position);
        Log.d("TAG", "onBindViewHolder: " + item);
        holder.subject.setText(item.getSubject());
        holder.time.setText(item.getTime());
        holder.date.setText(item.getDate());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickedItem = item;
                showContextMenu(item, holder.layout);
                return true;
            }
        });
    }

    private void showContextMenu(Note item, View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.note_list_item_actions, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_list_item_delete:
                deleteNote();
                break;
            case R.id.note_list_item_edit:
                editNote();
                break;
            case R.id.note_list_item_notification:
                setNotification();
                break;
        }
        return false;
    }

    private void setNotification() {
        Toast.makeText(mContext, "Setting notification", Toast.LENGTH_SHORT).show();

    }

    private void editNote() {
        Toast.makeText(mContext, "Editing " + clickedItem.getSubject(), Toast.LENGTH_SHORT).show();

    }

    private void deleteNote() {
        Toast.makeText(mContext, "Deleting " + clickedItem.getSubject(), Toast.LENGTH_SHORT).show();

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subject, time, date;

        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.view_note_subject);
            time = itemView.findViewById(R.id.view_note_time);
            date = itemView.findViewById(R.id.view_note_date);
            layout = itemView.findViewById(R.id.note_item_layout);
        }

    }
}

package com.android.example.baki_bohi.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Note;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class NoteFragement extends Fragment {

    private static NoteFragement noteFragemt;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query query;
    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteViewAdapter noteViewAdapter;
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Note note = dataSnapshot.getValue(Note.class);
            noteList.add(note);
            noteViewAdapter.notifyDataSetChanged();
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Note note = dataSnapshot.getValue(Note.class);
            noteList.add(note);
            noteViewAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }
    };

    public static NoteFragement getInstance() {
        if (noteFragemt == null) {
            noteFragemt = new NoteFragement();
        }
        return noteFragemt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_notes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UiUtil.showSimpleProgressDialog(getActivity(), "Please wait...", "Retrieving your data", true);
        noteList = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.notes_list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        noteViewAdapter = new NoteViewAdapter(noteList, getActivity());
        recyclerView.setAdapter(noteViewAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Notes");
        query = mRef.orderByChild("sid").equalTo(Persistance.uId);
        query.addChildEventListener(childEventListener);

    }

    @Override
    public void onDestroy() {
        query.removeEventListener(childEventListener);
        super.onDestroy();
    }
}

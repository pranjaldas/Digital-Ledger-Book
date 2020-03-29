package com.android.example.baki_bohi.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.example.baki_bohi.R;

public class NotificationFragemt extends Fragment {

    private static NotificationFragemt notificationFragemt;

    public static NotificationFragemt getInstance() {
        if (notificationFragemt == null) {
            notificationFragemt = new NotificationFragemt();
        }
        return notificationFragemt;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_notification, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}

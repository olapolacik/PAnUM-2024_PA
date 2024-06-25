package com.example.workoutapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WorkoutDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Pobierz id treningu z aktywności
        int workoutId = getActivity().getIntent().getIntExtra(DetailActivity.EXTRA_WORKOUT_ID, 0);
        // Wyświetl szczegóły treningu
        TextView textView = view.findViewById(R.id.workout_detail);
        textView.setText(Workout.workouts[workoutId].getName());
    }
}

package com.example.workoutapp;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
            new Workout("Rozgrzewka", "10 pompek,\n10 przysiadów,\n10 podciągnięć."),
            new Workout("Dla zaawansowanych", "100 podciągnięć,\n100 pompek,\n100 brzuszków,\n100 przysiadów."),
            new Workout("Dla początkujących", "5 podciągnięć,\n5 pompek,\n5 przysiadów."),
            new Workout("Trening wytrzymałościowy", "Trucht 10 min,\nBieg szybki 3km,\nTrucht 10 min.")
    };

    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }
}

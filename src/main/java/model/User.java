package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Workout> workouts;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.workouts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public void deleteWorkout(Workout workout) {
        workouts.remove(workout);
    }
    public boolean hasWorkoutTypeForDate(String type, Date date) {
        for (Workout workout : workouts) {
            if (workout.getType().equals(type) && isSameDate(workout.getDate(), date)) {
                return true;
            }
        }
        return false;
    }
    private boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
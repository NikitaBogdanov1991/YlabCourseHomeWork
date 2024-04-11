package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Workout {
    private String type;
    private Date date;
    private int duration;
    private int calories;
    private String additionalInformation;

    public Workout(String type, Date date, int duration, int calories) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.calories = calories;
    }

    public Workout(String type, Date date, int duration, int calories, String additionalInformation) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.calories = calories;
        this.additionalInformation = additionalInformation;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String formattedDate = sdf.format(date);
        if (additionalInformation == null) {
            return "Workout{" +
                    "type='" + type + '\'' +
                    ", date=" + formattedDate +
                    ", duration=" + duration +
                    ", calories=" + calories +
                    '\'' + '}';
        } else {
            return "Workout{" +
                    "type='" + type + '\'' +
                    ", date=" + formattedDate +
                    ", duration=" + duration +
                    ", calories=" + calories +
                    ", additionalInformation='" + additionalInformation +
                    '\'' + '}';
        }
    }
}
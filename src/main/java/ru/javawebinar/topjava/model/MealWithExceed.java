package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealWithExceed {

    private Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exceed;

    public MealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MealWithExceed{");
        sb.append("id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", calories=").append(calories);
        sb.append(", exceed=").append(exceed);
        sb.append('}');
        return sb.toString();
    }
}

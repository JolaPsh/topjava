package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserMealWithExceed{");
        sb.append("dateTime=").append(dateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", calories=").append(calories);
        sb.append(", exceed=").append(exceed);
        sb.append('}');
        return sb.toString();
    }
}

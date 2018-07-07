package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {
    private final Integer userId;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.userId = getUserId();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public boolean isNew() {
        return super.isNew();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "userId=" + userId +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", id=" + id +
                '}';
    }
}

package ru.javawebinar.topjava;

import javax.validation.groups.Default;

public class View {
    public interface Web extends Default {
    }

    // Validate only when DB save/update
    public interface Persist extends Default {
    }
}
package com.github.gustavoflor.wirepock.util;

import com.github.gustavoflor.wirepock.core.User;
import com.github.javafaker.Faker;

import static java.time.LocalDateTime.now;

public class FakerUtil {

    private static final Faker FAKER = new Faker();

    private FakerUtil() {
    }

    public static User randomUser() {
        return new User(
            FAKER.number().randomNumber(),
            FAKER.name().username(),
            FAKER.name().fullName(),
            FAKER.lorem().paragraph(),
            now(),
            now()
        );
    }

}

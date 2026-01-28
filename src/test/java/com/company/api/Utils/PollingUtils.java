package com.company.api.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PollingUtils {
    public static <T> T pollUntil(
            Supplier<T> supplier,
            Duration timeout,
            Duration interval,
            Predicate<T> condition) throws InterruptedException {

        Instant end = Instant.now().plus(timeout);

        while (Instant.now().isBefore(end)) {
            T result = supplier.get();
            if (condition.test(result)) {
                return result;
            }
            Thread.sleep(interval.toMillis());
        }
        throw new RuntimeException("Condition not met within timeout");
    }
}

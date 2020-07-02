package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyStopWatch extends Stopwatch {

    private static final Logger log = LoggerFactory.getLogger(MyStopWatch.class);

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        log.info(String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.MILLISECONDS.toMicros(nanos)));
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}


package com;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * This class is the main class for collecting the application metrics
 */

public class AppMetricsCollector {

    public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();

    public static void startReport() {
        //We can redirect the output to console, csv, JMX and log files as well. Here i am redirecting it to console.
        ConsoleReporter reporter = ConsoleReporter.forRegistry(METRIC_REGISTRY)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();

        reporter.start(1, TimeUnit.MINUTES);
    }
}

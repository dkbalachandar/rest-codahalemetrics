package com;

import com.codahale.metrics.servlets.AdminServlet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletRegistration;
import java.io.IOException;

public class GrizzlyServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpServer httpServer = HttpServer.createSimpleServer("/", 8082);

        WebappContext ctx = new WebappContext("userApi");

        ServletRegistration metricsServlet = ctx.addServlet("metrics", AdminServlet.class);
        metricsServlet.addMapping("/userApi/metrics/*");
        ctx.addListener(HealthCheckServletContextListener.class);
        ctx.addListener(MetricsServletContextListener.class);

        ServletRegistration jerseyServlet = ctx.addServlet("jersey", ServletContainer.class);
        jerseyServlet.addMapping("/userApi/*");
        //Instruct the application to scan through the particular package only for the resouce classes
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.resource");
        //Map json to pojo object and vice versa
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        ctx.deploy(httpServer);
        //Start to print the metrics report
        AppMetricsCollector.startReport();
        try {
            httpServer.start();
            Thread.currentThread().join();
        } finally {
            httpServer.shutdownNow();
        }
    }
}

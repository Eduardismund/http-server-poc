package ro.eduardismund;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        final var tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // Required to initialize Tomcat

        // Add a web application root (only needed if serving static files)
        String webAppDir = new File("src/main/webapp").getAbsolutePath();
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", webAppDir);

        Tomcat.addServlet(ctx, "HelloServlet", new HelloServlet());
        ctx.addServletMappingDecoded("/hello", "HelloServlet");

        Tomcat.addServlet(ctx, "PersonsServlet", new PersonsServlet(new PersonsRepository()));
        ctx.addServletMappingDecoded("/persons", "PersonsServlet");
        ctx.addServletMappingDecoded("/persons/*", "PersonsServlet");

        tomcat.start();
        tomcat.getServer().await();
    }

}
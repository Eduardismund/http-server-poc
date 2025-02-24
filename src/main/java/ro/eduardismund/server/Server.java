package ro.eduardismund.server;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;


public class Server {
    public static void main(String[] args) throws Exception {
        final var tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // Required to initialize Tomcat

        // Add a web application root (only needed if serving static files)
        String webAppDir = new File("src/main/webapp").getAbsolutePath();
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", webAppDir);


        Tomcat.addServlet(ctx, "PersonsServlet", new PersonsServlet(new PersonsRepository()));
        ctx.addServletMappingDecoded("/persons", "PersonsServlet");
        ctx.addServletMappingDecoded("/persons/*", "PersonsServlet");

        tomcat.start();
        tomcat.getServer().await();
    }

}
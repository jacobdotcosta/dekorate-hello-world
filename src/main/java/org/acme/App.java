package org.acme;

import com.sun.net.httpserver.HttpServer;
import io.dekorate.docker.annotation.DockerBuild;
import io.dekorate.jib.annotation.JibBuild;
import io.dekorate.kubernetes.annotation.ImagePullPolicy;
import io.dekorate.kubernetes.annotation.KubernetesApplication;
import io.dekorate.kubernetes.annotation.Port;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Hello world!
 */
@KubernetesApplication(
        name = "hello-dekorate-k8s",
        ports = @Port(name = "web", containerPort = 8080),
        host = "dekorate-app.127.0.0.1.nip.io",
        expose = true,
        imagePullPolicy = ImagePullPolicy.Always
)
// image = "hello-world-dekorate:1.0-SNAPSHOT",
//@JibBuild( autoBuildEnabled = true, autoPushEnabled = true, dockerBuild = true,  enabled = true, from = "openjdk:11-jre-slim", registry = "registry-1.docker.io", group = "trikorasolutions", name="dekorate-hello-world", version = "1.0-SNAPSHOT", image = "registry-1.docker.io/trikorasolutions/hello-world-dekorate:1.0-SNAPSHOT")
//image = "docker.io/trikorasolutions/hello-world-dekorate:1.0-SNAPSHOT"
//registry = "docker.io", group="trikorasolutions",name = "dekorate-hello-workd", version="1.0-SNAPSHOT"
@DockerBuild(image = "registry-1.docker.io/trikorasolutions/hello-world-dekorate:1.0-SNAPSHOT")
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/hello", (exchange -> {
            String respText = "Hello dekorated world!";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.setExecutor(null); // creates a default executor
        System.out.println("Listening on port " + serverPort);
        server.start();
    }
}

# dekorate-test

Testing [dekorate](https://dekorate.io/)...

# Building with buildah

```bash
$ buildah bud -t hello-dekorate-k8s Dockerfile
STEP 1: FROM openjdk:8-jdk-alpine
Getting image source signatures
Copying blob c2274a1a0e27 done  
Copying blob f910a506b6cb done  
Copying blob e7c96db7181b done  
Copying config a3562aa0b9 done  
Writing manifest to image destination
Storing signatures
STEP 2: ARG JAR_FILE=target/*.jar
STEP 3: COPY ${JAR_FILE} app.jar
STEP 4: EXPOSE 8080
STEP 5: ENTRYPOINT ["java","-jar","/app.jar"]
STEP 6: COMMIT
Getting image source signatures
Copying blob f1b5933fe4b5 skipped: already exists  
Copying blob 9b9b7f3d56a0 skipped: already exists  
Copying blob ceaf9e1ebef5 skipped: already exists  
Copying blob 07e4b6edfddb done  
Copying config c14e5839fe done  
Writing manifest to image destination
Storing signatures
--> c14e5839fec
c14e5839fec442113b8db0731b74d75b1bfe3c8f3f6fad7995e523e60d13801d
```

```bash
$ buildah login registry-1.docker.io
Username: my_username
Password: 
Login Succeeded!
```

```bash
$ buildah push localhost/hello-dekorate-k8s my-docker-repo:dekorate-hello-world
...
denied: requested access to the resource is denied
error parsing HTTP 401 response body: unexpected end of JSON input: ""
```
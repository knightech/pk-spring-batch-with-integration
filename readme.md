to run against the inmemory mariadb, use "local" profile

fix to get gradle bootRun to read the arguments passed by -D is to add:

```java
bootRun {
    systemProperties = System.properties
}
```

This means that in Intellij you can do

```java
VM options: -Dspring.profiles.active=local
```
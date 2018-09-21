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

If you want to add a database then you need to configure it
```java
  
@Bean
public DataSource dataSource() {
  return DataSourceBuilder.create()
          .url("jdbc:mysql://localhost:3306/batchdb")
          .driverClassName("com.mysql.jdbc.Driver")
          .username("root")
          .password("root")
          .build();
}
    
```
and shove it in the gradle:
```java
compile group: 'mysql', name: 'mysql-connector-java', version: '5.1.13'
```

and then in the integration file, initialize it

```java

<jdbc:initialize-database data-source="dataSource">
    <jdbc:script location="classpath:schema-drop-mysql.sql"/>
    <jdbc:script location="classpath:schema-mysql.sql"/>
</jdbc:initialize-database>
    
```
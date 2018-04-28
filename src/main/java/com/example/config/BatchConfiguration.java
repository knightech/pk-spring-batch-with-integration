package com.example.config;

import com.example.model.Person;
import com.example.writer.CustomItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file.name']}") String resource) {
        System.out.println("READER: " + resource);
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setLineMapper((line, lineNumber) -> new Person(line));
        reader.setResource(new FileSystemResource(resource));
        return reader;
    }

    @Bean
    public ItemProcessor processor() {
        System.out.println("PROCESSOR");
        return new PassThroughItemProcessor();
    }

    @Bean
    public CustomItemWriter writer() {
        System.out.println("WRITER");
        return new CustomItemWriter();
    }

    @Bean
    public Job personJob(Step step1) {
        System.out.println("JOB");
        return jobBuilderFactory.get("personJob").incrementer(new RunIdIncrementer()).flow(step1).end().build();
    }

    @Bean
    public Step step1(ItemReader reader, CustomItemWriter writer) {
        System.out.println("STEP");
        return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader).processor(processor()).writer(writer).build();
    }


    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/batchdb")
                .driverClassName("com.mysql.jdbc.Driver")
                .username("root")
                .password("root")
                .build();
    }
}


package io.spring;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CustomItemWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> items) throws Exception {
        System.out.println("WROTE: " + items);
    }
}
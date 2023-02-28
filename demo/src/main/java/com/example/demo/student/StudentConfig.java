package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Lawrence = new Student(
                    "Lawrence",
                    "lawrencezhang@outlook.com",
                    LocalDate.of(2023, Month.FEBRUARY,16)
            );
            Student Andrew = new Student(
                    "Andrew",
                    "AndrewJSY@outlook.com",
                    LocalDate.of(2021, Month.FEBRUARY,25)
            );
            repository.saveAll(
                    List.of(Lawrence,Andrew)
            );
        };
    }
}

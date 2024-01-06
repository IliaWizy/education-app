package com.example.educationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EducationAppApplication {
      public static void main(String[] args) {
         SpringApplication.run(EducationAppApplication.class, args);
  }

}
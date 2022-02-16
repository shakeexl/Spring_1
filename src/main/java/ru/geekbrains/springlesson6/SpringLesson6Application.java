package ru.geekbrains.springlesson6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.springlesson6.config.JpaConfig;
import ru.geekbrains.springlesson6.dao.ProductDao;

@SpringBootApplication
public class SpringLesson6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringLesson6Application.class, args);
    }
}

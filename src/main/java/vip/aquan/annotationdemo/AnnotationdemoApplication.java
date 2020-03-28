package vip.aquan.annotationdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("vip.aquan.annotationdemo.dao")
public class AnnotationdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationdemoApplication.class, args);
    }

}

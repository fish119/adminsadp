package site.fish119.adminsadp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "site.fish119.adminsadp")
@SpringBootApplication
public class AdminSadpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSadpApplication.class, args);
    }
}

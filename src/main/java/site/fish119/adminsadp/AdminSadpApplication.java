package site.fish119.adminsadp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "site.fish119.adminsadp")
@SpringBootApplication
@EnableTransactionManagement
public class AdminSadpApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminSadpApplication.class, args);
    }
}

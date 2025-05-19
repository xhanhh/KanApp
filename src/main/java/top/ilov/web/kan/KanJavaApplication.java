package top.ilov.web.kan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.ilov.web.kan.mapper")
public class KanJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanJavaApplication.class, args);
    }

}

import com.demo.Service.UserService;
import com.demo.config.AppConfig;
import com.demo.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void selectUser() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userMapper = context.getBean(UserService.class);
        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }
}

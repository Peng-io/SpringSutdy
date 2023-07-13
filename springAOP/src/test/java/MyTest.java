import com.demo.Service.UserService;
import com.demo.config.AopConfig;
import com.demo.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = context.getBean(UserService.class);
        userService.add();
        userService.delete();
    }

    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        Student student = context.getBean(Student.class);
        student.hello("你好");
    }
}

import demo.student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTest {
    @Test
    public void testMethodAutowire() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        student student = context.getBean("student", student.class);
        System.out.println(student);
    }
}

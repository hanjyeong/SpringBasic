package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class beanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext gc = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("자바 코드 기반의 빈 설정 메타정보 확인")
    void findBeanApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 각각의 빈들의 BeanDefinition 확인하기
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole()== BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName+
                        "beanDefinition = "+beanDefinition);
            }

        }
    }

    @Test
    @DisplayName("XML 기반의 빈 설정 메타정보 확인")
    void findBeanApplicationBeanByXML(){
        String[] beanDefinitionNames = gc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 각각의 빈들의 BeanDefinition 확인하기
            BeanDefinition beanDefinition = gc.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole()== BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName+
                        "beanDefinition = "+beanDefinition);
            }

        }
    }

}

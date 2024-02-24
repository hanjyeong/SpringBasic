package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){

        // 스프링 컨테이너에 있는 모든 빈 이름 저장
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("빈 이름 = "+beanDefinitionName+" , "+ "등록된 빈 class = " + bean);

        }
    }

    @Test
    @DisplayName("우리가 설정한 빈들만 출력하기")
    void findApplicationBean(){

        // 스프링 컨테이너에 있는 모든 빈 이름 저장
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            // getBeanDefinition : 빈 하나하나에 대한 정보
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("빈 이름 = "+beanDefinitionName+" , "+ "등록된 빈 class = " + bean);

            }


        }
    }

}

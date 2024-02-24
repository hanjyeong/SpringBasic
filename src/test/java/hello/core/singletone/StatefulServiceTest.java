package hello.core.singletone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 싱글톤 컨테이너의 문제점을 알아보기 위한 테스트
class StatefulServiceTest {

    @Test
    public void statefulServiceSingletone(){

        // StatefulService의 인스턴스는 싱글톤으로 관리됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자A 10000원 주문
        int userA_Price = statefulService1.order("userA", 10000);
        // ThreadB: 사용자B 20000원 주문
        int userB_Price= statefulService2.order("userB", 20000);

        System.out.println("userA_Price = " + userA_Price);
        System.out.println("userB_Price = " + userB_Price);

    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }


    }



}


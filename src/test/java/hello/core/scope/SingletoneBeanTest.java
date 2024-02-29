package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class SingletoneBeanTest {

    @Test
    @DisplayName("싱글톤 스코프 테스트")
    void singletoneBenaTest(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(SingletoneBean.class);

        SingletoneBean singletoneBean1 = ac.getBean(SingletoneBean.class);
        SingletoneBean singletoneBean2 = ac.getBean(SingletoneBean.class);

        System.out.println("singletoneBean1 = " + singletoneBean1);
        System.out.println("singletoneBean2 = " + singletoneBean2);

        Assertions.assertThat(singletoneBean1).isSameAs(singletoneBean2);

        ac.close();
    }

    @Scope("singleton") // 싱글톤 스코프의 빈 생성
    static public class SingletoneBean{

        @PostConstruct
        public void init(){
            System.out.println("SingletoneBena.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletoneBean.destroy");
        }
    }
}

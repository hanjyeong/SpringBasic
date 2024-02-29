package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac=
                new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1:");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2:");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

      /*  // 클라이언트가 직접 호출해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();
*/
        ac.close(); // 호출 안됨
    }

    @Scope("prototype") //프로토 타입 빈 등록
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }
}

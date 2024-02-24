package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔해서 스프링 빈 등록하기")
    void basicScan(){

        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        // 등록된 모든 빈 조회하기
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("등록된 빈 = " + beanDefinitionName);
        }


    }
}

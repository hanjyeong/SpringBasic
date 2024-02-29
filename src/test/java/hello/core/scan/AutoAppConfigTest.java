package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;

import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔으로 스프링 빈 등록하기")
    void basicScan(){

        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        DiscountPolicy discountPolicy = orderService.getDiscountPolicy();
        System.out.println("discountPolicy = " + discountPolicy);

    }
}

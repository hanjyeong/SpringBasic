package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService=appConfig.memberService();
        orderService= appConfig.orderService();
    }

    @Test
    void createOrder(){

//        MemberService memberService= new MemberServiceImpl();
//        OrderService orderService= new OrderServiceImpl();

        //given

        Long memberId1=1L;
        Long memberId2=2L;
        Member member1= new Member(memberId1,"오리너구리", Grade.VIP);
        Member member2 =new Member(memberId2,"냥냥",Grade.BASIC);
        memberService.join(member1);
        memberService.join(member2);

        //when : createOrder메소드 테스트하기

        Order order1 = orderService.createOrder(memberId1, "감자", 5000);
        Order order2 = orderService.createOrder(memberId2, "물고기", 2000);

        //then :VIP 고객의 할인 값이 1000원인지 확인하기
        Assertions.assertThat(order1.getDiscountPrice()).isEqualTo(500);
        Assertions.assertThat(order2.getDiscountPrice()).isEqualTo(0);



    }
}

package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    // RateDiscountPolicy클래스의 discount 메소드의 로직을 테스트하기 위함
    // 로직의 계산 결과가 실제로 10%의 값이 맞는지 테스트 해보는것

    RateDiscountPolicy rateDiscountPolicy= new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 되어야한다")
    void vip_0(){

        //given
        Long memberId=1L;
        Member member= new Member(memberId,"오리너구리", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("BASIC은 할인이 없다")
    void BASIC_X(){

        //given
        Long memberId=2L;
        Member member= new Member(memberId,"오리", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);

    }


}
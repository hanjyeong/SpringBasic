package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    // VIP 고객은 항상 상품가격의 10%할인
    int discountPercent= 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price*discountPercent/100;       // 할인된 가격
        }else{
            return 0;
        }
    }
}

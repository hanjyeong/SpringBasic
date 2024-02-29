package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OrderServiceImpl implements OrderService{

      private  final MemberRepository memberRepository;
      private  final DiscountPolicy   discountPolicy;

      @Autowired
      public OrderServiceImpl(@Qualifier("memorymemberRepository")MemberRepository memberRepository,DiscountPolicy discountPolicy) {

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {

        return memberRepository;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(memberId);
        // 할인과 관련된 부분은 disCountPolicy에 위임
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);

    }
}

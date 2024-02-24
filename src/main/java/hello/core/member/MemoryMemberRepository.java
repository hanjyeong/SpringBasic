package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // 빈 등록 대상
public class MemoryMemberRepository implements MemberRepository{

    // MemberRepository의 구현체
    public static Map<Long,Member> store = new HashMap<>();
    @Override
    public void save(Member member) {

        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {

        return store.get(memberId);
    }

}

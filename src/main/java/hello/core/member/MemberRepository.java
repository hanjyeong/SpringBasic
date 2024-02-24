package hello.core.member;

public interface MemberRepository {

    void save (Member meber); // 회원 저장
    Member findById(Long memberId); // 회원 아이디 찾기
}

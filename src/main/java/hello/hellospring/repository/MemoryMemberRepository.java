package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store =new HashMap<>();
    //메모리니까 저장을 해놓아야한다. 키는 회원 아이디로, 값은 멤버로 한다.
    private static long sequence=0L;
    //0,1,2,... 키 값을 생성해주는 것
    @Override
    public Member save(Member member) {
        member.setId((++sequence));
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //get값이 null일수있으므로 OPTIONAL로 감쌀수있다. NULL이면 클라이언트에서 처리가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals((name)))
                .findAny();
        //찾으면 findAny로 반환, 끝까지 못찾으면 optional로 포함되어서 null로 반환된다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //values는 멤버라는 뜻
        //반환은 리스트로 사용한다.
    }

    public void clearStore(){
        store.clear();
    }
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository
{
    Member save(Member member);
    //회원 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //저장소에서 findById, findByName으로 찾아올수있다
    List<Member> findAll();
    //지금까지 저장된 회원리스트 반환
}

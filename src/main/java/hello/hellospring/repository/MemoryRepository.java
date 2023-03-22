package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface MemoryRepository {
    //1. 회원 저장
    Member save(Member member);

    //2.아이디로 회원 찾기
    Optional<Member> findId(Long id);

    //3. 이름으로 회원찾기
    Optional<Member> findName(String name);

    //4. 모든 회원
    List<Member> findAll();

    void clear();

}

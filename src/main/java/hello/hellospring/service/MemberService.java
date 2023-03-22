package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryRepository;

import java.util.LinkedList;
import java.util.List;

public class MemberService {
    private final MemoryRepository memberRepository= new MemoryMemberRepository();
    /*
    * 회원가입
    * */
    public Long join (Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * */

    public List<Member> allMembers(){
        return memberRepository.findAll();
    }
}

package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository memory;

    @BeforeEach
    public void beforEach(){
        memory = new MemoryMemberRepository();
        service= new MemberService(memory);
    }


    @AfterEach
    void clear(){
        memory.clear();
    }

    @Test
    void join() {
        //given
        Member member1=new Member();
        member1.setName("gildong");

        //when
        Long resultId=service.join(member1);

        //then
        Member member2=service.findOne(resultId).get();
        Assertions.assertThat(member2.getId()).isEqualTo(member1.getId());
    }

    @Test
    void 중복회원찾기(){
        //given
        Member member1=new Member();
        member1.setName("songhe");

        Member member2=new Member();
        member2.setName("songhe");

        //when
        service.join(member1);

        //then
        IllegalStateException e= assertThrows(IllegalStateException.class, ()-> service.join(member2));
    }


}
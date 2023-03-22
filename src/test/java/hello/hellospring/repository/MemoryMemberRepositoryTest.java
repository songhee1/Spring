package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clear();
    }

    @Test
    public void save(){
        Member member1=new Member();
        member1.setName("gildong");
        Member result=repository.save(member1);
        Assertions.assertThat(member1).isEqualTo(result);
    }
    @Test
    public void findName(){
        Member member1=new Member();
        member1.setName("gildong");
        repository.save(member1);
        Member result=repository.findName("gildong").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("gildong");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("songhee");
        repository.save(member2);

        List<Member> result=repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);
        Member result=repository.findById((member.getId())).get();
        //findByid의 반환값은 optional이므로 get으로 바로 빼낼 수 있다. 좋은 방법은 아님
        Assertions.assertThat(member).isEqualTo(result);
        //다음 단계로 넘어간다.
    }

    @Test
    public void findByName(){
        Member member1= new Member();
        member1.setName("sprig1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("sprig2");
        repository.save(member2);

        Member result=repository.findById(member1.getId()).get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findList(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("sprig2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}

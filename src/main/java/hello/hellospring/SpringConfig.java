package hello.hellospring;

import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memoryRepository());
    }

    @Bean
    public MemoryMemberRepository memoryRepository(){
        return new MemoryMemberRepository();
    }
}

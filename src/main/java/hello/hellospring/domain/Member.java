package hello.hellospring.domain;

import hello.hellospring.repository.MemoryMemberRepository;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //db가 알아서 생성해주는 것= identity
    private static Long id;
    @Column(name="username")
    //db와 연결
    private static String name;


    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        Member.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Member.name = name;
    }
}

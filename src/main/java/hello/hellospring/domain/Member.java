package hello.hellospring.domain;

public class Member {
    private static Long id;
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

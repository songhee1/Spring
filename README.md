# Spring 입문 - 배운 내용 정리

# 1. MVC 와 템플릿 엔진

<BR><BR>

## 1) [정적 컨텐츠] : 파일을 그대로 내려준다.

<BR>
hello-static 서버를 찾고 브라우저에 반환

<BR>
<BR>

-----------

<BR>
<BR>

## 2) [mvc, 템플릿 엔진] : 변환해서 html브라우저에 출력한다.<BR> 
html렌더링

<BR> 
    - MVC : Model, View, Controller <BR>
    - Controller: 내부처리에 집중(서버 뒷단에서 처리)<BR>
    - View: 화면 그리는데 집중

<BR>
<BR>

--------> 코드

```java
@getMapping("hell-mvc")
public String helloMvc(@RequestParam("name") String name, Model model){
 model.addAttribute("name", name);
 return "hello-template"
}
```

<BR>
<BR>


-----> 해설

### RequestParam

<BR> 외부에서 url파라미터를 받을때 쓰는 것<BR>
"name": 키에 해당

<BR>

>return "~" : hello-template으로 가며, template폴더>hello-template.html 생성, ${name}에 내용이 치환이 된다. 

<BR>

*브라우저에 localhost:8080/hello-mvc 접속하면
처음엔 에러, 왜 에러가 나느냐?
name이 없다>에러정보 확인하기*

<BR>
<bR>

### @RequestParam(name="name")추가!!

<BR>

브라우저에 localhost:8080/hello-mvc?name=spring!!! 접속시 "hello spring!!!"이 출력된다
컨트롤러에서 name=spring!!!으로 바뀌게 되고 hello-template.html에서도 name이 치환되서 출력이 되는 것이다. 

<BR>

내장 톰켓서버라는게 왔어
스프링 컨테이너는 helloController 실행-> viewResolver 로 뷰 찾아주고 등등 일을 한다. 

변환해서 웹 브라우저에 출력한다. 
name="spring!!!" 이것처럼

<BR>
<BR>
<BR>
<BR>

------------

<BR>

## 3) [ API ] : 데이터 바로 내리는 방법

<BR>
<BR>

```java
@GetMapping("hello-string")
@ResponseBody
public String helloString(@RequestParam("name") String name){
 return "hello"+name;
}
```

<br>

### @ResponseBody 
 
 <br>

- html body tag가 아니라 http에서 body부에 data를 직접 넣어주겠다는 뜻 <br>
- 클라이언트에게 바로 데이터 바로 넣어주겠다는 뜻

<br>

파라미터에 `name=spring!!!` 넣으면 `"hello spring!!!"`이 나옴
소스보기보면 `hello spring!!!` 그대로 출력되는 것을 볼 수 있음


<br>
<br>

----

<br>
<Br>


## 4) [json 방식]************* 최신 프로젝트에는 json 방식으로 쓰는 것이 좋다. json으로 반환하는 것이 기본이다.

<br>
<br>

```java
@GetMapping("hello-string")
@ResponseBody
public  Hello helloApi (@RequestParam("name") String name){
    Hello hello=new Hello();
    hello.setName(name);
    return hello;
}

static class Hello{
    private String name;
    public String getName(){
    return name;
    }

    public void setName(String name){
    this.name=name;
    }
}
```

<br>
<br>
<br>

### @ResponseBody 사용원리 

<br>

여기서는 리턴값이 객체, 객체가 오면 기본값이 json값으로 데이터 만들어서 data를 반환하겠다는 것이 기본방식이다.

`HttpMessageConverter`가 동작한다. 객체면 `JsonConverter`가 동작, 객체를 브라우저에 보내준다. 


<br>
<br>



<br>
<br>
<br>
<br>

# 2. 비즈니스 요구사항 정리

<br>

## 1) [비즈니스 요구사항]

- 데이터 : 회원id, 이름
- 기능: 회원등록, 조회

<br>
<br>
<Br>



## 2) [클래스 의존관계]

<br>

- MemberService<br>
- MemberRepository(interface): 회원 저장하는 것은 interface로 설계<br>
- MemoryMemberRepository(interface 구현체): 인터페이스 구현체

<br>위와 같이 클래스와 인터페이스를 생성할 것으로 시나리오 작성됨.
<br>
<br>
<br>
<br>
<br>
<br>


# 3. 회원 도메인과 리포지토리 만들기

<br>

- package이름(`domain`)> class이름(`Member`) 생성<br>

-> 시스템이 저장하는 아이디, 이름
getter, setter 작성


<br>


# 4. 회원 리포지토리 테스트 케이스 작성
- main메서드로 실행하거나.......반복실행하기 어렵다는 문제점이 있다. 단점 여럿 존재
->**junit 테스트코드** 실행해 문제점 해결할 수 있다.

- @Test ->해당 메서드만 실행할 수 있다.

- *옵셔널*은 get()으로 꺼내 사용할 수 다. 크게 추천하는 방식은 아니다.

<br>

## 1)결과값과 기대값 비교하는 방법
```java
Assertions(org.junit.jupiter.api).assertEquals(member,result);
//기대하는것, 실제값을 비교할 수 있다.

Assertions(org.assertj.core.api).assertThat(member).isEqualTo(result);
//임포트하면 static import되서 assertThat바로 쓸수있다.
//member가 result와 같은지를 확인할 수 있다. 다르다면 오류

//만든 메서드들 모두 테스트해본다.
```

💡 단축키 팁 [shift+f6] 이름 바꿀수있다.

<br>

## 2) 테스트 관련 데이터 지우기 *중요*

- 모든 테스트들은 메서드별로 따로 동작하게 설계되어있다. 
테스트하나 끝내고 나면 데이터를 클리어해야 다른 테스트 메서드 돌릴때 오류가 나지 않는다. 

```java
@AfterEach
//테스트가 모두 끝난 후에 동작하는 코드라는 뜻의 어노테이션
public void afterEach(){
 repository.clearStore(); 
}
```

📍 [테스트 케이스 만들고 레포지토리 만드는것] : TDD(테스트 주도 개발)


# 5. 회원 서비스 개발
실제 비즈니스 로직을 작성하는 곳
package: service > class: MemberService

<br>

## 1)회원가입

♦ **private static final** 선언
- 상수 선언시 명시적으로 private static final선언
결론: 초기화한 값을 끝까지 유지하기 위해서(값을 불변으로 만든다.)
- 선언한 변수로 재할당이 어려우며, 메모리 한번 올라가면 클래스 내부 전체필드, 메서드에서 공유한다.

<br>

# 6. 회원서비스 테스트

## 1) given when then 테스트 코드 작성 

- 테스트 코드작성시 **given when then**으로 작성하는 것이 좋다 <br>
이 패턴으로 하면서 점점 변형하는 것을 추천한다.<br>

```java
//given
Member member=new Member();
```

## 2) 중복 회원 에러 띄우기 

- assertThat()쓰려면 Assertions(org.assertj.core.api) 꺼를 사용한다.

- 정상회원이 아닌, 아이디가 중복되는 *중복회원*도 확인해야 좋은 테스트다.

<br>

```java
IllegalStateException e=assertThrows(에러 이름.class, ()->memberService.join(member2));
```

- 위의 문장은 두 번째 매개변수에 해당하는 코드를 실행할건데 첫번째 매개변수로 전달된 에러가 떠야 한다는 것을 의미한다.<br>
다른오류가 뜨면 테스트 실패


## 3) 같은 객체 사용

- 테스트 코드 작성할 경우에도 활용될 수 있다.

```java
class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository memory;

    @BeforeEach
    //@BeforeEach: 테스트 실행하기 전에 동작
    public void beforEach(){
        memory = new MemoryMemberRepository();
        service= new MemberService(memory);
    }

}
```


# 7. 스프링 빈과 의존관계

## 1) 스프링 빈 등록, 의존관계 설정하기- 컴포넌트 스캔과 자동 의존관계 설정하기

- 컨트롤러 생성후 @Autowired (=연결시켜줄때 사용) 어노테이션을 생성자 초기화에서 사용한다. 매개변수 인수에는 사용하고자 하는 클래스 명으로 작성해주면 연결된다.

- @Service (=스프링 올라올때 스프링이 스프링 컨테이너에 해당 클래스를 등록해주는 역할을 한다.)

- @Repository(=데이터 저장)

- 스프링이 처음 실행될때 스프링 컨테이너라는 통이 생성된다. 거기에 @Controller 에노테이션이 있는 곳에 대해 해당 객체 생성되고, 스프링이 관리한다.
-> 스프링 빈이 관리된다.

- *스프링 컨테이너로부터 받아서 쓰도록* 코드 수정필요하다. 하나만 컨테이너에서 생성해서 공유해서 쓰면 된다. **싱클턴**이라고 한다.

- private final 로 사용할 클래스를 선언후, 생성자 초기화할 때, 해당 클래스를 인자로 받아 초기화해준다.

```java
@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
}
```
MemberService클래스에서는 MemoryRepository 클래스를 사용하므로, 위와 같은 형식으로 코드를 작성해 리포지토리를 이용한다.

``` java
public class MemberService {
    private final MemoryRepository memberRepository;

    public MemberService(MemoryRepository memberRepository){
        this.memberRepository=memberRepository;
    }
}
```

- 생성자에 **@Autowired** 어노테이션을 쓰면, MemberConroller가 생성될때 스프링 빈에 등록되어있는 *MemberService객체*를 가져와 넣어준다. (DI,Dependency Injection, 의존관계 주입)
- 해당 어노테이션을 쓰면, 스프링이 컨트롤러와 MemberService와 연결시켜준다.

🧨 사용할 클래스에 대해서는 스프링이 알 길이 없으므로 **@Service** 어노테이션을 작성해줌으로써 스프링에서 Memberserive에 등록할수있도록 한다.

🧨 리포지토리 구현체에서는 @Repository 어노테이션을 작성해주어야 한다. **스프링이 처음 실행될때, Controller, Repository, Service를 가지고 간다**

- 스프링이 실행될때 **@Component**와 관련된 에노테이션(@Service, @Repository 등)이 있으면 해당 객체 하나씩 생성해, 스프링 컨테이너에 등록한다. 스프링 빈으로 자동 등록된다.



## 2) 자바코드로 직접 스프링 빈 등록하기

- @Bean :스프링 빈을 직접 등록을 의미한다. 직접 설정파일에 등록해서 스프링 빈에 등록할 수 있다.

```java
@Configuration
public class SpringConfig{
 @Bean
 public MemberService memberService(){
  return new MemberService();
 }
 // 해당 메서드를 실행해서 스프링 빈에 등록해준다.
// 스프링빈에 등록하게 되면, 스프링 빈 등록 이미지처럼 연결된다.
```

- @Service, @Repository, @Autowire은 모두 지워줘도 된다, 
 
 
 # 8. 회원 관리 예제 

## 1) 홈 화면 추가

- 버튼있는 링크있는 사이트 추가해보는 것이 목표다.
- 컨트롤러를 먼저 찾고 없으면 static 파일을 찾는다. 만약 컨트롤러를 찾게 되면 호출되고 끝나게 된다. static 파일은 무시된다. 

```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}

```
- template 내의 home.html 파일을 찾아서 localhost:8080 페이지로 연결시킨다. 

## 2) 이동 화면 추가

회원 등록 버튼/ 회원 조회 버튼

(1) 회원 등록 버튼

- 관련 컨트롤러에서 매핑 코드를 작성해준다.

```java

  @GetMapping(value="members/new")
    public String createForm(){
        return "members/createForm";
    }

    @PostMapping(value="members/new")
    public String postForm(createMemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);

        System.out.println(member.getName());
        return "redirect:/";
    }
```

- *조회*할 때 사용 되는 것은 get mapping 이용, *등록*할 때는 post mapping을 이용한다.

- get 매핑으로 접속한 members/createForm.html 페이지에서 form작성 후, 제출하게 되면, submit에 연결되었던 위의 코드인 postmapping으로 연결된다.

- 변수였던 name이 createMemberForm 클래스의 name 값으로 치환되어 들어간다. 

```java
public class createMemberForm {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## 3) 전체 회원 조회

- 조회이므로 GetMapping 어노테이션을 이용하며, *Model*(org.springframework.ui.Model import!)에 등록해준다.

```java
@GetMapping(value="/members")
    public String list(Model model){
        List<Member> lists=memberService.allMembers();
        model.addAttribute("members", lists);
        return "members/lists";
    }
```
- 해당하는 lists.html 파일에서 members를 찾아서 렌더링한다

```html
<tr th:each="member : ${members}">
        <td th:text="${member.id}"></td>
        <td th:text="${member.name}"></td>
</tr>
```


package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping(value="members/new")
    public String createForm(){
        return "members/createForm";
    }

    @PostMapping(value="members/new")
    public String postForm(createMemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "home";
    }
}
package com.dd.alcohol.service;

import com.dd.alcohol.dto.MemberDto;
import com.dd.alcohol.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("test@email.com");
        memberDto.setName("홍길동");
        memberDto.setAddress("서울시 마포구 합정동");
        memberDto.setPassword("123467");
        return Member.createMember(memberDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);
        assertEquals(member.getEmail(), savedMember.getEmail()); // A and B 객체가 같은 값을 가지는지 확인
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);});
        LOGGER.info(e.getMessage());
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }

}

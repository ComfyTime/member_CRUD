package org.example.member_crud.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.member_crud.dto.MemberRequestDto;
import org.example.member_crud.dto.MemberResponseDto;
import org.example.member_crud.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto save(@RequestBody MemberRequestDto dto) {
        return memberService.save(dto);
    }
    //다건
    @GetMapping("/members")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }
    //단건
    @GetMapping("/members/{id}")
    public MemberResponseDto findOne(@PathVariable Long id) {
        return MemberService.findById(id);
    }
    @PutMapping("/members/{id}")
    public MemberResponseDto update(@PathVariable Long id, @RequestBody MemberRequestDto dto) {
        return memberService.update(id, dto);
    }
    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}

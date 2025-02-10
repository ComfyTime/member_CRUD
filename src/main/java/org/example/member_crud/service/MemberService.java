package org.example.member_crud.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.member_crud.dto.MemberRequestDto;
import org.example.member_crud.dto.MemberResponseDto;
import org.example.member_crud.entity.Member;
import org.example.member_crud.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getName());

        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll(){
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDto dto = new MemberResponseDto(member.getId(), member.getName());
            dtos.add(dto);

        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("None")
        );
        return new MemberResponseDto(member.getId(), member.getName());
    }
    @Transactional
    public MemberResponseDto update(Long id, MemberResponseDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("None")
        );
        member.update(dto.getName());
        return new MemberResponseDto(member.getId(), member.getName());
    }
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("none");

        }
        memberRepository.deleteById(id);
    }
}

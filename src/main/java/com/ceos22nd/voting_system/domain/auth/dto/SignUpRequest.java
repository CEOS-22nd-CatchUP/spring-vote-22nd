package com.ceos22nd.voting_system.domain.auth.dto;

import com.ceos22nd.voting_system.domain.member.enums.PartType;
import com.ceos22nd.voting_system.domain.member.enums.TeamType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @NotBlank(message = "아이디를 입력해주세요.")
        String loginId,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "이름을 입력해주세요.")
        String realName,

        @NotNull(message = "파트를 입력해주세요.")
        PartType part,

        @NotNull(message = "소속 팀을 선택해주세요.")
        TeamType team,

        @NotNull(message = "파트장 후보 여부를 체크해주세요.")
        Boolean isPartLeadCandidate
) {
}

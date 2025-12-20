package com.ceos22nd.voting_system.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TeamType {
    // 괄호 안의 문자열이 프론트에서 보내주는 value와 정확히 일치해야 합니다.
    STORIX("STORIX"),
    MODELLY("Modelly"),
    CATCHUP("CatchUp"),
    MENUAL("Menual"),
    DIGGINDIE("DiggIndie");

    private final String inputValue; // 프론트에서 넘어오는 값

    @JsonCreator
    public static TeamType from(String value) {
        return Stream.of(TeamType.values())
                .filter(team -> team.getInputValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getValue() {
        return name(); // CATCHUP
    }
}
package com.ceos22nd.voting_system.domain.member.enums;

public enum PartType {
    FRONTEND, BACKEND;

    public static PartType from(String value) {
        return PartType.valueOf(value.toUpperCase());
    }
}

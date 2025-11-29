    package com.ceos22nd.voting_system.domain.auth.dto;

    import jakarta.validation.constraints.NotBlank;

    public record LogInRequest(
            @NotBlank String loginId,
            @NotBlank String password
    ) {
    }

package com.github.gustavoflor.wirepock.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record User(Long id,
                   @JsonProperty("login") String username,
                   String name,
                   String bio,
                   @JsonProperty("created_at") LocalDateTime createdAt,
                   @JsonProperty("updated_at") LocalDateTime updatedAt) {

}

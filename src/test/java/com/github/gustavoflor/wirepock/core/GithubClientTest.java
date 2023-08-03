package com.github.gustavoflor.wirepock.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.gustavoflor.wirepock.util.FakerUtil.randomUser;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 0)
class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("""
    GIVEN an username\s
    WHEN find user by username\s
    THEN should return an user\s
    """)
    void givenAnUsernameWhenFindUserByUsernameThenShouldReturnAnUser() throws JsonProcessingException {
        final var stubUser = randomUser();
        final var stubResponse = aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withBody(objectMapper.writeValueAsBytes(stubUser));
        final var expectedPath = String.format("/users/%s", stubUser.username());
        stubFor(get(urlEqualTo(expectedPath)).willReturn(stubResponse));

        final var result = githubClient.findUserByUsername(stubUser.username());

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(stubUser.id());
        assertThat(result.username()).isEqualTo(stubUser.username());
        assertThat(result.name()).isEqualTo(stubUser.name());
        assertThat(result.bio()).isEqualTo(stubUser.bio());
        assertThat(result.createdAt()).isEqualTo(stubUser.createdAt());
        assertThat(result.updatedAt()).isEqualTo(stubUser.updatedAt());
    }

}

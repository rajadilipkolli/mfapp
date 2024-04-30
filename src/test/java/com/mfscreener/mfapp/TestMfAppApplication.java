package com.mfscreener.mfapp;

@TestConfiguration(proxyBeanMethods = false)
public class TestMfAppApplication {

    @Bean
    @ServiceConnection(name = "redis")
    GenericContainer<?> redisContainer() {
        return new GenericContainer<>(DockerImageName.parse("redis").withTag("7.2.4-alpine")).withExposedPorts(6379);
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres").withTag("16-alpine"));
    }

    public static void main(String[] args) {
        SpringApplication.from(MfAppApplication::main)
                .with(TestMfAppApplication.class)
                .run(args);
    }
}

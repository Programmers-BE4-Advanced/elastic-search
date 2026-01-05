package com.back;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

// 모든 테스트 클래스가 상속하여 Elasticsearch 컨테이너 공유
public class BaseTest {
    // Elasticsearch 컨테이너 정의
    @Container
    public static final ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer(
            DockerImageName.parse("elasticsearch:nori")
    )
            .withEnv("discovery.type", "single-node")
            .withEnv("xpack.security.enabled", "false")
            .withExposedPorts(9200)
            .waitingFor(
                    Wait.forHttp("/")
                            .forPort(9200)
                            .forStatusCode(200)
            );

    // 테스트 시 동적으로 Elasticsearch URI 주입
    @DynamicPropertySource
    public static void setElasticsearchProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", elasticsearchContainer::getHttpHostAddress);
    }
}

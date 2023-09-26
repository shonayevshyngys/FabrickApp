package com.github.fabrickgateway;

import lombok.extern.log4j.Log4j2;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class GroupedOpenAPI {

    final
    RouteDefinitionLocator locator;

    public GroupedOpenAPI(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            log.info(routeDefinition.getId() + " " + routeDefinition.getUri());
            String name = routeDefinition.getId().replace("-service", "");
            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        });
        return groups;
    }

}

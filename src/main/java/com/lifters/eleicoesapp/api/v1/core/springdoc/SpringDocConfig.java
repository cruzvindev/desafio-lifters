package com.lifters.eleicoesapp.api.v1.core.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Lifters Eleições")
                        .version("v1")
                        .description("API desenvolvida como solução para teste técnico")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                );

    }

    @Bean
    public OpenApiCustomizer openApiCustomizer(){
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach(((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();

                                switch (httpMethod){
                                    case GET:
                                        responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                        responses.addApiResponse("406", new ApiResponse()
                                                .description("Recurso não possui representação que possa satisfazer o consumidor"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    case POST:
                                        responses.addApiResponse("400", new ApiResponse().description("Requisição inválida"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    case PUT:
                                        responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                        responses.addApiResponse("400", new ApiResponse().description("Requisição inválida"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                    default:
                                        responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                        break;
                                }
                            }))
                    );
        };
    }
}

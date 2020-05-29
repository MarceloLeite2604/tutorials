package com.github.marceloleite2604.tutorials.configuration;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.github.marceloleite2604.tutorials.fetcher.GraphQLDataFetchers;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlConfiguration {

  public static final String GRAPHQL_SCHEMA_RESOURCE_NAME = "schema.graphqls";

  @Bean("graphQL")
  public GraphQL createGraphQL(GraphQLDataFetchers graphQLDataFetchers) throws IOException {
    RuntimeWiring runtimeWiring = createRuntimeWiring(graphQLDataFetchers);
    TypeDefinitionRegistry typeDefinitionRegistry = createTypeDefinitionRegistry();
    GraphQLSchema graphQLSchema = createGraphQLSchema(typeDefinitionRegistry, runtimeWiring);
    return GraphQL.newGraphQL(graphQLSchema).build();
  }

  public RuntimeWiring createRuntimeWiring(GraphQLDataFetchers graphQLDataFetchers) {
    return RuntimeWiring.newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
        .type(newTypeWiring("Book")
            .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
        .build();
  }

  private TypeDefinitionRegistry createTypeDefinitionRegistry()
      throws IOException {
    URL url = Resources.getResource(GRAPHQL_SCHEMA_RESOURCE_NAME);
    String sdl = Resources.toString(url, StandardCharsets.UTF_8);
    return new SchemaParser().parse(sdl);
  }

  private GraphQLSchema createGraphQLSchema(TypeDefinitionRegistry typeDefinitionRegistry,
      RuntimeWiring runtimeWiring) {
    return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
  }

}

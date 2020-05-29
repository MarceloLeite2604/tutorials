package com.github.marceloleite2604.tutorials.model;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
public class Food {

  @Id @GeneratedValue
  @GraphQLQuery(name = "id", description = "A food's id")
  private Long id;

  @GraphQLQuery(name = "name", description = "A food's name")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Food{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

}


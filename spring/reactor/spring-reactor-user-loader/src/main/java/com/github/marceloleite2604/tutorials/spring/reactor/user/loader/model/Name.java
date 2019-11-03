package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {

  @JsonProperty
  private String title;

  @JsonProperty
  private String first;

  @JsonProperty
  private String last;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  @Override
  public String toString() {
    return "Name [title=" + title + ", first=" + first + ", last=" + last + "]";
  }
}

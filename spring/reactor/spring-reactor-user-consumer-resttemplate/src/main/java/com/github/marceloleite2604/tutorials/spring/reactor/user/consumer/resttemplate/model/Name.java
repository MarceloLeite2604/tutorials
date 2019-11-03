package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.model;

public class Name {

  private String title;

  private String first;

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

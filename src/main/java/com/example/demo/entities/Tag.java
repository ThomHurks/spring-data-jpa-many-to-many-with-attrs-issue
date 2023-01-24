package com.example.demo.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue
  private Long id;

  @NaturalId
  private String name;

  @OneToMany(
      mappedBy = "tag",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<PostTag> posts = new ArrayList<>();

  public Tag() {
  }

  public Tag(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return Objects.equals(name, tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

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

  public List<PostTag> getPosts() {
    return posts;
  }

  public void setPosts(List<PostTag> posts) {
    this.posts = posts;
  }
}
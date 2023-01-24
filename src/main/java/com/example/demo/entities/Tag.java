package com.example.demo.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue
  private Long id;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(
      mappedBy = "tag",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<PostTag> posts = new HashSet<>();

  public Tag() {
  }

  public Tag(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof final Tag other) {
      return Objects.equals(getName(), other.getName());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
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

  public Set<PostTag> getPosts() {
    return posts;
  }

  public void setPosts(Set<PostTag> posts) {
    this.posts = posts;
  }
}
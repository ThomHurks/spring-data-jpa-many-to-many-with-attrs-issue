package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Post")
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String title;

  @OneToMany(
      mappedBy = "post",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<PostTag> tags = new HashSet<>();

  public Post() {
  }

  public Post(String title) {
    this.title = title;
  }

  public void addTag(final @NotNull @Valid Tag tag) {
    PostTag postTag = new PostTag(this, tag);
    tags.add(postTag);
    tag.getPosts()
       .add(postTag);
  }

  public void removeTag(final @NotNull @Valid Tag tag) {
    for (Iterator<PostTag> iterator = tags.iterator();
         iterator.hasNext(); ) {
      PostTag postTag = iterator.next();

      if (postTag.getPost()
                 .equals(this) &&
          postTag.getTag()
                 .equals(tag)) {
        iterator.remove();
        postTag.getTag()
               .getPosts()
               .remove(postTag);
        postTag.setPost(null);
        postTag.setTag(null);
      }
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof final Post other) {
      return Objects.equals(getId(), other.getId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<PostTag> getTags() {
    return tags;
  }

  public void setTags(Set<PostTag> tags) {
    this.tags = tags;
  }
}
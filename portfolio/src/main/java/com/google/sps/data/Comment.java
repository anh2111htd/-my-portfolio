package com.google.sps.data;

import javax.annotation.Nullable;

/** An item on a comment list. */
public final class Comment {

  private final long id;
  private String comment;
  private final long timestamp;
  @Nullable private String imageURL;

  public Comment(long id, String comment, long timestamp, String imageURL) {
    this.id = id;
    this.comment = comment;
    this.timestamp = timestamp;
    this.imageURL = imageURL;
  }
}
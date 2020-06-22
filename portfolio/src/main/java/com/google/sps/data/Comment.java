package com.google.sps.data;

/** An item on a comment list. */
public final class Comment {

  private final long id;
  private final String comment;
  private final long timestamp;
  private final String imageURL;

  public Comment(long id, String comment, long timestamp) {
    this.id = id;
    this.comment = comment;
    this.timestamp = timestamp;
  }

  public Comment(long id, String comment, long timestamp, String imageURL) {
    Comment(id, comment, timestamp);
    this.imageURL = imageURL;
  }
}
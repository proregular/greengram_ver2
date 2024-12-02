package com.green.greengramver2.feed.comment.model;

import lombok.Builder;
import lombok.Getter;

// Value Object
@Getter
@Builder
public class FeedCommentDto {
    private long feedCommentId;
    private String comment;
    private long writerUserId;
    private String writerNm;
    private String writerPic;
}

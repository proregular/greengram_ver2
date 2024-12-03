package com.green.greengramver2.feed.comment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class FeedCommentDelReq {
    private long feedCommentId;
    private long signedUserId;

    public FeedCommentDelReq(@BindParam("feed_comment_id") long feedCommentId, @BindParam("signed_user_id") long signedUserId) {
        this.feedCommentId = feedCommentId;
        this.signedUserId = signedUserId;
    }
}

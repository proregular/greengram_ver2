package com.green.greengramver2.feed.comment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class FeedCommentDelReq {
    @Schema(name="feed_comment_id")
    private long feedCommentId;
    @Schema(name="signed_user_id")
    private long userId;

    @ConstructorProperties({"feed_comment_id", "signed_user_id"})
    public FeedCommentDelReq(long feedCommentId, long signedUserId) {
        this.feedCommentId = feedCommentId;
        this.userId = signedUserId;
    }
}

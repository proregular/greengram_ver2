package com.green.greengramver2.feed.comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greengramver2.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCommentGetReq {
    private final static int FIRST_COMMENT_SIZE = 3;
    private final static int DEFAULT_PAGE_SIZE = 20;

    @Schema(title="피드 PK", example = "4", requiredMode = Schema.RequiredMode.REQUIRED)
    private long feedId;

    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private int size;

   public void setSize(int page) {
       if(page < 1) { return; }
       if(page == 1) {
           this.startIdx = 0;
           this.size = FIRST_COMMENT_SIZE + 1; //+1은 isMore처리용
           return;
       }
       this.startIdx = ((page - 2) * DEFAULT_PAGE_SIZE) + FIRST_COMMENT_SIZE;
       size = DEFAULT_PAGE_SIZE + 1; // +1은 isMore처리용
       
   }
}

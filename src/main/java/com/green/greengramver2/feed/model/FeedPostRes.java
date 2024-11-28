package com.green.greengramver2.feed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "피드 정보")
public class FeedPostRes {
    @Schema(description = "피드 PK")
    private long feedId;
    @Schema(description = "피드 사진 리스트")
    private List<String> pics;
}

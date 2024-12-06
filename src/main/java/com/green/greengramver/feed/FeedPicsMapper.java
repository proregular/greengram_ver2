package com.green.greengramver.feed;

import com.green.greengramver.feed.model.FeedPicDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    int insFeedPics(FeedPicDTO p);
    List<String> selFeedPics(long feedId);
}

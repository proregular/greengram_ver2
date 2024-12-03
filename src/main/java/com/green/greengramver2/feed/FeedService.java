package com.green.greengramver2.feed;

import com.green.greengramver2.common.MyFileUtils;
import com.green.greengramver2.feed.comment.FeedCommentMapper;
import com.green.greengramver2.feed.comment.model.FeedCommentDto;
import com.green.greengramver2.feed.comment.model.FeedCommentGetReq;
import com.green.greengramver2.feed.comment.model.FeedCommentGetRes;
import com.green.greengramver2.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedPicsMapper picsMapper;
    private final FeedCommentMapper feedCommentMapper;
    private final MyFileUtils myFileUtils;

    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = feedMapper.insFeed(p);
        long feedId = p.getFeedId();

        // 파일 등록
        String middlePath = String.format("feed/%d", feedId); // 파일저장경로
        // 폴더 생성
        myFileUtils.makeFolders(middlePath);

        // 랜덤 파일명 저장용 >> feed_pics 테이블에 저장할 때 사용
        List<String> picList = new ArrayList<>(pics.size());

        for(MultipartFile pic : pics) {
            String saveFileName = myFileUtils.makeRandomFileName(pic);
            String filePath = String.format("%s/%s", middlePath, saveFileName);

            picList.add(saveFileName);

            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FeedPicDTO feedPicDTO = new FeedPicDTO();

        feedPicDTO.setFeedId(feedId);
        feedPicDTO.setPics(picList);

        int resultPics = picsMapper.insFeedPics(feedPicDTO);

        return FeedPostRes.builder()
                .feedId(feedId)
                .pics(picList)
                .build();
    }

    public List<FeedGetRes> getFeedList(FeedGetReq p) {
        List<FeedGetRes> list = feedMapper.selFeedList(p);

        for(FeedGetRes item : list) {
            item.setPics(picsMapper.selFeedPics(item.getFeedId()));

            //피드당 댓글 4개
            FeedCommentGetReq commentGetReq = new FeedCommentGetReq(item.getFeedId(), 0, 3);

            List<FeedCommentDto> commentList = feedCommentMapper.selFeedCommentList(commentGetReq);

            FeedCommentGetRes commentGetRes = new FeedCommentGetRes();
            commentGetRes.setCommentList(commentList);
            commentGetRes.setMoreComment(commentList.size() == commentGetReq.getSize());

            if(commentGetRes.isMoreComment()) {
                commentList.remove(commentList.size() - 1);
            }
            item.setComment(commentGetRes);
        }

        return list;
    }
}

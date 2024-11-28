package com.green.greengramver2.feed;

import com.green.greengramver2.common.MyFileUtils;
import com.green.greengramver2.feed.model.FeedPostReq;
import com.green.greengramver2.feed.model.FeedPostRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedPicsMapper picsMapper;
    private final MyFileUtils myFileUtils;

    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = feedMapper.insFeed(p);
        long feedId = p.getFeedId();

        if(result > 0) {
            // 파일 등록
            String middlePath = String.format("feed/%d", feedId); // 파일저장경로
            // 폴더 생성
            myFileUtils.makeFolders(middlePath);

            for(MultipartFile pic : pics) {
                String saveFileName = myFileUtils.makeRandomFileName(pic);
                String filePath = String.format("%s/%s", middlePath, saveFileName);

                try {
                    myFileUtils.transferTo(pic, filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

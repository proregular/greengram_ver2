package com.green.greengramver2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "회원가입 정보")
public class UserSignUpReq {
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private String pic;
    @Schema(description = "아이디", example = "test", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(description = "비밀번호", example = "1212", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(description = "닉네임")
    private String nickName;
}

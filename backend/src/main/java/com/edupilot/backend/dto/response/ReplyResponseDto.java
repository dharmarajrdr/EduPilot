package com.edupilot.backend.dto.response;

import com.edupilot.backend.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDto {

    private String userName;

    private String message;

    private Long discussionId;

    private LocalDateTime createdAt;

    public static ReplyResponseDto fromReply(Reply reply) {

        return ReplyResponseDto.builder().userName(reply.getUser().getUserName()).message(reply.getMessage()).createdAt(reply.getCreatedAt()).discussionId(reply.getLectureDiscussion().getId()).build();
    }
}

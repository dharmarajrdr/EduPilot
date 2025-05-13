package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Reply;
import lombok.Data;

@Data
public class AddReplyRequestDto {

    private String message;

    private Long lectureDiscussionId;

    public Reply toReply() {

        return Reply.builder().message(message).build();
    }

}

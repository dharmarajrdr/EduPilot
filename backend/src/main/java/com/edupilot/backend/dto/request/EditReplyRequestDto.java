package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Reply;
import lombok.Data;

@Data
public class EditReplyRequestDto {

    private String message;

    public Reply patchReply(Reply reply) {

        reply.setMessage(message);
        return reply;
    }
}

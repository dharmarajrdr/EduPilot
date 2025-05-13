package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Reply;

public interface ReplyService {

    public Reply save(Reply reply);

    public Reply findById(Long id);

    public void deleteReply(Long replyId);
}

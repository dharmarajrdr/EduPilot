package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.ReplyNotFound;
import com.edupilot.backend.model.Reply;
import com.edupilot.backend.repository.ReplyRepository;
import com.edupilot.backend.service.interfaces.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    /**
     * Save the reply
     *
     * @param reply
     * @return
     */
    @Override
    public Reply save(Reply reply) {

        return replyRepository.save(reply);
    }

    /**
     * Find reply by id
     *
     * @param replyId
     * @return
     */
    @Override
    public Reply findById(Long replyId) {

        return replyRepository.findById(replyId).orElseThrow(() -> new ReplyNotFound(replyId));
    }

    /**
     * @param replyId 
     * @return
     */
    @Override
    public void deleteReply(Long replyId) {

        replyRepository.deleteById(replyId);
    }
}

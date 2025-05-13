package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.PermissionDenied;
import com.edupilot.backend.custom_exception.ReplyNotFound;
import com.edupilot.backend.dto.request.EditReplyRequestDto;
import com.edupilot.backend.dto.response.ReplyResponseDto;
import com.edupilot.backend.model.Reply;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.ReplyRepository;
import com.edupilot.backend.service.interfaces.ReplyService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final UserService userService;

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

    /**
     * Edit the reply
     *
     * @param editReplyRequestDto
     * @param userId
     * @return
     */
    @Override
    public ReplyResponseDto editReply(Long replyId, EditReplyRequestDto editReplyRequestDto, Long userId) {

        User user = userService.findUserById(userId);
        Reply reply = editReplyRequestDto.patchReply(findById(replyId));
        if (!reply.getUser().equals(user)) {
            throw new PermissionDenied("You don't have permission to edit this reply.");
        }
        return ReplyResponseDto.fromReply(save(reply));
    }
}

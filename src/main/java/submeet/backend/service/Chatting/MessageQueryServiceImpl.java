package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.MemberChat;
import submeet.backend.repository.ChatMessageRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageQueryServiceImpl implements MessageQueryService {
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public Page<ChatMessage> findChatMessageByMemberChat(MemberChat memberChat, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        return chatMessageRepository.findByMemberChat(memberChat, pageRequest);
    }
}

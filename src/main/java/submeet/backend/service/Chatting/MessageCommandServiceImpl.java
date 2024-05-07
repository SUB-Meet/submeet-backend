package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.repository.ChatMessageRepository;
import submeet.backend.repository.ChatRepository;
import submeet.backend.service.member.MemberQueryService;
import submeet.backend.web.dto.chat.ChatMessageDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageCommandServiceImpl implements MessageCommandService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatQueryService chatQueryService;
    private final MemberQueryService memberQueryService;
    @Override
    @Transactional
    public ChatMessage save(ChatMessageDTO chatMessageDTO) {
        ChatRoom chatRoom = chatQueryService.findById(chatMessageDTO.getChatRoomId());
        Member member = memberQueryService.findMember(chatMessageDTO.getMemberId());
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(member)
                .type(chatMessageDTO.getMessageType())
                .message(chatMessageDTO.getMessage())
                .build();
        return chatMessageRepository.save(chatMessage);
    }
}

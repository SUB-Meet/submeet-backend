package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;
import submeet.backend.repository.ChatMessageRepository;
import submeet.backend.repository.MemberChatRepository;
import submeet.backend.service.member.MemberQueryService;
import submeet.backend.web.dto.chat.ChatMessageDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageCommandServiceImpl implements MessageCommandService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatQueryService chatQueryService;
    private final MemberQueryService memberQueryService;
    private final MemberChatRepository memberChatRepository;
    @Override
    @Transactional
    public ChatMessage save(ChatMessageDTO chatMessageDTO) {
        ChatRoom chatRoom = chatQueryService.findById(chatMessageDTO.getChatRoomId());
        Member member  = memberQueryService.findMember(chatMessageDTO.getMemberId());
        MemberChat memberChat = memberChatRepository.findByMemberAndChatRoom(member, chatRoom).orElseThrow(()->new ChatHandler(ErrorStatus.MEMBER_CHAT_NOT_FOUND));
        ChatMessage chatMessage = ChatMessage.builder()
                .memberChat(memberChat)
                .type(chatMessageDTO.getMessageType())
                .message(chatMessageDTO.getMessage())
                .build();
        return chatMessageRepository.save(chatMessage);
    }
}

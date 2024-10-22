package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.apiPayLoad.exception.handler.MemberHandler;
import submeet.backend.common.enums.MessageType;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.repository.ChatMessageRepository;
import submeet.backend.repository.ChatRepository;
import submeet.backend.repository.MemberRepository;
import submeet.backend.service.Chatting.ChatCommandService;
import submeet.backend.service.Chatting.MessageCommandService;
import submeet.backend.service.member.MemberQueryService;
import submeet.backend.web.dto.chat.ChatMessageDTO;


@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final MemberQueryService memberQueryService;
    private final ChatCommandService chatCommandService;
    private final MessageCommandService messageCommandService;

    /**
     * 채팅 입장
     * /pub/chat/enter 로 보내면 여기로 온다.
     * @param message
     */
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
        message.setMessageType(MessageType.ENTER.toString());
        message.setMessage(memberQueryService.findMember(message.getMemberId()).getNickName() + "님이 채팅방에 참여하였습니다.");
        chatCommandService.addMember(message.getChatRoomId(), message.getMemberId());
        chatCommandService.plusUserCnt(message.getChatRoomId());
        messageCommandService.save(message);
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);

    }

    /**
     * 메세지 전송
     * @param message
     */
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        message.setMessageType(MessageType.MESSAGE.toString());
        messageCommandService.save(message);
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    /**
     * 채팅방 나감
     * @param message
     */
    @MessageMapping(value = "/chat/leave")
    public void leave(ChatMessageDTO message){
        message.setMessageType(MessageType.LEAVE.toString());
        message.setMessage(memberQueryService.findMember(message.getMemberId()).getNickName() + "님이 채팅방에서 나갔습니다.");
        messageCommandService.save(message);
        chatCommandService.delMember(message.getChatRoomId(), message.getMemberId());
        chatCommandService.minusUserCnt(message.getChatRoomId());
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

}

package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import submeet.backend.converter.ChatConverter;
import submeet.backend.entity.ChatRoom;
import submeet.backend.service.Chatting.ChatCommandService;
import submeet.backend.web.dto.chat.ChatRequestDTO;
import submeet.backend.web.dto.chat.ChatResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatCommandService chatCommandService;
    /**
     * 채팅방 생성
     */
    @PostMapping("/room")
    public ChatResponseDTO.ChatCreateResultDTO createRoom(@RequestBody ChatRequestDTO.ChatCreateRequestDTO chatCreateRequestDTO){
        ChatRoom chatRoom = chatCommandService.createChatRoom(chatCreateRequestDTO);
        return ChatConverter.toChatCreateResultDTO(chatRoom);
    }

}

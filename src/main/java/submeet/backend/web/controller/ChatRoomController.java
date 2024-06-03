package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.common.comparator.MessageDTOComparator;
import submeet.backend.converter.ChatConverter;
import submeet.backend.converter.MemberChatConverter;
import submeet.backend.converter.MemberConverter;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;
import submeet.backend.repository.MemberChatRepository;
import submeet.backend.service.Chatting.ChatCommandService;
import submeet.backend.service.Chatting.ChatQueryService;
import submeet.backend.service.member.MemberQueryService;
import submeet.backend.web.dto.chat.ChatRequestDTO;
import submeet.backend.web.dto.chat.ChatResponseDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatCommandService chatCommandService;
    private final ChatQueryService chatQueryService;
    private final MemberChatRepository memberChatRepository;
    private final MemberQueryService memberQueryService;
    /**
     * 채팅방 생성
     */
    @PostMapping("/room")
    public ChatResponseDTO.ChatCreateResultDTO createRoom(@RequestBody ChatRequestDTO.ChatCreateRequestDTO chatCreateRequestDTO){
        ChatRoom chatRoom = chatCommandService.createChatRoom(chatCreateRequestDTO);
        return ChatConverter.toChatCreateResultDTO(chatRoom);
    }

    /**
     * chatRoomId인 채팅방에 참여중인 member 반환
     * @param chatRoomId
     * @return
     */
    @GetMapping("/room/members/{chatRoomId}")
    public ApiResponse<ChatResponseDTO.MemberListDTO> findMemberList(@PathVariable Long chatRoomId){
        ChatRoom chatRoom = chatQueryService.findById(chatRoomId);
        List<MemberChat> memberChatList = memberChatRepository.findByChatRoom(chatRoom);
        List<Member> memberList = MemberChatConverter.toMemberList(memberChatList);
        List<MemberResponseDTO.MemberDTO> memberDTOList = MemberConverter.toMemberDTOList(memberList);
        ChatResponseDTO.MemberListDTO result = ChatResponseDTO.MemberListDTO.builder()
                .member_list(memberDTOList)
                .build();
        return ApiResponse.of(SuccessStatus.CHAT_MEMBERS,result);
    }

    /**
     * member가 참여중인 채팅방 목록 반환
     * @param memberId
     * @return
     */
    @GetMapping("/rooms/{memberId}")
    public ApiResponse<ChatResponseDTO.ChatListDTO> findChatList(@PathVariable Long memberId){
        Member member = memberQueryService.findMember(memberId);
        List<MemberChat> memberChatList = memberChatRepository.findByMember(member);
        List<ChatRoom> chatList = MemberChatConverter.toChatList(memberChatList);
        List<ChatResponseDTO.ChatRoomDTO> chatRoomDTOList = ChatConverter.toChatDTOList(chatList);
        ChatResponseDTO.ChatListDTO result = ChatResponseDTO.ChatListDTO.builder()
                .chat_list(chatRoomDTOList)
                .build();
        return ApiResponse.of(SuccessStatus.CHATS_FOUND,result);
    }

    /**
     * 채팅 과거 내용 조회
     * @param chatRoomId
     * @return
     */
    @GetMapping("/room/history/{chatRoomId}")
    public ApiResponse<ChatResponseDTO.ChatMessageListDTO> getChatHistory(
            @PathVariable Long chatRoomId) {
        ChatRoom chatRoom = chatQueryService.findById(chatRoomId);
        List<MemberChat> memberChatList = memberChatRepository.findByChatRoom(chatRoom);
        List<List<ChatMessage>> messageDoubleList = memberChatList.stream()
                .map(
                        MemberChat::getMessageList
                )
                .toList();



        List<ChatResponseDTO.MessageDTO> messageDTOList = new ArrayList<>();
        for (List<ChatMessage> chatMessages : messageDoubleList) {
            for (ChatMessage chatMessage : chatMessages) {
                MemberChat memberChat = chatMessage.getMemberChat();
                ChatResponseDTO.MessageDTO messageDTO = ChatResponseDTO.MessageDTO.builder()
                        .created_at(chatMessage.getCreatedAt())
                        .message_id(chatMessage.getId())
                        .message(chatMessage.getMessage())
                        .type(chatMessage.getType())
                        .profile_image(memberChat.getMember().getProfile_image())
                        .nick_name(memberChat.getMember().getNickName())
                        .member_id(memberChat.getMember().getId())
                        .email(memberChat.getMember().getEmail())
                        .build();
                messageDTOList.add(messageDTO);
            }
        }

        messageDTOList.sort(new MessageDTOComparator());


        ChatResponseDTO.ChatMessageListDTO chatMessageListDTO = ChatResponseDTO.ChatMessageListDTO.builder()
                .message_list(messageDTOList)
                .build();
        return ApiResponse.of(SuccessStatus.CHATS_FOUND,chatMessageListDTO);
    }
}

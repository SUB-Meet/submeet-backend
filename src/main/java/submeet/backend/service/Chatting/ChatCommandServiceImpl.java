package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.apiPayLoad.exception.handler.MemberChatHandler;
import submeet.backend.apiPayLoad.exception.handler.MemberHandler;
import submeet.backend.apiPayLoad.exception.handler.PostHandler;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;
import submeet.backend.entity.Post;
import submeet.backend.repository.ChatRepository;
import submeet.backend.repository.MemberChatRepository;
import submeet.backend.repository.MemberRepository;
import submeet.backend.repository.PostRepository;
import submeet.backend.web.dto.chat.ChatRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatCommandServiceImpl implements ChatCommandService {
    private final ChatRepository chatRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberChatRepository memberChatRepository;

    @Override
    @Transactional
    public ChatRoom createChatRoom(ChatRequestDTO.ChatCreateRequestDTO chatCreateRequestDTO) {
        Post post = postRepository.findById(chatCreateRequestDTO.getPost_id()).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        ChatRoom chatRoom = ChatRoom.builder()
                .post(post)
                .appointmentTime(chatCreateRequestDTO.getAppointment_time())
                .userCount(0L)
                .build();
        post.setChatRoom(chatRoom);
       return chatRepository.save(chatRoom);
    }

    @Override
    @Transactional
    public void plusUserCnt(Long roomId) {
        ChatRoom chatRoom = chatRepository.findById(roomId).orElseThrow(() -> new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
        chatRoom.setUserCount(chatRoom.getUserCount() + 1L);
    }

    @Override
    @Transactional
    public void minusUserCnt(Long roomId) {
        ChatRoom chatRoom = chatRepository.findById(roomId).orElseThrow(() -> new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
        chatRoom.setUserCount(chatRoom.getUserCount() - 1L);
    }

    @Override
    @Transactional
    public Member addMember(Long roomId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        ChatRoom chatRoom = chatRepository.findById(roomId).orElseThrow(() -> new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
        if(memberChatRepository.existsByMemberAndChatRoom(member,chatRoom)){
            MemberChat memberChat = memberChatRepository.findByMemberAndChatRoom(member, chatRoom).orElseThrow(() -> new ChatHandler(ErrorStatus.MEMBER_CHAT_NOT_FOUND));
            if(memberChat.getStatus()){
                throw new MemberChatHandler(ErrorStatus.MEMBER_CHAT_NOT_FOUND);
            }
            else{
                memberChat.setStatus(true);
                return memberChat.getMember();
            }
        }
        MemberChat memberChat = MemberChat.builder()
                .member(member)
                .chatRoom(chatRoom)
                .status(true)
                .build();
        MemberChat savedMemberChat = memberChatRepository.save(memberChat);
        return savedMemberChat.getMember();
    }

    @Override
    @Transactional
    public Member delMember(Long roomId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        ChatRoom chatRoom = chatRepository.findById(roomId).orElseThrow(() -> new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
        MemberChat memberChat = memberChatRepository.findByMemberAndChatRoom(member, chatRoom).orElseThrow(()->new ChatHandler(ErrorStatus.MEMBER_CHAT_NOT_FOUND));
        memberChat.setStatus(false);
        return member;
    }

}

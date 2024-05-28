package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.apiPayLoad.exception.handler.PostHandler;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Post;
import submeet.backend.repository.ChatRepository;
import submeet.backend.repository.PostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService{
    private final ChatRepository chatRepository;
    private final PostRepository postRepository;

    @Override
    public ChatRoom findById(Long roomId) {
        return chatRepository.findById(roomId).orElseThrow(()->new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
    }

    @Override
    public ChatRoom findByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        return chatRepository.findByPost(post).orElseThrow(()->new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
    }


}

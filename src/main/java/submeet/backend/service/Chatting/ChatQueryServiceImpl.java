package submeet.backend.service.Chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.ChatHandler;
import submeet.backend.entity.ChatRoom;
import submeet.backend.repository.ChatRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService{
    private final ChatRepository chatRepository;

    @Override
    public ChatRoom findById(Long roomId) {
        return chatRepository.findById(roomId).orElseThrow(()->new ChatHandler(ErrorStatus.CHAT_ROOM_NOT_FOUND));
    }
}

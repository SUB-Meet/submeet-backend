package submeet.backend.service.Chatting;

import submeet.backend.entity.ChatRoom;

public interface ChatQueryService {
    public ChatRoom findById(Long roomId);
}

package submeet.backend.common.comparator;

import submeet.backend.web.dto.chat.ChatResponseDTO;

import java.util.Comparator;

public class MessageDTOComparator implements Comparator<ChatResponseDTO.MessageDTO> {

    @Override
    public int compare(ChatResponseDTO.MessageDTO o1, ChatResponseDTO.MessageDTO o2) {
        return o1.getCreated_at().compareTo(o2.getCreated_at());
    }
}

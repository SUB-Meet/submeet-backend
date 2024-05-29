package submeet.backend.converter;

import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;

import java.util.List;
import java.util.stream.Collectors;

public class MemberChatConverter {
    public static List<Member> toMemberList(List<MemberChat> memberChatList){
        return memberChatList.stream()
                .filter(MemberChat::getStatus)
                .map(MemberChat::getMember)
                .toList();
    }

    public static List<ChatRoom> toChatList(List<MemberChat> memberChatList) {
        return memberChatList.stream()
                .filter(MemberChat::getStatus)
                .map(MemberChat::getChatRoom)
                .collect(Collectors.toList());
    }
}

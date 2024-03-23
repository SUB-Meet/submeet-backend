package submeet.backend.dto;

import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import submeet.backend.entity.Member_chat;
import submeet.backend.entity.Post;

import java.sql.Timestamp;

public class PostForm {
    private int id;

    private String description;
    private int departure;
    private int destination;
    private String title;
    private Timestamp start_time;
    private Timestamp end_time;
    private String category;
    private int participants;
    private int current_participants;
    private int gender;
    private int age_range;
    public Post toEntity(){
        return new Post(null,description,departure,destination,title,start_time,end_time,category,participants,current_participants,gender,age_range);
    }
}

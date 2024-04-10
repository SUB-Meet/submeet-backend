package submeet.backend.service.station;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StationCommandServiceImplTest {

    @Test
    void 코드에서문자열반환테스트(){
        String s = StationCommandServiceImpl.codeToString(999L);
        System.out.println("s = " + s);
        Assertions.assertEquals("0999",s);
    }

}
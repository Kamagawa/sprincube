package sprincube.friend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sprincube.friend.domain.Friend;
import sprincube.friend.service.FriendRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
@EnableSpringDataWebSupport
public class FriendControllerTest {

    @MockBean
    private FriendRepository friendRepository;
    @Autowired
    private MockMvc mockMvc;

    List<Friend> list;

    @Test
    public void findUser() throws Exception {
        Mockito.when(friendRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/api/friend"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        // todo continue here

        Mockito.verify(friendRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findUserById() throws Exception {
        Mockito.when(friendRepository.findAllById(Collections.singleton(2))).thenReturn(Collections.singletonList(list.get(2)));
        mockMvc.perform(get("/api/friend?Id=2"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        // todo continue here

        Mockito.verify(friendRepository, Mockito.times(1)).findAllById(Collections.singleton(2));
    }

    @Before
    public void Setup() {
        generateFriend(0xfff);
    }

    public void generateFriend(int number) {
        if (list== null) list = new ArrayList<>();
        for (int i = 0; i < number; i ++) {
            list.add(new Friend(i, "Friend"+i, 1));
        }
    }
}

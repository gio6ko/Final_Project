package bg.softuni.final_project.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import bg.softuni.final_project.repository.UserRepository;
import com.cloudinary.Cloudinary;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Cloudinary cloudinary;


    @Autowired
    private UserRepository testRepository;


    @AfterEach
    void clean() {
        testRepository.deleteAll();
    }


    @Test
    public void testGetRegisterForm() throws Exception {

        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }
}
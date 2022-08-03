package com.codewithjindu.fashionblog.service.impl;

import com.codewithjindu.fashionblog.constant.DefaultMessage;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.repository.PostRepository;
import com.codewithjindu.fashionblog.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private HttpSession httpSession;
    @Mock
    private PostRepository postRepository;
    @Mock
    private AdminService adminService;


    @Test
    void createPost() throws NotAuthorizedException {
        PostRequestPayload postRequestPayload = new PostRequestPayload("Fashion Recycles",
                "Fashion always recycles itself as it evolves");
        String result = DefaultMessage.SUCCESSFUL_POST_CREATION;
//        String expected = adminService.createPost(postRequestPayload);
//        System.out.println(expected);
//
//        assertThat(expected).isEqualTo(result);
    }

    @Test
    void editPost() {
    }

    @Test
    void deletePost() {
    }

}
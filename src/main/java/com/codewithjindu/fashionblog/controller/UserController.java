package com.codewithjindu.fashionblog.controller;

import com.codewithjindu.fashionblog.dto.CommentRequestPayload;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.exception.ResourceNotFoundException;
import com.codewithjindu.fashionblog.model.Pagination;
import com.codewithjindu.fashionblog.model.Post;
import com.codewithjindu.fashionblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/post")
public class UserController {
    private final UserService userService;

    @PutMapping("/edit_comment/{comment_id}")
    public ResponseEntity<String> editComment(@RequestBody CommentRequestPayload commentRequestPayload, @PathVariable("comment_id") Long commentId)
            throws NotAuthorizedException, ResourceNotFoundException {
        var response = userService.editComment(commentRequestPayload, commentId);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/create_comment/{postId}")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestPayload commentRequestPayload, @PathVariable("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.createComment(commentRequestPayload, postId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/like_post/{postId}")
    public ResponseEntity<String> likePost(@PathVariable ("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.likePost(postId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/display_pages")
    public ResponseEntity<Page<Post>> viewAllPost(Pagination pagination){
        return ResponseEntity.ok(userService.getAllPost(pagination));
    }
}

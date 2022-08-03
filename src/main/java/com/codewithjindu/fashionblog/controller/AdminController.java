package com.codewithjindu.fashionblog.controller;

import com.codewithjindu.fashionblog.dto.CategoryDto;
import com.codewithjindu.fashionblog.dto.CommentRequestPayload;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.dto.SearchDto;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.exception.ResourceNotFoundException;
import com.codewithjindu.fashionblog.model.Comment;
import com.codewithjindu.fashionblog.model.Post;
import com.codewithjindu.fashionblog.service.AdminService;
import com.codewithjindu.fashionblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/post")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    @PostMapping("/create_post/{cat_id}")
    public ResponseEntity<String> createPost(@RequestBody PostRequestPayload postRequestPayload, @PathVariable("cat_id") Long cat_id) throws NotAuthorizedException, ResourceNotFoundException {
        var response = adminService.createPost(postRequestPayload, cat_id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/edit_post/{post_id}")
    public ResponseEntity<String> editPost(@RequestBody PostRequestPayload postRequestPayload, @PathVariable("post_id") Long postId) throws ResourceNotFoundException {
        var response = adminService.editPost(postRequestPayload, postId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete_post/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") Long postId) throws NotAuthorizedException {
        var response = adminService.deletePost(postId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete_comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable("comment_id") Long commentId) throws NotAuthorizedException {
        var response = adminService.deleteComment(commentId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/view_post/{post_id}")
    public ResponseEntity<Post> getPost(@PathVariable("post_id") Long postId) throws ResourceNotFoundException {
        var response = adminService.retrievePost(postId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/view_all_post")
    public ResponseEntity<List<Post>> viewAllPosts(){
        var response = adminService.viewAllPosts();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/view_comments/{postId}")
    public ResponseEntity<List<Comment>> viewComments(@PathVariable ("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.viewAllComments(postId);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/create_category")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        var response = adminService.createCategory(categoryDto);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/search_post")
    public ResponseEntity<List<Post>> SearchPostByTittle(@RequestBody SearchDto searchDto)  {
        var response = adminService.searchPostByTitle(searchDto);
        return ResponseEntity.ok().body(response);
    }
}

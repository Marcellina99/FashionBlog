package com.codewithjindu.fashionblog.service;

import com.codewithjindu.fashionblog.dto.CategoryDto;
import com.codewithjindu.fashionblog.dto.CommentRequestPayload;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.dto.SearchDto;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.exception.ResourceNotFoundException;
import com.codewithjindu.fashionblog.model.Comment;
import com.codewithjindu.fashionblog.model.Post;

import java.util.List;

public interface AdminService {
    String createPost(PostRequestPayload postRequestPayload,Long catId) throws NotAuthorizedException,ResourceNotFoundException;
    Post retrievePost(Long postId) throws ResourceNotFoundException;
    String editPost(PostRequestPayload postRequestPayload, Long postId) throws ResourceNotFoundException;
    List<Post> viewAllPosts ();
    List<Post> searchPostByTitle(SearchDto searchDto);
    String deletePost(Long id) throws NotAuthorizedException;
    // deleteComment
    String deleteComment(Long id) throws NotAuthorizedException;
    Comment getComment(Long id) throws ResourceNotFoundException;
    String createCategory(CategoryDto categoryDto);
}

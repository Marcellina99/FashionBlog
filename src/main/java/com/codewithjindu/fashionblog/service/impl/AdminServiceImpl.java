package com.codewithjindu.fashionblog.service.impl;

import com.codewithjindu.fashionblog.constant.DefaultMessage;
import com.codewithjindu.fashionblog.constant.Role;
import com.codewithjindu.fashionblog.dto.CategoryDto;
import com.codewithjindu.fashionblog.dto.CommentRequestPayload;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.dto.SearchDto;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.exception.ResourceNotFoundException;
import com.codewithjindu.fashionblog.model.Categories;
import com.codewithjindu.fashionblog.model.Comment;
import com.codewithjindu.fashionblog.model.Post;
import com.codewithjindu.fashionblog.model.User;
import com.codewithjindu.fashionblog.repository.CategoryRepository;
import com.codewithjindu.fashionblog.repository.CommentRepository;
import com.codewithjindu.fashionblog.repository.PostRepository;
import com.codewithjindu.fashionblog.repository.UserRepository;
import com.codewithjindu.fashionblog.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final HttpSession httpSession;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public String createPost(PostRequestPayload postRequestPayload, Long catId) throws NotAuthorizedException, ResourceNotFoundException {
        Role role = (Role) httpSession.getAttribute("Role");//TODO
        if (!role.equals(Role.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        Categories categories = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));

        Post post = Post.builder()
                .content(postRequestPayload.getContent())
                .title(postRequestPayload.getTitle())
                .categories(categories)
                .build();

        postRepository.save(post);

        return DefaultMessage.SUCCESSFUL_POST_CREATION;
    }

    @Override
    public Post retrievePost(Long postId) throws ResourceNotFoundException {
//        Optional<Post> post = postRepository.findById(postId);
//        if (post.isPresent())
//            return post.get();
       return postRepository.findById(postId)
               .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
    }

    @Override
    public String editPost(PostRequestPayload postRequestPayload, Long postId) throws ResourceNotFoundException {
        Optional<Post> postCheck = postRepository.findById(postId);
        if (postCheck.isPresent()){
            Post editedPost = postCheck.get();
            editedPost.setTitle(postRequestPayload.getTitle());
            editedPost.setContent(postRequestPayload.getContent());
            editedPost.setUpdateTime(LocalDateTime.now());
            postRepository.save(editedPost);
            return DefaultMessage.SUCCESSFUL_POST_UPDATE;
        }
        throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
    }

    @Override
    public List<Post> viewAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> searchPostByTitle(SearchDto searchDto) {
        return postRepository.findPostsByTitleContainsIgnoreCase(searchDto.getTitle());
    }

    @Override
    public String deletePost(Long id) throws NotAuthorizedException {
        Role role = (Role) httpSession.getAttribute("Role");
        if (!role.equals(Role.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        postRepository.deleteById(id);
        return DefaultMessage.SUCCESSFUL_POST_DELETION;
    }

    @Override
    public String deleteComment(Long id) throws NotAuthorizedException {
        Role role = (Role) httpSession.getAttribute("Role");
        if (!role.equals(Role.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        commentRepository.deleteById(id);
        return DefaultMessage.SUCCESSFUL_COMMENT_DELETION;
    }

    @Override
    public Comment getComment(Long id) throws ResourceNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent())
            return comment.get();
        else throw new ResourceNotFoundException(DefaultMessage.COMMENT_NOT_FOUND);
    }

    @Override
    public String createCategory(CategoryDto categoryDto) {
        Categories categories = Categories.builder()
                .title(categoryDto.getTitle())
                .build();
        categoryRepository.save(categories);
        return DefaultMessage.SUCCESSFUL_CATEGORY_CREATION;

    }

}

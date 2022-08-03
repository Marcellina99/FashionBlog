package com.codewithjindu.fashionblog.service.impl;

import com.codewithjindu.fashionblog.constant.DefaultMessage;
import com.codewithjindu.fashionblog.dto.CommentRequestPayload;
import com.codewithjindu.fashionblog.exception.NotAuthorizedException;
import com.codewithjindu.fashionblog.exception.ResourceNotFoundException;
import com.codewithjindu.fashionblog.model.*;
import com.codewithjindu.fashionblog.repository.CommentRepository;
import com.codewithjindu.fashionblog.repository.LikeRepository;
import com.codewithjindu.fashionblog.repository.PostRepository;
import com.codewithjindu.fashionblog.repository.UserRepository;
import com.codewithjindu.fashionblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final HttpSession httpSession;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    @Override
    public String editComment(CommentRequestPayload commentRequestPayload, Long commentId) throws NotAuthorizedException, ResourceNotFoundException {
        Optional<User> user = userRepository.findById((Long) httpSession.getAttribute("Login_ID"));
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (user.isPresent()){
            if (comment.isPresent()){
                if (user.get().equals(comment.get().getUser())){
                    Comment editedComment = comment.get();
                    editedComment.setText(commentRequestPayload.getComment());
                    return DefaultMessage.SUCCESSFUL_COMMENT_UPDATE;
                }else throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
            }else throw new ResourceNotFoundException(DefaultMessage.COMMENT_NOT_FOUND);

        }else throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
    }

    @Override
    public String likePost(Long postId) throws ResourceNotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
        }
        Optional<User> user = userRepository.findById((Long) httpSession.getAttribute("Login_ID"));
        if (user.isEmpty()){
            System.out.println("user is anonymous");
        }
        LikePost newLike = new LikePost(user.get(),post.get());
        likeRepository.save(newLike);
        return DefaultMessage.POST_SUCCESSFULLY_LIKED;
    }

    @Override
    public String createComment(CommentRequestPayload commentRequestPayload, Long postId) throws ResourceNotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
        }
        Optional<User> user = userRepository.findById((Long) httpSession.getAttribute("Login_ID"));
        if (user.isEmpty()){
            System.out.println("user is anonymous");
        }
        Comment comment = new Comment();
        comment.setText(commentRequestPayload.getComment());
        comment.setPost(post.get());
        comment.setUser(user.get());
        commentRepository.save(comment);

        return DefaultMessage.SUCCESSFUL_COMMENT_CREATION;
    }

    @Override
    public List<Comment> viewAllComments(Long postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
        return commentRepository.findAllByPost(post);
    }

    @Override
    public Page<Post> getAllPost(Pagination pagination) {
        Sort sort = Sort.by(pagination.getSortDirection(), pagination.getSortBy());
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), sort);
        Page<Post> post = postRepository.findAll(pageable);

        return post;
    }
}


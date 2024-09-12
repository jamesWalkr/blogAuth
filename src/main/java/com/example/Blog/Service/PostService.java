package com.example.Blog.Service;

import com.example.Blog.DTOS.PostRequest;
import com.example.Blog.Exceptions.PostNotFoundException;
import com.example.Blog.Models.Post;
import com.example.Blog.Repository.PostRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


import static java.util.stream.Collectors.toList;
@Service
public class PostService {

    private UserService userService;
    private PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public void createPost(PostRequest postRequest){
        Post post = mapDtoToPost(postRequest);
        postRepository.save(post);
    }

    public List<PostRequest> showAllPost(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapPostToDto).collect(toList());
    }

    public PostRequest readSinglePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id ));
        return mapPostToDto(post);
    }

    private Post mapDtoToPost(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        User loggedIn = userService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedIn.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }

    private PostRequest mapPostToDto(Post post){
        PostRequest postDto = new PostRequest(post.getId(),post.getUsername(),post.getTitle(),post.getUsername());
        return postDto;
    }

}

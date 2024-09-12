package com.example.Blog.Controllers;

import com.example.Blog.DTOS.PostRequest;
import com.example.Blog.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PostRequest>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPost(), HttpStatus.OK);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<PostRequest> getSinglePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }

}

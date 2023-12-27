package com.ll.medium_clone_coding.domain.home.home.controller;

import com.ll.medium_clone_coding.domain.post.post.entity.Post;
import com.ll.medium_clone_coding.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;

    @GetMapping("/")
    public String main(Model model) {

        List<Post> postList = postService.getList();
        model.addAttribute("postList", postList);

        return "domain/home/home/main";
    }
}

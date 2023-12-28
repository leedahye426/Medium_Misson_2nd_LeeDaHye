package com.ll.medium_clone_coding.domain.post.post.controller;

import com.ll.medium_clone_coding.domain.member.member.entity.Member;
import com.ll.medium_clone_coding.domain.member.member.service.MemberService;
import com.ll.medium_clone_coding.domain.post.post.entity.Post;
import com.ll.medium_clone_coding.domain.post.post.entity.PostForm;
import com.ll.medium_clone_coding.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/post/write")
    public String write(PostForm postForm) {
        return "domain/post/post/post_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/write")
    public String write(@Valid PostForm postForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "domain/post/post/post_form";
        }

        if (postForm.isPublished() != true) {
            postForm.setPublished(false);
        }

        if (postForm.isPaid() != true) {
            postForm.setPaid(false);
        }

        Member member = memberService.getMember(principal.getName());
        Post post = postService.write(member, postForm.getTitle(), postForm.getBody(), postForm.isPublished(), postForm.isPaid());

        return "redirect:/post/%s".formatted(post.getId());
    }

    @GetMapping("/post/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Post> paging = postService.getList(page);
        model.addAttribute("paging", paging);
        model.addAttribute("title", "전체 글 리스트");

        return "domain/post/post/all_list";
    }

    @GetMapping("/b/{username}")
    public String list(Model model, @PathVariable("username") String username) {
        Member member = memberService.getMember(username);

        List<Post> postList = member.getPostList();

        model.addAttribute("postList", postList);
        model.addAttribute("title", "회원 " + member.getUsername() + "의 전체 글 리스트");

        return "domain/post/post/list";
    }

    @GetMapping("/b/{username}/{id}")
    public String detail(Model model, @PathVariable("username") String username, @PathVariable("id") long id) {
        Post post = postService.getPost(username, id);
        model.addAttribute("post", post);

        return "domain/post/post/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/myList")
    public String myList(Model model, Principal principal) {
        Member member = memberService.getMember(principal.getName());
        List<Post> postList = member.getPostList().reversed();
        model.addAttribute("title", "내 글 리스트");
        model.addAttribute("postList", postList);

        return "domain/post/post/list";
    }


    @GetMapping("/post/{id}")
    public String detail(@PathVariable long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);

        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member member = memberService.getMember(username);

            if(!member.isPaid()) {
                model.addAttribute("content", "이 글은 유료 멤버십 전용 입니다.");
            } else {
                model.addAttribute("content", post.getBody());
            }
        } else {
            model.addAttribute("content", "이 글은 유료 멤버십 전용 입니다.");
        }


        return "domain/post/post/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{id}/modify")
    public String modifyForm(@PathVariable("id") long id, Principal principal, PostForm postForm) {
        Post post = postService.getPost(id);

        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        postForm.setTitle(post.getTitle());
        postForm.setBody(post.getBody());
        postForm.setPublished(false);
        postForm.setPaid(false);
        return "domain/post/post/post_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{id}/modify")
    public String modify(@PathVariable("id") long id, @Valid PostForm postForm, BindingResult bindingResult, Principal principal) {
        Post post = postService.getPost(id);

        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (bindingResult.hasErrors()) {
            return "domain/post/post/post_form";
        }

        if (postForm.isPublished() != true) {
            postForm.setPublished(false);
        }

        if (postForm.isPaid() != true) {
            postForm.setPaid(false);
        }

        postService.modify(post, postForm.getTitle(), postForm.getBody(), postForm.isPublished(), postForm.isPaid());

        return "redirect:/post/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{id}/delete")
    public String delete(@PathVariable("id") long id, Principal principal) {
        Post post = postService.getPost(id);

        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        postService.delete(post);

        return "redirect:/post/myList".formatted(id);
    }


}

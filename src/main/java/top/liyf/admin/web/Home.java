package top.liyf.admin.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Home {


    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('test1')")
    public String test1(Principal principal) {
        System.out.println("principal = " + principal);
        return "test1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAnyAuthority('test2')")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAnyAuthority('test3')")
    public String test3() {
        return "test3";
    }
}

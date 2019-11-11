package top.liyf.admin.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.admin.domain.User;

import java.security.Principal;

@Controller
public class Home {

    @RequestMapping("/login")
    public String login() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println("encode = " + encode);
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('test1')")
    public String test1(Principal principal) {
        System.out.println("principal = " + principal);
        return "test1";
    }

    @GetMapping("/test2")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('test2')")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test3")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('test3')")
    public String test3() {
        return "test3";
    }
}

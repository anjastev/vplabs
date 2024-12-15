package mk.ukim.finki.wp.lab.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showLoginPage(String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "You have successfully logged out.");
        }
        return "login";
    }

    @GetMapping("/sign-in")
    public String showSignInPage() {
        return "sign-in";
    }

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logout";
    }

}

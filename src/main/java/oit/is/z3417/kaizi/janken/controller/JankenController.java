package oit.is.z3417.kaizi.janken.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import oit.is.z3417.kaizi.janken.model.UserMapper;
import oit.is.z3417.kaizi.janken.model.User;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/janken")
  public String janken(Model model) {
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);
    return "janken";
  }
}

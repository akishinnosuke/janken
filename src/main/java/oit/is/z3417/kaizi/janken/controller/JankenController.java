package oit.is.z3417.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3417.kaizi.janken.model.UserMapper;
import oit.is.z3417.kaizi.janken.model.Match;
import oit.is.z3417.kaizi.janken.model.MatchMapper;
import oit.is.z3417.kaizi.janken.model.User;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper; // ← これをここに追加

  @GetMapping("/janken")
  public String janken(ModelMap model) { // ← Model を ModelMap に変更
    // ユーザ情報の取得（既存の処理）
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);

    // 試合結果の取得（追加する処理）
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    return "janken";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model, Principal prin) {
    User cpu = userMapper.selectById(id);
    model.addAttribute("cpu", cpu);
    model.addAttribute("loginUser", prin.getName());
    return "match";
}
}

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
import oit.is.z3417.kaizi.janken.model.MatchInfo;
import oit.is.z3417.kaizi.janken.model.MatchInfoMapper;
import oit.is.z3417.kaizi.janken.model.User;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;
  
  @Autowired
  private MatchInfoMapper matchInfoMapper;

  @GetMapping("/janken")
  public String janken(ModelMap model, Principal prin) {
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    // Task2: アクティブな試合を取得して表示する
    ArrayList<MatchInfo> activeMatches = matchInfoMapper.selectActiveMatches();
    model.addAttribute("activeMatches", activeMatches);

    return "janken";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model, Principal prin) {
    User cpu = userMapper.selectById(id);
    model.addAttribute("cpu", cpu);
    model.addAttribute("loginUser", prin.getName());
    return "match";
  }

  @GetMapping("/wait")
  public String wait(@RequestParam int id, @RequestParam String hand, ModelMap model, Principal prin) {
    User opponent = userMapper.selectById(id);
    User me = userMapper.selectByName(prin.getName());

    MatchInfo info = new MatchInfo();
    info.setUser1(me.getId());
    info.setUser2(opponent.getId());
    info.setUser1Hand(hand);
    info.setIsActive(true);

    matchInfoMapper.insertMatchInfo(info);

    model.addAttribute("loginUser", prin.getName());
    return "wait";
  }
}

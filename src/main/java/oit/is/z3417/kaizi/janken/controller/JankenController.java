package oit.is.z3417.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z3417.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken(@RequestParam(name = "hand", required = false) String myHand, Principal prin, Model model) {
    // 1. ログイン中のユーザー名を取得
    String loginUser = prin.getName();

    // 2. DIされた Entry オブジェクトにユーザーを追加
    this.entry.addUser(loginUser);

    // 3. Modelにデータを登録
    model.addAttribute("entry", this.entry);
    model.addAttribute("loginUser", loginUser);

    // 4. じゃんけんの手が選択された場合の処理（例）
    if (myHand != null) {
      String cpuHand = "Gu"; // CPUの手（例: ぐー固定）
      String result = "";

      if (myHand.equals(cpuHand)) {
        result = "Draw";
      } else if ((myHand.equals("Gu") && cpuHand.equals("Choki")) ||
          (myHand.equals("Choki") && cpuHand.equals("Pa")) ||
          (myHand.equals("Pa") && cpuHand.equals("Gu"))) {
        result = "You Win!";
      } else {
        result = "You Lose...";
      }

      model.addAttribute("myHand", myHand);
      model.addAttribute("cpuHand", cpuHand);
      model.addAttribute("result", result);
    }

    return "janken";
  }
}

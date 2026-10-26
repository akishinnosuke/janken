package oit.is.z3417.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import oit.is.z3417.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  // DIでEntryクラスのインスタンスを注入
  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    // ログインユーザー名を取得
    String loginUser = prin.getName();

    // ユーザーをエントリーリストに追加
    entry.addUser(loginUser);

    // テンプレートにデータを渡す
    model.addAttribute("entry", entry);
    model.addAttribute("loginUser", loginUser);

    return "janken";
  }

  // 以前のじゃんけん処理のGETやPOSTメソッドがこの後に続きます...
}

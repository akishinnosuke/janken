package oit.is.z3417.kaizi.janken.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Entry {
  private ArrayList<String> users = new ArrayList<>();

  // ユーザー一覧を取得
  public ArrayList<String> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

  // 重複を防止してユーザーを追加
  public void addUser(String name) {
    for (String user : this.users) {
      if (user.equals(name)) {
        return; // 既に登録済みの場合は何もしない
      }
    }
    this.users.add(name);
  }
}

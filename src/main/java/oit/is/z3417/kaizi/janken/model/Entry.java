package oit.is.z3417.kaizi.janken.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Entry {
  private ArrayList<String> users = new ArrayList<>();

  public void addUser(String name) {
    // 同じユーザーが複数回リストに追加されないようにチェック
    for (String s : users) {
      if (s.equals(name)) {
        return;
      }
    }
    users.add(name);
  }

  public ArrayList<String> getUsers() {
    return users;
  }
}

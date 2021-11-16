package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {


    private List<User> users = new ArrayList<>();
    @PostMapping("users")
    public void addUser(@RequestBody User user) {
        users.add(new User(user.name, user.age));
    }
// curl -X POST http://localhost:8080/users -H 'Content-Type:application/json' -d '{"name" : "Kate Kadnikova", "age" : "17"}'
    @DeleteMapping("users/{index}")
    public void deleteUser(@PathVariable("index") Integer index) {
        users.remove((int) index);
    }
// curl -X DELETE http://localhost:8080/users/2
    @GetMapping("users/{index}")
    public User getUser(@PathVariable("index") Integer index) {
        return users.get(index);
    }
// curl -X GET http://localhost:8080/users/2
    @GetMapping("users")
    public List<User> getUsers() {
        return users;
    }
// curl -X GET http://localhost:8080/users
    @PutMapping("users/{index}")
    public void updateUserAge(@PathVariable("index") Integer i, @RequestBody int age) {
        users.get(i).setAge(age);
    }
// curl -X PUT http://localhost:8080/users/2 -H 'Content-Type: application/json' -d '7'






    private List<Theme> themes = new ArrayList<Theme>();
    @PostMapping("themes")
    public void addTheme(@RequestBody String themeName) {
        themes.add(new Theme(themeName));
    }
    // curl -X POST http://localhost:8080/themes -H 'Content-Type:text/plain' -d 'the Bible and it's references'
    @DeleteMapping("themes/{index}")
    public void deleteTheme(@PathVariable("index") Integer index) {
        themes.remove((int) index);
    }
    // curl -X DELETE http://localhost:8080/themes/2
    @GetMapping("themes")
    public List<Theme> getThemes() {
        return themes;
    }
    // curl -X GET http://localhost:8080/themes
    @PutMapping("themes/{index}")
    public void updateTheme(@PathVariable("index") Integer i, @RequestBody String themeName) {
        themes.get(i).setThemeName(themeName);
    }
    // curl -X PUT http://localhost:8080/users/2 -H 'Content-Type: text/plain' -d 'Byron and Shakespeare in Chekhov's plays'
    @DeleteMapping("themes")
    public void deleteThemes() {
        for(int i=0;i<themes.size();i++){
            themes.remove(i);
        }
    }
    // curl -X DELETE http://localhost:8080/themes
    @GetMapping("themes/{index}")
    public Theme getTheme(@PathVariable("index") Integer index) {
        return themes.get(index);
    }
    // curl -X GET http://localhost:8080/themes/2
    @GetMapping("themes/number")
    public int getThemesNumber() {
        return themes.size();
    }
    // curl -X GET http://localhost:8080/themes/number





    @PostMapping("themes/{index}/{indUser}/comments")
    public void addThemeComment(@PathVariable("index") Integer i, @PathVariable("indUser") Integer ind, @RequestBody String text) {
        users.get(ind).getComments().add(new Comment(text));
        themes.get(i).getComments().add(new Comment(text));
    }
    // curl -X POST http://localhost:8080/themes/2/0/comments -H 'Content-Type:text/plain' -d 'Moliere wrote the poem "Manfred"'
    @DeleteMapping("themes/{index}/{indUser}/comments/{indexCom}")
    public void deleteThemeComment(@PathVariable("index") Integer index, @PathVariable("indUser") Integer ind, @PathVariable("indexCom") Integer indexCom) {
        users.get(ind).getComments().remove(indexCom);
        themes.get(index).getComments().remove(indexCom);
    }
    // curl -X DELETE http://localhost:8080/themes/2/0/comments/1
    @PutMapping("themes/{index}/{indUser}/comments/{indexCom}")
    public void updateThemeComment(@PathVariable("index") Integer i, @PathVariable("indUser") Integer ind,@PathVariable("indexCom") Integer indexCom, @RequestBody String text) {
        users.get(ind).getComments().get(indexCom).setText(text);
        themes.get(i).getComments().get(indexCom).setText(text);
    }
    // curl -X PUT http://localhost:8080/themes/2/0/comments/1 -H 'Content-Type: text/plain' -d 'Byron and Shakespeare in Chekhov's plays are a tool in the disclosure of the hero'
    @GetMapping("themes/{index}/comments")
    public List<Comment> getThemeComments(@PathVariable("index") Integer i) {
        return themes.get(i).getComments();
    }
    // curl -X GET http://localhost:8080/themes/2/comments


    @GetMapping("users/{index}/comments")
    public List<Comment> getUserComments(@PathVariable("index") Integer i) {
        return users.get(i).getComments();
    }
    // curl -X GET http://localhost:8080/users/2/comments
    @PutMapping("users/{index}/{ind}/comments/{indexCom}")
    public void updateUserComment(@PathVariable("index") Integer i, @PathVariable("ind") Integer ind,@PathVariable("indexCom") Integer indexCom, @RequestBody String text) {
        users.get(i).getComments().get(indexCom).setText(text);
        themes.get(ind).getComments().get(indexCom).setText(text);
    }
    // curl -X PUT http://localhost:8080/themes/2/0/comments/1 -H 'Content-Type: text/plain' -d 'Byron and Shakespeare in Chekhov's plays are a tool in the disclosure of the hero'
}

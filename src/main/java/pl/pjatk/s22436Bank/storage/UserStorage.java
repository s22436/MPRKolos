package pl.pjatk.s22436Bank.storage;

import org.springframework.stereotype.Component;
import pl.pjatk.s22436Bank.models.User;

import java.util.ArrayList;

@Component
public class UserStorage {

    private ArrayList<User> users;

    public UserStorage (){
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public User getUser(String id){
        for(User user: users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public void addUser (User user){
        users.add(user);
    }
}

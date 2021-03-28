package repository;

import model.User;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepo {

    Map<String, User> userMap = new LinkedHashMap<>();

    public void addUser(User user) throws Exception {
        if (userMap.containsKey(user.getId()))
            throw new Exception("User already exists");
        userMap.put(user.getId(), user);
    }

    public Optional<User> getUser(String id) {
        if (!userMap.containsKey(id))
            return Optional.empty();
        return Optional.of(userMap.get(id));
    }
    public Map<String, User> getUserMap() {
        return userMap;
    }

}

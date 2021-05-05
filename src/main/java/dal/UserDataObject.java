package dal;

import Models.User;

public interface UserDataObject {
    User getUser();
    void putUser(User user);
}

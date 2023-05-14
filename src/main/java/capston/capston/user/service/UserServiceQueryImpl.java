package capston.capston.user.service;

import capston.capston.user.model.User;

public interface UserServiceQueryImpl {
    void save(User user);

    User findByStudentId(String studentId);

}

package source.service;


import source.dto.request.UserCreateRequestDto;
import source.entity.User;

public interface UserService {

    public User createUser(UserCreateRequestDto request) throws Exception;
}

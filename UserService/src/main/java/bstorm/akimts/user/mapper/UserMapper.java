package bstorm.akimts.user.mapper;

import bstorm.akimts.user.dto.UserDTO;
import bstorm.akimts.user.entity.User;

public class UserMapper {

    public static UserDTO toDto(User entity){
        if(entity == null)
            return null;

        return new UserDTO(entity.getUsername(), null,entity.getRoles());

    }

}

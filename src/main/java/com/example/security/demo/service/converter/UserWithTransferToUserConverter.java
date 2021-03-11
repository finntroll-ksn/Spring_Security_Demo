package com.example.security.demo.service.converter;
import com.example.security.demo.model.User;
import com.example.security.demo.model.dto.UserTransfer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserWithTransferToUserConverter implements Converter<UserTransfer, User> {
    @Override
    public User convert(UserTransfer source) {
       return User.builder()
               .name(source.getName())
               .id(source.getId())
               .username(source.getUsername())
               .email(source.getEmail())
               .build();
    }
}

package com.example.security.demo.service.converter;

import com.example.security.demo.model.User;
import com.example.security.demo.model.dto.UserTransfer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserLikeAuthorTransferConverter implements Converter<User, UserTransfer> {

    @Override
    public UserTransfer convert(User source) {
        return UserTransfer
                .builder()
                .email(source.getEmail())
                .id(source.getId())
                .name(source.getName())
                .username(source.getUsername())
                .build();
    }
}

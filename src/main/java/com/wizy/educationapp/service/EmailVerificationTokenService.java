package com.wizy.educationapp.service;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.model.EmailVerificationToken;
import com.wizy.educationapp.model.User;

public interface EmailVerificationTokenService {

  EmailVerificationToken findByToken(String token);

  EmailVerificationToken findByUser(User user);

  EmailVerificationToken save(UserDto userDto);
}

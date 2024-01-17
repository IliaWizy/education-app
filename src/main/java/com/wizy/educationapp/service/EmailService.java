package com.wizy.educationapp.service;

import com.wizy.educationapp.dto.UserDto;
import com.wizy.educationapp.model.User;

public interface EmailService {

  User sendMail(UserDto userDto);
}

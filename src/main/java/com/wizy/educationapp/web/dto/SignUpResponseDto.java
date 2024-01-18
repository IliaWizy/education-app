package com.wizy.educationapp.web.dto;

public record SignUpResponseDto(

    String email,

    String firstName,

    String lastName,

    String message) {
}

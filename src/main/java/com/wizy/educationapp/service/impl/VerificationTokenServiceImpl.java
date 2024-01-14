package com.wizy.educationapp.service.impl;

import com.wizy.educationapp.model.User;
import com.wizy.educationapp.model.VerificationToken;
import com.wizy.educationapp.repository.VerificationTokenRepository;
import com.wizy.educationapp.service.VerificationTokenService;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private final VerificationTokenRepository verificationTokenRepository;

  @Transactional
  public VerificationToken findByToken(String token) {
    return verificationTokenRepository.findByToken(token);
  }

  @Transactional
  public VerificationToken findByUser(User user) {
    return verificationTokenRepository.findByUser(user);
  }

  @Transactional
  public void save(User user, String token) {
    VerificationToken verificationToken = new VerificationToken(token, user);
    verificationToken.setExpiryDate(calculateExpiryDate(1440));
    verificationTokenRepository.save(verificationToken);
  }

  private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Timestamp(calendar.getTime().getTime());
  }
}

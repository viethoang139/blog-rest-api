package com.leviethoang.service;

import com.leviethoang.dto.LoginDto;
import com.leviethoang.dto.RegisterDto;

public interface AuthService {
     String login(LoginDto loginDto);

     String register(RegisterDto registerDto);

}

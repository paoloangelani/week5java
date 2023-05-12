package com.U2W2D5.auth.service;

import com.U2W2D5.auth.payload.LoginDto;
import com.U2W2D5.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}

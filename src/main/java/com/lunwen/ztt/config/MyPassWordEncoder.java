package com.lunwen.ztt.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码解释器
 */
public class MyPassWordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence arg0) {
        return arg0.toString();
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return arg1.equals(arg0.toString());
    }
}

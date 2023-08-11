package org.wang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wang.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
public class TestBoot {

    @Resource
    private UserService userService;

    @Test
    public void testDigest() {

        userService.userRegister("winston","123456","me","1");

    }
}

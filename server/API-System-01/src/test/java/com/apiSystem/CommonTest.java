package com.apiSystem;

import com.apiSystem.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {
    @Test
    public void testd5(){
        System.out.println(CommonUtil.md5("123456"+"71c9b"));
    }
}

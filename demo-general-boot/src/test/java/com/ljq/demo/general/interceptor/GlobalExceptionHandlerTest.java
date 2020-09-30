package com.ljq.demo.general.interceptor;

import com.ljq.demo.general.common.exception.BizException;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

class GlobalExceptionHandlerTest {

    @Test
    void exceptionHandler() {
        System.out.println(BizException.class.getSimpleName());
    }
}
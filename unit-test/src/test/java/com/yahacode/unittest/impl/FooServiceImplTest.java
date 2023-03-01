package com.yahacode.unittest.impl;

import com.yahacode.unittest.FooRepository;
import com.yahacode.unittest.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class FooServiceImplTest {

    @InjectMocks
    FooService fooService = new FooServiceImpl();

    @Mock
    FooRepository fooRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("简单的单元测试")
    void isOdd() {
        assertTrue(fooService.isOdd(1));
    }

    @Test
    @DisplayName("抛出异常")
    void isOddTooBig() {
        assertThrows(RuntimeException.class, () -> fooService.isOdd(10001));
    }

    @DisplayName("参数化测试")
    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7})
    void isOddParam(int param) {
        assertTrue(fooService.isOdd(param));
    }

    @Test
    @DisplayName("mock依赖")
    void getOne() {
        Mockito.when(fooRepository.getOne(Mockito.anyInt())).thenReturn("aaa");
        assertEquals("aaa", fooService.getOne(1));
    }


    @Test
    @DisplayName("field注入")
    void getThreshold() {
        ReflectionTestUtils.setField(fooService, "THRESHOLD", 100);
        assertEquals(100, fooService.getThreshold());
    }
}
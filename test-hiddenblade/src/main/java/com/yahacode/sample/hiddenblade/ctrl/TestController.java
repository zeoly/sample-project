package com.yahacode.sample.hiddenblade.ctrl;

import com.yahacode.hiddenblade.app.support.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hb")
public class TestController {

    @Autowired
    private RedisOperator operator;

    @GetMapping("/hexpire")
    public void hset0() {
        operator.expire("thb");
    }

    @GetMapping("/hset0/{field}/{value}")
    public void hset0(@PathVariable String field, @PathVariable String value) {
        operator.hSet("thb", field, value);
    }

    @GetMapping("/hset1/{field}/{value}")
    public void hset1(@PathVariable String field, @PathVariable int value) {
        operator.hSet("thb", field, value);
    }

    @GetMapping("/hset/{field}")
    public void hset(@PathVariable String field) {
        operator.hSet("thb", field, new Test("user" + Math.random(), LocalDateTime.now()));
    }

    @GetMapping("/hset")
    public void hset() {
        Map<String, Object> map = new HashMap<>();
        map.put("111", "222");
        map.put("333", "444");
        operator.hSet("thb", map);
    }

    @GetMapping("/hget0/{field}")
    public String hget0(@PathVariable String field) {
        return operator.hGet("thb", field, String.class);
    }

    @GetMapping("/hget1/{field}")
    public int hget1(@PathVariable String field) {
        return operator.hGet("thb", field, Integer.class);
    }

    @GetMapping("/hget/{field}")
    public Test hget(@PathVariable String field) {
        return operator.hGet("thb", field, Test.class);
    }

    @GetMapping("/hget")
    public Map<Object, Object> hgetAll() {
        return operator.hGet("thb");
    }

}

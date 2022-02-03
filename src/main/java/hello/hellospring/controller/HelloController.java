package hello.hellospring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /*
        1) 정적 컨텐츠
        2) mvc template 방식
        3) api 방식
    */

    @GetMapping("hello")
    //@ResponseBody 없으면 View Resolver에 던짐. 있으면 Http 응답에 그대로 넘김
    public String hello(Model model){
        model.addAttribute("data", "hello!!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //요청한 데이터 그대로 리턴해줌
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody //객체 반환하면 json형태로 반환(디폴트)
    //1. HttpMessageConverter 동작
    //2. String이면 StringHttpMessageConverter,
    //   객체면 MappingJackson2HttpMessageConverter
    //   byte 처리 등 기타 : HttpMessageConverter 동작
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @Getter
    @Setter
    static class Hello {
        private String name;

        /* 자바빈 규약
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }*/
    }
}
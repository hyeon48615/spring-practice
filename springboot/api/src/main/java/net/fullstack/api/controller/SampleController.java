package net.fullstack.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/test")
    public void hello(
            @RequestParam(name="msg", required = false, defaultValue = "World") String msg,
            Model model
    ) {
        model.addAttribute("msg", msg);
    }

    @GetMapping("/test2")
    public String[] hello2() {
        return new String[] { "Hello", "Spring", "Boot" };
    }

    @GetMapping("/ex1")
    public void ex1(Model model) {
        model.addAttribute("num11", "11");
        model.addAttribute("num22", "21");
        model.addAttribute("num33", "31");
    }

    @GetMapping("/ex2")
    public void ex2(Model model) {
        List<String> lists = Arrays.asList("Hello", "Spring", "Boot1", "Boot2", "Boot3", "Boot4");
        model.addAttribute("lists", lists);
    }

    @GetMapping("/ex3")
    public void ex3(Model model) {
        List<String> lists = Arrays.asList("Hello", "Spring", "Boot1", "Boot2", "Boot3", "Boot4");
        model.addAttribute("lists", lists);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
        List<String> lists = Arrays.asList("Hello", "Spring", "Boot1", "Boot2", "Boot3", "Boot4");
        model.addAttribute("lists", lists);
    }

    @GetMapping("/ex5")
    public void ex5(Model model) {
        List<String> lists = Arrays.asList("Hello", "Spring", "Boot1", "Boot2", "Boot3", "Boot4");

        Map<String, String> map = new HashMap<>();
        map.put("key1", "홍길동");
        map.put("key2", "20");

        SampleDTO dto = new SampleDTO();
        dto.p1 = "값p1";
        dto.p2 = "값p2";
        dto.p3 = "값p3";

        model.addAttribute("lists", lists);
        model.addAttribute("map", map);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/ex6")
    public void ex6(Model model) {
        List<SampleMember> lists = Arrays.asList(
                new SampleMember("홍길동1", 20),
                new SampleMember("홍길동2", 30),
                new SampleMember("홍길동3", 40),
                new SampleMember("홍길동4", 10));

        model.addAttribute("lists", lists);
    }

}

class SampleDTO {
    public String p1;
    public String p2;
    public String p3;

    public String getP1() { return p1; }
    public String getP2() { return p2; }
    public String getP3() { return p3; }
}

class SampleMember {
    SampleMember() {}
    SampleMember(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public int age;

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SampleMember [name=" + name + ", age=" + age + "]";
    }
}
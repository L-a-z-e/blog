package com.laze.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
		// 파일 리턴 기본경로 : src/main/resources/static 그래서 앞에 /를 안쓰면 statichome.html이됨
	}

}

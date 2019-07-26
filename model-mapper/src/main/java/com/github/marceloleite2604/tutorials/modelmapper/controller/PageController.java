package com.github.marceloleite2604.tutorials.modelmapper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	
	@GetMapping(path = Paths.INDEX)
	public String getIndex() {
		return Templates.INDEX;
	}
	
	public static final class Paths {
		public static final String INDEX = "index";
		
		private Paths() {
			// Private constructor to avoid object instantiation.
		}
	}
	
	static final class Templates {
		private static final String INDEX = "index";
		
		private Templates() {
			// Private constructor to avoid object instantiation.
		}
	}
}

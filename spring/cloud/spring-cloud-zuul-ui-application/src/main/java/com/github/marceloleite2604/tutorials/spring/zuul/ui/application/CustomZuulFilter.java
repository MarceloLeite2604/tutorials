package com.github.marceloleite2604.tutorials.spring.zuul.ui.application;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CustomZuulFilter extends ZuulFilter {
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("Test", "TestSample");
        return null;
    }
 
    @Override
    public boolean shouldFilter() {
       return true;
    }

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1110;
	}
}
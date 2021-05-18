package tgc.edu.mcy.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	//通过RedirectStrategy对象负责所有重定向事务
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 重写handle方法，方法中通过RedirectStrategy对象重定向到指定的URL
	 * */

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		//通过determineTargetUrl方法返回需要跳转的URL
		String targetUrl = determineTaargetUrl(authentication);
		//重定向请求到指定的url
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * 从Authentication对象中提取当前登录用户的角色，并根据其角色返回适当的URL
	 * */
	private String determineTaargetUrl(Authentication authentication) {
		String url = "";
		
		//获取当前登录用户的角色权限集合
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		
		//将角色名称添加到list集合中
		for(GrantedAuthority a: authorities) {
			roles.add(a.getAuthority());
		}
		
		//判断不同角色跳转到不同的URL
		if(isSystem(roles)) {
			url = "/system";
		}else if(isAdmin(roles)) {
			url = "/admin";
		}else if(isUser(roles)) {
			url = "/user";
		}else {
			url = "/accessDenied";
		}
		return url;
	}

	private boolean isSystem(List<String> roles) {
		if(roles.contains("ROLE_SYSTEM")) {
			return true;
		}
		return false;
	}

	private boolean isUser(List<String> roles) {
		if(roles.contains("ROLE_USER") || roles.contains("ROLE_TEACHER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if(roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}

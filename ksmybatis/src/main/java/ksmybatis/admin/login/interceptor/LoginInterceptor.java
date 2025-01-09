package ksmybatis.admin.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String sessionId = (String) session.getAttribute("SID");
		
		boolean isProcess = true;
		
		if(sessionId == null) {
			response.sendRedirect("/admin/login");
			isProcess = false;
		}else {
			String memberGrade = (String) session.getAttribute("SGRD");
			
			String requestUri = request.getRequestURI();
			if("mbr_grd_2".equals(memberGrade) || "mbr_grd_3".equals(memberGrade)) {				
				if(requestUri.indexOf("/admin/member") > -1) {
					response.sendRedirect("/admin");
					isProcess = false;
				}
			}
			
			
		}
		
		return isProcess;
	}
}












package com.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestAction extends Action{
	//서블릿역할
	@Override
	public ActionForward execute(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//데이터는 form에 담긴다
		
		String uri = request.getRequestURI();
		
		if(uri.indexOf("/write_ok.do")!=-1) {
			
			TestForm f = (TestForm)form;
			
			request.setAttribute("vo", f);
			
			return mapping.findForward("ok");
		}
		return mapping.findForward("error");
	}
	
}

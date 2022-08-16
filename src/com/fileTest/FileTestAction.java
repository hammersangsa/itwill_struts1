package com.fileTest;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.FileManager;
import com.util.MyPage;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class FileTestAction extends DispatchAction{


	public ActionForward write(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("write");
	}

	public ActionForward write_ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + "pds" + File.separator + "saveFile";
		
		FileTestForm f = (FileTestForm)form;
		
		//���� ���ε�
		String newFileName = FileManager.doFileUpload(f.getUpload(), savePath);
		
		//DB�� ����
		if(newFileName!=null) { 
			
			int maxNum = dao.getIntValue("fileTest.maxNum");
			
			f.setNum(maxNum + 1);
			f.setSaveFileName(newFileName);
			f.setOriginalFileName(f.getUpload().getFileName());
			
			dao.insertData("fileTest.insertData", f);
		}
		
		return mapping.findForward("write_ok");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		String cp = request.getContextPath();
		
		MyPage myPage = new MyPage();
		
		int numPerPage = 4;
		int totalPage = 0;
		int totalDataCount = 0;
		String pageNum = request.getParameter("pageNum");
		int currentPage = 1;
		
		if(pageNum!=null && !pageNum.equals("")) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		//�����Ͱ���
		totalDataCount = dao.getIntValue("fileTest.dataCount");
		
		//��������
		if(totalDataCount!=0) {
			totalPage = myPage.getPageCount(numPerPage, totalDataCount);
		}
		
		if(currentPage>totalPage) {
			currentPage = totalPage;
		}
		
		Map<String, Object> hMap = new HashMap<String, Object>();
	
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> lists = dao.getListData("fileTest.listData", hMap);
		
		//�Ϸù�ȣ �����
		Iterator<Object> it = lists.iterator();
		int listNum, n = 0;
		String str;
		
		while(it.hasNext()) {
			
			FileTestForm dto = (FileTestForm)it.next();
			
			listNum = totalDataCount - (start + n -1);
			dto.setListNum(listNum);
			n++;
			
			//���ϴٿ���
			str = cp + "/file.do?method=download&num=" + dto.getNum();
			dto.setUrlFile(str);
		}
		
		String urlList = cp + "/file.do?method=list";
		
		request.setAttribute("lists", lists);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalDataCount", totalDataCount);
		request.setAttribute("pageIndexList",
				myPage.pageIndexList(currentPage, totalPage, urlList));
		
		return mapping.findForward("list");
	}
	
	public ActionForward delete_ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + "pds" + File.separator + "saveFile";
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		FileTestForm dto =
				(FileTestForm)dao.getReadData("fileTest.readData", num);
		
		//���� ����
		FileManager.doFileDelete(dto.getSaveFileName(), savePath);
		//DB����
		dao.deleteData("fileTest.deleteData", num);
		
		return mapping.findForward("delete_ok");
	}
	//�ٿ�ε� �� �� �� â�� ���ƾ��ϹǷ� null�� ����
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + "pds" + File.separator + "saveFile";
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		FileTestForm dto =
				(FileTestForm)dao.getReadData("fileTest.readData", num);
		
		boolean flag = 
				FileManager.doFileDownload(response, dto.getSaveFileName(),
						dto.getOriginalFileName(), savePath);

		if(!flag) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script type='text/javascript'>");
			out.print("alert('Error: Download!!!');");
			out.print("history.back()");
			out.print("</script>");
		}
		
		return mapping.findForward(null);
	}
	
}


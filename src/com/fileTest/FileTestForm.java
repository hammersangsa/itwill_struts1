package com.fileTest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileTestForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private int num;
	private String subject;
	private String saveFileName;
	private String originalFileName;
	
	//FormFile�� upload ������ ó���ϴ� ����
	//2�� �̻��� ����ó���� �迭�� �Ѵ�
	private FormFile upload;
	
	//����Ʈ �Ϸù�ȣ
	private int listNum;
	//���� �ٿ�ε� ���
	private String urlFile;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public FormFile getUpload() {
		return upload;
	}
	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getUrlFile() {
		return urlFile;
	}
	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}
	
}

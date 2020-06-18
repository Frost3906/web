<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//파일 다운로드
	String fileName = request.getParameter("fileName");

	//다운로드 받을 서버 폴더
	String downPath = "d:\\upload";
	//다운받을 파일 경로 생성
	String filePath = downPath+"/"+fileName;
	
	//response 객체를 이용해 header에 붙여서 보내기
	response.setContentType("application/octtet-stream");
	fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
//	int start =fileName.indexOf('_');
//	fileName = fileName.substring(start+1);
	response.setHeader("Content-Disposition", "attachment;filename="+fileName.substring(fileName.lastIndexOf('_')+1));
	BufferedOutputStream buf = new BufferedOutputStream(response.getOutputStream());
	
	FileInputStream in = new FileInputStream(filePath);
	int read = 0;
	byte[] b = new byte[4096];
	while((read=in.read(b))!=-1){
		buf.write(b,0,read);
	}
	buf.flush();
	buf.close();
	in.close();
%>
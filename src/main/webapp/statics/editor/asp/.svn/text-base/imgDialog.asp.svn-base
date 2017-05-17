<%@LANGUAGE="VBSCRIPT" CODEPAGE="936"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="../../admin/images/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.4.4.min.js"></script>
<style type="text/css">

.imgDialogList img{ border:solid 3px #D1D1CF;width:230px; height:150px;}
.imgDialogList a:link{
  float:left; margin-left:4px; margin-top:4px;
}
.imgDialogList a:hover img{border:solid 3px #999999;}
</style>
<title>无标题文档</title>
</head>

<body>

<div class="box1">
	<div class="top_center">
		<div class="top_left">
			<div class="top_right"></div>
		</div>
	</div>
	<div class="mid_left">
		<div class="mid_right">
		  <div class="box1_content">
<form name="form1" method="post" action="imgDialogUpload.asp" enctype="multipart/form-data">
<input type=file name="img">
<input type=submit name="submit" value="提交">
</form>		
<div class="imgDialogList" >
<%
Function GetFileList()
	Dim s_CurrDir
	Dim s_List, i
	Dim o_FSO, o_Folder, o_Files, o_File, s_FileName
	s_CurrDir = "../../uploadfile/"
	On Error Resume Next
	Set o_FSO = Server.CreateObject("Scripting.FileSystemObject")
	Set o_Folder = o_FSO.GetFolder(server.mappath(s_CurrDir))
	If Err.Number>0 Then
		Exit Function
	End If
	Set o_Files = o_Folder.Files
	i = -1
	s_List = ""
	For Each o_File In o_Files
		s_FileName = o_File.Name
		if s_FileName<>"Thumbs.db" then 
		%>
		<a ondblclick="top.mainFrame().editor.insertHtml('<img src=\'<%=websiteurl%>/uploadfile/<%=s_FileName%>\' />');ownerDialog.close();"><img src="<%=s_CurrDir&s_FileName%>"/></a>
		<%
		end if
	Next
	Set o_Folder = Nothing
	Set o_Files = Nothing
	Set o_FSO = Nothing
End Function
call  GetFileList()
%>
</div>
	  </div>
		</div>
	</div>
	<div class="bottom_center">
		<div class="bottom_left">
			<div class="bottom_right"></div>
		</div>
	</div>
</div>
</body>
</html>

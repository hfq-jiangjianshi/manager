
<!-- #include file="upfile_class.asp" -->
<%

On Error Resume Next
Dim oUpload, oFile,sUploadDir
sUploadDir="../../uploadfile/"
Set oUpload = New upfile_class
oUpload.GetData 10000*1024
If oUpload.Err > 0 Then
	Select Case oUpload.Err
		Case 1
		Response.Write "你没有上传数据呀???是不是搞错了??"
		Case 2
		Response.Write "你上传的文件超出我们的限制,最大10M"	
	End Select
else 
	Set oFile = oUpload.File("img")
	sFileExt = LCase(oFile.FileExt)	
	
	sOriginalFileName = oFile.FileName
	sSaveFileName = GetRndFileName(sFileExt)
	
	Dim str_Mappath
	str_Mappath = sUploadDir & sSaveFileName	
	oFile.SaveToFile server.MapPath(str_Mappath)
	Set oFile = Nothing
	Set oUpload = Nothing
	'Response.redirect("imgDialog.asp")
	response.Write("<script language=javascript>top.Dialog.alert('上传成功!',function(){ownerDialog.close();});top.mainFrame().setProPic('"&sSaveFileName&"');</script>") 
End If


Function FormatTime(s_Time, n_Flag)
Dim y, m, d, h, mi, s
FormatTime = ""
If IsDate(s_Time) = False Then Exit Function
y = cstr(year(s_Time))
m = cstr(month(s_Time))
If len(m) = 1 Then m = "0" & m
d = cstr(day(s_Time))
If len(d) = 1 Then d = "0" & d
h = cstr(hour(s_Time))
If len(h) = 1 Then h = "0" & h
mi = cstr(minute(s_Time))
If len(mi) = 1 Then mi = "0" & mi
s = cstr(second(s_Time))
If len(s) = 1 Then s = "0" & s
Select Case n_Flag
Case 1
FormatTime = y & "-" & m & "-" & d & " " & h & ":" & mi & ":" & s
Case 2
FormatTime = y & "-" & m & "-" & d
Case 3
FormatTime = h & ":" & mi & ":" & s
Case 4
FormatTime = y & m & d
Case 5
FormatTime = y & m & d & h & mi & s
End Select
End Function

Function GetRndFileName(sExt)
Dim sRnd
Randomize
sRnd = Int(900 * Rnd) + 100
GetRndFileName = FormatTime(Now(), 5) & sRnd & "." & sExt
End Function

%>
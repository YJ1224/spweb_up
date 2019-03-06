function de(index){
	
	$(".delivery1_btn"+index).click(function() {
		$(".delivery"+index).val("배송 중");
		run(index);
	});

	$(".delivery2_btn"+index).click(function() {
		$(".delivery"+index).val("배송 완료");
		run(index);

	});	
}

function run(index) {
	$(".deliveryForm"+index).submit();
}

function idCheck(){
if(document.frm.userid.value == ""){
	alert('아이디를 입력하여 주십시오.');
	document.frm.userid.focus();
	return;
}
var url="idCheck.do?userid="+document.frm.userid.value;
window.open(url,"_blank_1", "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=450, height=200");
}
function idok(){
	opener.frm.userid.value=document.frm.userid.value;
	opener.frm.reid.value=document.frm.userid.value;
	self.close();
}
/**
 * 
 */

$(function(){
	$("#modifyform").validate({
		
		rules:{
			current_password:{
				required: true
			},
			
			new_password:{
				required: true,
				validPwd: true
			},
			confirm_password:{
				required: true,
				equalTo: "#new_password"
			}
		},
			
		messages:{
				current_password: {
					required: "필수입력사항입니다."
				},
				
				new_password: {
					required: "필수입력사항입니다."
				},
				confirm_password: {
					required: "필수입력사항입니다.",
					equalTo: "비밀번호가 다릅니다."
					
				}
				
		},
		
		errorPlacement:function(error,element){
			$(element).closest("form").find("small[id='"+element.attr("id")+"']").append(error);
		}
	
			
		
	
	})

})
$.validator.addMethod("validPwd",function(value){
	const regPwd = /^(?=.+[A-z])(?=.+[0-9])(?=.+[!@#$%^&*])[A-z0-9!@#$%^&*]{8,15}$/;
	
	return regPwd.test(value);
},"비밀번호는 영문자,숫자,특수문자(!,@,#,$,%,^,&,*)의 조합으로 8~15자리여야 합니다.");


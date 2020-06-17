/**
 * joinForm 유효성 검증하기
 */

//규칙
//아이디 : 영문자, 숫자, 특수문자 조합으로 6~12자리
//비밀번호 : 영문자, 숫자 특수문자 조합으로 8~15자리
//이름 : 2~4자리
//성별 : 필수입력
//이메일 : 필수입력, 이메일검증

$(function(){
	$("#joinform").validate({
		
		rules:{
			
			userid:{
				required: true,
				validID: true
			},
			password:{
				required: true,
				validPwd: true
			},
			confirm_password:{
				required: true,
				equalTo: "#password"
			},
			name:{
				required: true,
				validName: true
			},
			gender:{
				required: true
			},
			email:{
				required: true,
				email: true
			}
		},
		messages:{
			userid:{
				required: "ID는 필수 항목입니다.",
			},
			password:{
				required: "비밀번호는 필수 항목입니다.",
			},
			confirm_password:{
				required: "비밀번호는 필수 항목입니다.",
				equalTo: "비밀번호가 다릅니다."
			},
			name:{
				required: "이름은 필수 항목입니다.",
			},
			gender:{
				required: "성별은 필수 항목입니다."
			},
			email:{
				required: "이메일은 필수 항목입니다.",
				email: "이메일 형식으로 입력해주세요."
					
			}
			

		},//message end
		errorPlacement: function(error,element){//에러 메세지 위치 지정
			$(element).closest("form").find("small[id='"+element.attr("id")+"']").append(error);
		
		}
			
				
	})
	
})

$.validator.addMethod("validID",function(value){
	const regID = /^(?=.*[A-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-z0-9!@#$%^&*]{6,12}$/;
	return regID.test(value);
},"ID는 영문자,숫자,특수문자(@,#,$)의 조합으로 6~12자리여야 합니다.");

$.validator.addMethod("validPwd",function(value){
	const regPwd = /^(?=.+[A-z])(?=.+[0-9])(?=.+[!@#$%^&*])[A-z0-9!@#$%^&*]{8,16}$/;
	return regPwd.test(value);
},"비밀번호는 영문자,숫자,특수문자(!,@,#,$,%,^,&,*)의 조합으로 8~15자리여야 합니다.");


$.validator.addMethod("validName",function(value){
	const regName = /^[가-힣]{2,4}$/;
	return regName.test(value);
},"이름을 2~4자로 입력해주세요.");




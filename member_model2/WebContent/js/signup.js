/**
 *  signupFrom.html 유효성 검증
 */

$(function(){
	$("#signupForm").validate({
		//검증 규칙
		rules:{
			username:{//이름은 필수속성, 글자는 2~4 허용
				required:true,
				minlength:2,
				maxlength:4
			},
			password:{//비밀번호는 필수, 글자는 8 ~ 15허용
				required:true,
				rangelength:[8,15] // == minlength, maxlength
			},
			confirm_password:{//비밀번호는 필수, 글자는 8~15 허용,password와 일치
				required: true,
				rangelength: [8,15],
				equalTo: "#password"
			},
			email:{ //email 필수, email검증
				required: true,
				email: true
			},
			policy:{
				required: true
			},
			topics:{
				required : "#newsletter:checked",
				minlength : 2
			}
		},
		//메세지
		messages:{
			username:{//이름은 필수속성, 글자는 2~4 허용
				required:"이름은 필수 요소 입니다.",
				minlength:"이름은 최소 2자리 입니다.",
				maxlength:"이름은 최대 4자리까지 허용됩니다."
			},
			password:{//비밀번호는 필수, 글자는 8 ~ 15허용
				required:"비밀번호는 필수 요소 입니다.",
				rangelength:"비밀번호는 8 ~ 15자리까지 허용됩니다."
			},
			confirm_password:{//비밀번호는 필수, 글자는 8~15 허용,password와 일치
				required: "비밀번호는 필수 요소 입니다.",
				rangelength: "비밀번호는 8 ~ 15자리까지 허용됩니다.",
				equalTo: "비밀번호가 같지 않습니다."
			},
			email:{ //email 필수, email검증
				required: "이메일은 필수 요소입니다.",
				email: "이메일 형식을 확인해 주세요"
			},
			policy:{
				required: "우리 정책에 동의를 필요로 합니다."
			},
			topics: "관심사를 적어도 2개는 표시하세요"
		},//message end
		//에러메세지 위치 지정
		errorElement:"em",
		errorPlacement : function(error,element){
			error.addClass("help-block");
			if(element.prop("type") === "checkbox"){
				error.insertAfter(element.next("label"));
			}else {
				error.insertAfter(element);
			}
		}
	})//검증
	$("#newsletter").click(function(){
		let topics = $("#newsletter_topics");
		if(topics.css("display")==="none"){
			$(topics).css("display", "inline-block");
		}else {
			$(topics).css("display","none");
		}
	})
	
	
})
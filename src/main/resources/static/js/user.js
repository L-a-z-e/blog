/**

 */
let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ // function(){}, ()=>{} 를 사용하는 이유는 this를 바인딩하기 위해서
			this.save();	
			
		});
	
	},
	
	save: function(){
		let data = {
			userName:$("#userName").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};
		
		// console.log(data);
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type:"POST",
			url:"/blog/api/user",
			data:JSON.stringify(data), //http body 데이터 -> MIME TYPE 필요
			contentType:"application/json;charset=utf-8",
			dataType:"json" //요청을 서버로 해서 응답이 왔을 때, 그 데이터는 기본적으로 문자열 -> 생긴게 Json이라면 => javascript object로 변환
		}).done(function(response){
			alert("회원가입이 완료되었습니다.");
			console.log(response);
			//location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
			
		}); 

	}
};

index.init();
$().ready(function(){
	$("#login-Form").validate({
		rules:{
			userName:{
				required:true
			},
			password:{
				required:true
			}	
		},
		message:{
			userName:{
				required:"Please enter a username"
			},
			password:{
				required:"Please enter a password"
			}
		},
		submitHandler: function(form) {
	          form.submit();
	      }
	});	
});
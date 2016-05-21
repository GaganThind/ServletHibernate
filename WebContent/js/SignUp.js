$(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: "1950:2050"
    });
  });
  
//form validation rules
$().ready(function(){
  $("#registration-Form").validate({
      rules: {
          firstName: {
        	  required:true
          },
          lastName: {
        	  required:true
          },
          userName: {
        	  required:true
          },
          dob:{
        	  required:true
          },
          phoneNumber: {
        	  required:true,
        	  minlength:10,
        	  maxlength:10
          },
          email: {
              required: true,
              email: true
          },
          password: {
              required: true,
              minlength: 8
          },
          confirmPassword: {
        	  required: true,
              minlength: 8,
        	  equalTo: "#password"
          }
          //agree: "required"
      },
      messages: {
          firstName: {
        	  required: "Please enter your firstname"
    	  },
          lastName: {
        	  required: "Please enter your lastname"
    	  },
          userName: {
        	  required: "Please enter your username"
    	  },
          dob: {
        	  required: "Please enter your date of birth",
          },
          phoneNumber:{
        	  required: "Please enter your phone number",
        	  minlength: "Phone number should be 10 digits only and without prefixing 0",
        	  maxlength: "Phone number should be 10 digits only and without prefixing 0"
          },
          email: "Please enter a valid email address",
          password: {
              required: "Please provide a password",
              minlength: "Your password must be at least 8 characters long"
          },
          confirmPassword: {
              required: "Please provide a password",
              minlength: "Your password must be at least 8 characters long",
              equalTo: "Passwords don't match"
          },
          
          //agree: "Please accept our policy"
      },
      submitHandler: function(form) {
          form.submit();
      }
  });
  });
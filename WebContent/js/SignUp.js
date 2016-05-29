$(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: "1950:2050"
    });
  });

$.validator.addMethod( "strongPassword", function( value, element ) {
	return this.optional( element ) || value.length>=8 && /\d/.test( value ) && /[a-z]/i.test(value);
}, "Your password must be atleast 8 characters long and should contain atleast a Digit" );
  
//form validation rules
$().ready(function(){
  $("#registration-Form").validate({
      rules: {
          firstName: {
        	  required:true,
        	  lettersonly: true
          },
          lastName: {
        	  required:true,
        	  lettersonly: true
          },
          userName: {
        	  required:true,
        	  alphanumeric: true
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
              strongPassword: true
          },
          confirmPassword: {
        	  required: true,
        	  equalTo: "#passwordId"
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
        	  required: "Please enter your username",
        	  alphanumeric: "Please enter only Letters, numbers and underscore for username"
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
              required: "Please provide a password"
          },
          confirmPassword: {
              required: "Please provide a password",
              equalTo: "Passwords don't match"
          },
          
          //agree: "Please accept our policy"
      },
      submitHandler: function(form) {
          form.submit();
      }
  });
  });
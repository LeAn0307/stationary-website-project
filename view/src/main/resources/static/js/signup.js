(function ($) {
    $(document).ready(function() {
        $('#btn_sign_up').click(function (){
            event.preventDefault();
            //alert('hello');
            var name = $("#user_name").val();
            var email = $("#user_email").val();
            var pass = $("#user_password").val();
            var pass1 = $("#user_password_comp").val();
            var sdt = $("#user_number").val();
            var address = $("#user_address").val();


            if(pass!==pass1){
                alert('Password khong trung khop');
            } else if(name==""||name==null){
                alert('Ten khong duoc de trong')
            } else if(email==""||email==null){
                alert('Email khong duoc de trong')
            } else if(pass==""||pass==null){
                alert('Password khong duoc de trong')
            } else if(sdt==""||sdt==null){
                alert('SDT khong duoc de trong')
            } else if(address==""||address==null){
                alert('Dia chi khong duoc de trong')
            } else{
                var settings = {
                    "url": "http://localhost:8765/auth/register",
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                      "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                      "userName": name,
                      "address": address,
                      "phone": sdt,
                      "email": email,
                      "accountPassword": pass
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        alert("Đã có lỗi xảy ra");
                        //console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
                    }),
                  };
                  
                  $.ajax(settings).done(function (response) {
                    console.log(response);
                    alert('Đăng ký thành công');
                    window.location.href = "index.html";
                  });
            }
        });
        
    });
})(jQuery);

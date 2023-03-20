
const collapse = document.getElementById('collapseExample');
function checkLoginFunc() {
    const loginbtn = document.getElementById('loginBtn');
    var jwt = "";
    var decoded="";
    if (document.cookie.indexOf("token") != -1) 
    {
        jwt = document.cookie.split(";").find(row => row.startsWith('token='))?.split('=')[1];
        console.log(jwt);
        decoded = jwt_decode(jwt);
        console.log(decoded);
        console.log(decoded.userId);
        loginbtn.innerText = 'Hello ' + decoded.fullName;
        loginbtn.setAttribute("data-toggle", "collapse");
        
        getCountProductOfCart1(decoded.userId,jwt);

        } 
        else {
        loginbtn.innerText = 'Đăng nhập';
        loginbtn.setAttribute("data-toggle", "");
    }
}

function Login() {
    if(!localStorage.getItem('isLoggedIn')) {
        window.location.href = "/login.html";
    }
}

function LogOut() {
    localStorage.clear();
    window.location.href = "/login.html";
}

function getCountProductOfCart1(cartId,jwt)
    {
         $.ajax({
        url: 'http://localhost:8765/api/cartproducts/counterproductive/'+cartId,
        type: 'get',
        dataType: "json",
        contentType : "application/json",
        headers: {
            "Authorization": "Bearer "+jwt
        },
        success: function(data) {

            document.getElementById('countproduct').innerText =(data);
        },
        error: function(data) {

            // Some error in ajax call
            console.log(data);
        },
    });
}
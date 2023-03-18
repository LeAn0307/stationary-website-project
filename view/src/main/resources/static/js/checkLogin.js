
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
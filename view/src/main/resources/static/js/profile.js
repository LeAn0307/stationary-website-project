(function ($) {
    "use strict";
    function getInfoFromToken(jwt) {
        const decoded = jwt_decode(jwt);
        return decoded;
    }
    $(document).ready(function () {
        var jwt = document.cookie.split(";").find(row => row.startsWith('token=')).split('=')[1];
        const objectUser = getInfoFromToken(jwt);
        fetch("http://localhost:8080/users/" + objectUser.userId, {
            method: 'GET',
            headers: {
                "Authorization": "Bearer " + jwt
            }
        })
            .then((response) => response.json())
            .then((data) => {
                let userInfo = "";
                userInfo += `<div class="card mb-4">
            <div class="card-body">
              <div class="row">
                <div class="col-sm-3">
                  <p class="mb-0">Họ tên</p>
                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${data.userName}</p>
                </div>
              </div>
              <hr>
              <div class="row">
                <div class="col-sm-3">
                  <p class="mb-0">Email</p>
                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${objectUser.sub}</p>
                </div>
              </div>
              <hr>
              <div class="row">
                <div class="col-sm-3">
                  <p class="mb-0">Phone</p>
                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${data.phone}</p>
                </div>
              </div>
              <hr>
              <div class="row">
                <div class="col-sm-3">
                  <p class="mb-0">Địa chỉ</p>
                </div>
                <div class="col-sm-9">
                  <p class="text-muted mb-0">${data.address}</p>
                </div>
              </div>
            </div>
          </div>`;
                document.getElementById("user-info").innerHTML = userInfo;
            })
    });
    const pageLinks = document.querySelectorAll('.page-item');

    pageLinks.forEach(link => {
        link.addEventListener('click', function () {
            // Remove "active" class from all links
            pageLinks.forEach(link => {
                link.classList.remove('active');
                link.classList.add('default');
            });

            // Add "active" class to clicked link
            this.classList.remove('default');
            this.classList.add('active');
        });
    });

})(jQuery)
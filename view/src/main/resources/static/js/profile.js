(function ($) {
  //"use strict";
  // function getInfoFromToken(jwt) {
  //     const decoded = jwt_decode(jwt);
  //     return decoded;
  // }
  var userId = 1;
  var cartId = 1;
  var jwt = "";
  var decoded = "";
  if (document.cookie.indexOf("token") != -1) {
    jwt = document.cookie.split(";").find(row => row.startsWith('token='))?.split('=')[1];
    console.log(jwt);
    decoded = jwt_decode(jwt);
    userId = decoded.userId;
    cartId = decoded.userId;
    console.log(decoded);
    console.log(decoded.userId);
  } else {
    window.location.href = "login.html";
  }
  $(document).ready(function () {
    var jwt = document.cookie.split(";").find(row => row.startsWith('token=')).split('=')[1];
  //  const objectUser = getInfoFromToken(jwt);
    console.log(userId);
    getinfoUser();
    async function getinfoUser()
    {
      await  fetch("http://localhost:8765/api/users/" + decoded.userId, {
      method: 'GET',
      headers: {
        "Authorization": "Bearer " + jwt
      }
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
        let userInfo = "";
        let userInfoShort = "";
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
    <p class="text-muted mb-0">${data.email}</p>
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
        userInfoShort += `
<div class="card mb-4">
                <div class="card-body text-center">
                  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                    class="rounded-circle img-fluid" style="width: 150px;">
                  <h5 class="my-3">${data.userName}</h5>
                  <p class="text-muted mb-1">${data.phone}</p>
                  <p class="text-muted mb-4">${data.email}</p>
                  <div class="d-flex justify-content-center mb-2">
                    <button type="button" class="btn btn-primary">Follow</button>
                    <button type="button" class="btn btn-outline-primary ms-1">Message</button>
                  </div>
                </div>
              </div>`;
        document.getElementById("user-info").innerHTML = userInfo;
        document.getElementById("user-info-short").innerHTML = userInfoShort;

      })
      .catch((error) => {
        console.error(error);
      });
    }
    // var jwt = document.cookie.split(";").find(row => row.startsWith('token=')).split('=')[1];
    // const objectUser = getInfoFromToken(jwt);
    // fetch("http://localhost:8080/users/" + objectUser.userId, {
    //       fetch("http://localhost:8765/users/1")
    //       //  {
    //       //   method: 'GET'
    //         //, headers: {
    //         //     "Authorization": "Bearer " + jwt
    //         // }
    //         .then((response) => {response.json()})
    //         .then((data) => {
    //           console.log(data);
    //                     })
  });
  const formatter = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',

    // These options are needed to round to whole numbers if that's what you want.
    //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
    //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
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
  //const url = "http://localhost:8765/api/historyOrder/?id="+;
  var userId = 1;
  const baseUrl = "http://localhost:8765";
  const statusMap = {
    1: "Đang xử lý",
    2: "Đã xác nhận",
    3: "Đang vận chuyển",
    4: "Đã giao hàng",
    5: "Đã nhận hàng",
    6: "Đã thanh toán",
    7: "Đã hủy"
  };
  async function loadOrderDetails() {
    try {
      const response = await fetch(baseUrl + "/api/" + "bills/customerid/" + userId, {
        method: 'GET',
        headers: {
          "Authorization": "Bearer " + jwt
        }
      });
      const data = await response.json();
      var orderdetail = "";

      // create table rows
      for (const rowData of data) {
        const date = new Date(rowData.dateOrder);
        const year = date.getFullYear().toString().padStart(4, "0");
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");
        var dateorder = `${day}-${month}-${year}`;
        var orderdetailChild = "";
        const status = statusMap[rowData.idBillStatus];
        console.log(status);
        orderdetailChild += `<div class="d-flex justify-content-between align-items-center">
          <div>
            <p class="text-muted mb-2">ID đơn hàng  <span class="fw-bold text-body">${rowData.id}</span></p>
            <p class="text-muted mb-0">Thời gian <span class="fw-bold text-body">${dateorder}</span> </p>
            <p class="text-muted mb-0">Thời gian <span class="fw-bold text-body"> Hình thức giao hàng (COD) </span> </p>
            
          </div>
        </div>
        <div class="card-body p-4">`;

        var temp = await loadOrderDetailIteam(rowData.id);

        orderdetailChild += temp +
          ` 
        <div> <h5 class="style14">Trạng thái đơn hàng</h5>
        <div class="progress-bar" role="progressbar" style="width: 25%;" aria-valuenow="25" aria-valuemin="25"
               aria-valuemax="100">${status}+</div>
               </div>
        </div>
        <hr>
        `;

        orderdetail += orderdetailChild;
      }

      // add table to document
      document.getElementById("order-detail").innerHTML = orderdetail;
    } catch (error) {
      console.log(error);
    }
  }
  loadOrderDetails();


  async function loadOrderDetailIteam(bilId) {
    var orderdetailChild = "";
    var urldetailitem = "http://localhost:8765/api/historyOrder/?id=" + bilId;
    try {
      const response = await fetch(urldetailitem);
      const data = await response.json();

      var orderdetailItem = "";
      console.log(orderdetailItem);
      console.log(data);

      // create table rows
      data.forEach((rowDatadetail) => {
        console.log(rowDatadetail);

        orderdetailItem += `
        <div class="d-flex flex-row mb-4 pb-2">
          <div class="flex-fill">
            <h4 class="bold">${rowDatadetail.productName}</h4>
            <p class="text-muted"> Số lượng: ${rowDatadetail.quantity} </p>
            <p class="text-muted"> Màu sắc ${rowDatadetail.productColor} </p>
            <h4 class="mb-3">${formatter.format(rowDatadetail.price)} <span class="small text-muted">  </span></h4>
          </div>
          <div>
            <img class="align-self-center img-fluid"
              src="${baseUrl + "/images/product/" + rowDatadetail.productImage}" width="250">
          </div>
        </div> 
        <hr>
        `;
      });

      orderdetailChild += orderdetailItem;
      console.log(orderdetailChild);
      return orderdetailChild;
    } catch (error) {
      console.error(error);
    }
  }

})(jQuery)
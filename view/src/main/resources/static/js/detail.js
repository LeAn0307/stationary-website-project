(function ($) {
    var jwt="" ;
    var decoded="";
    if (document.cookie.indexOf("token") != -1) 
            {
                jwt = document.cookie.split(";").find(row => row.startsWith('token='))?.split('=')[1];
                console.log(jwt);
                decoded = jwt_decode(jwt);
                console.log(decoded);
                console.log(decoded.userId);
            } else {
                //window.location.href = "login.html";
            }   
    var url_string = window.location.href; 
    var url = new URL(url_string);
    var id = url.searchParams.get("id");
    let baseURL ="http://localhost:8765";
    let productid=id;
    var cardId=decoded.userId;
    var userId=decoded.userId;
    console.log(productid);
    $.ajax({
        type:"GET",
        url:"http://localhost:8765/api/carts/userid/"+ decoded.userId,
        headers: {
            "Authorization": "Bearer "+jwt
        },
        success:function (data){
            cardId=data.id;
            console.log(cardId);
        },
        error: function (xhr, status, error){
            console.error(error);
            console.log('chua co dang nhap');
            //alert(error);
        }
    });
    // fetch(baseURL+'/products/14')
    //     .then(response => response.json())
    //     .then(data => {
    //         // do something with the data
    //         document.getElementById('productName').innerHTML = JSON.stringify(data["name"]);
    //     })
    //     .catch(error => {
    //         console.error('There was a problem with the fetch operation:', error);
    //     });
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',

        // These options are needed to round to whole numbers if that's what you want.
        //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
        //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
    });
    $.ajax({
        url: baseURL+'/api/products/'+productid,
        type: 'get',
        dataType: "json",
        contentType : "application/json",
        success: function(data) {

            document.getElementById('productName').innerHTML =(data["name"]);
            document.getElementById('description').innerHTML = (data["description"].replace(/(?:\r\n|\r|\n)/g, '<br>'));
            document.getElementById('price').innerHTML = (formatter .format(data["price"]));
            document.getElementById('color').innerHTML =  (data["color"]);
            if((data["avgrating"])!=null)
            {
                document.getElementById('avgratingproduct').innerHTML= reviewstarproduct(data["avgrating"]);
            }
            else{
                //document.getElementById('avgratingproduct').innerHTML= reviewstarproduct(1);
            }
            document.getElementById('imageproduct').setAttribute("src", baseURL+"/images/product/"+data["image"]);
            getCountProductOfCart(cardId)
        },
        error: function(data) {

            // Some error in ajax call
            console.log(data);
        },
    });

    function getCountProductOfCart(cardId)
    {
         $.ajax({
        url: baseURL+'/api/cartproducts/counterproductive/'+cardId,
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
    function reviewstar(rating){
        let stars = "";

        for (let i = 1; i <= 5; i++) {
            if (i <= rating) {
                stars += '<i class="fas fa-star"></i>';
            } else if (i - rating <= 0.5) {
                stars += '<i class="fas fa-star-half-alt"></i>';
            } else {
                stars += '<i class="far fa-star"></i>';
            }
        }
        return stars;
    }
    function reviewstarproduct(rating){
        let stars = "";

        for (let i = 1; i <= 5; i++) {
            if (i <= rating) {
                stars += '<small class="fas fa-star"></small>';
            } else if (i - rating <= 0.5) {
                stars += '<small class="fas fa-star-half-alt"></small>';
            } else {
                stars += '<small class="far fa-star"></small>';
            }
        }
        return stars;
    }
    loadReview();
    function loadReview()
    {
    $.ajax({
        url: baseURL+'/api/ratings/product/'+productid,
        type: 'get',
        dataType: "json",
        contentType : "application/json",
        success: function(data) {
            let listreview='';

            data.map(x=>{
                console.log("aaaaaa");
                let rating =x["rateScore"];
                let reviewsta=reviewstar(rating);
                let reviews= '<div class="media mb-4">' +
                    '<img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">' +' <div class="media-body">'+
                    reviewsta +
                    '<h6>'+x["userName"] + '</h6>' +
                    '<div class="text-primary mb-2" id="rating"></div>'+
                    '<p>'+x["comment"]+'</p>' +
                    '</div>' +
                    '</div>';

                listreview=listreview+reviews;

            })
            document.getElementById("reviews").innerHTML=listreview;

            document.getElementById("sum").innerHTML='('+data.length + ' reviews'+')';
            document.getElementById("reviewcounts").innerHTML='Đánh giá( '+data.length + ')';

        },
        error: function(data) {

            // Some error in ajax call
            console.log(data);
        },
    });
}
    const form = document.querySelector('#review');

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        const message = document.querySelector('#message').value;
        const productId = productid;
        const ratingScore=rating;

        // Perform validation on form fields
        if (!message ) {
            alert('Please fill out all required fields.');
            return;
        }

        // Construct the data object to send to the server
        const data = {
            comment: message,
            rateScore: ratingScore,
            userId: userId,
            productId:productid
        };

        // Send form data to server using AJAX
        const xhr = new XMLHttpRequest();
        xhr.open('POST', baseURL+'/rating');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
               // alert('Your review has been submitted successfully!');
                swal("Thông báo! ", "Đánh giá thành công", "success");
                form.reset();
                loadReview();
                
            } else {
               // alert('Error submitting form. Please try again later.');
                swal("Thông báo! ", "Đánh giá không thành công", "error");
            }
        };
        xhr.send(JSON.stringify(data));
    });
    const stars = document.querySelectorAll('.star1');
    const ratingValue = document.querySelector('.rating-value');
    let rating = 0;

    stars.forEach((star, index) => {
        star.addEventListener('click', () => {
            rating = index + 1;
            //ratingValue.innerText = `You've rated this product ${rating} out of ${stars.length} stars.`;
            updateActiveStars();
        });
    });

    function updateActiveStars() {
        stars.forEach((star, index) => {
            console.log(index);
            if (index < rating) {
                star.classList.add('active');
            } else {
                star.classList.remove('active');
            }
        });
    }
    let ft =`<div class="col-12">
    <nav>
      <ul class="pagination justify-content-center">`;
getListofSuggestProduct();
function getListofSuggestProduct() {
$.ajax({
type: "GET",
url: "http://localhost:8765/api/products/count",
success: function (data) {
//alert(data.valueOf())
let offset = data.valueOf() / 3;
if (data.valueOf() % 3 != 0) {
offset++;
}
for (let i = 1; i < offset; i++) {
ft += `<button class="btn btn-primary btn-ft">
            ${i}
            </button>`
}
ft += `</ul>
    </nav>
</div>`;
},
error: function (xhr, status, error) {
console.error(error);
//alert(error);
}
})
fetch("http://localhost:8765/api/products/offset/0")
.then((response) => response.json())
.then((data) => {
let productList = "";
data.forEach((product) => {
productList += `
<div class="col-lg-4 col-md-6 col-sm-6 pb-1">
<div class="product-item bg-light mb-4">
<div class="product-img position-relative overflow-hidden">
<img class="img-fluid w-100" src="http://127.0.0.1:8765/images/product/${product.image}" alt="">
<div class="product-action">
<a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-shopping-cart"></i></a>
<a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>
<a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
<a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
</div>
</div>
<div class="text-center py-4">
<a class="h6 text-decoration-none text-truncate" href="detail.html?id=${product.id}">${product.name}</a>
<div class="d-flex align-items-center justify-content-center mt-2">
<h5>${product.price}</h5><h6 class="text-muted ml-2"><del>Discount: ${product.discount}</del></h6>
</div>
<div class="d-flex align-items-center justify-content-center mb-1">
<small class="fa fa-star text-primary mr-1"></small>
<small class="fa fa-star text-primary mr-1"></small>
<small class="fa fa-star text-primary mr-1"></small>
<small class="fa fa-star text-primary mr-1"></small>
<small class="fa fa-star text-primary mr-1"></small>
<small>(${product.avgrating})</small>
</div>
</div>
</div>
</div>
`;
});
productList += ft;
document.getElementById("product-list").innerHTML = productList;
});
// Back to top button
$(document).on("click", '.btn-ft', function () {
var button = $(this);
//document.getElementById("product-list").innerHTML=``;

fetch("http://127.0.0.1:8765/api/products/offset/" + button.text())
.then((response) => response.json())
.then((data) => {
let productList = "";
console.log(data)
data.forEach((product) => {
    var avgratingproducthtml="";
    if(product.avgrating!=null) {
        avgratingproducthtml+= reviewstarproduct(product.avgrating);
    }
    else
        avgratingproducthtml+=reviewstarproduct(0);
    productList += `
<div class="col-lg-4 col-md-6 col-sm-6 pb-1">
<div class="product-item bg-light mb-4">
  <div class="product-img position-relative overflow-hidden">
    <img class="img-fluid w-100" src="http://127.0.0.1:8765/images/product/${product.image}" alt="">
    <div class="product-action">
      <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-shopping-cart"></i></a>
      <a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>
      <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
      <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
    </div>
  </div>
  <div class="text-center py-4">
    <a class="h6 text-decoration-none text-truncate" href="detail.html?id=${product.id}">${product.name}</a>
    <div class="d-flex align-items-center justify-content-center mt-2">
      <h5>${product.price}</h5><h6 class="text-muted ml-2"><del>Discount: ${product.discount}</del></h6>
    </div>
    <div class="d-flex align-items-center justify-content-center mb-1">
     ${avgratingproducthtml} 
      <small>(${product.avgrating})</small>
    </div>
  </div>
</div>
</div>
`;
});
productList += ft;
document.getElementById("product-list").innerHTML = productList;
});


});
};
    // Xử lý phần tăng giảm số lượng

    var elementMinus=document.querySelector('#minus');
    var elementPlus=document.querySelector('#plus');
    elementMinus.addEventListener("click", minusQuantity);
    elementPlus.addEventListener("click", plusQuantity);
    function minusQuantity() {
        var number=document.querySelector('#quantity').value;
       number=parseInt(number)-1;
        document.querySelector('#quantity').setAttribute("value",number);
    }
    function plusQuantity() {
        var number=document.querySelector('#quantity').value;
        number=parseInt(number)+1;
        document.querySelector('#quantity').setAttribute("value",number);
    }
    // Xử lý thêm vào giỏ hàng
    var elementAddToCard=document.querySelector('#addToCard');
    elementAddToCard.addEventListener("click", addToCardAction);
    function addToCardAction()
    {
        if (jwt!=""){
            const number=document.querySelector('#quantity').value;
            const data = {
                "quantity":number,
                "productId": productid,
                "cartId": cardId
            };

            const xhr = new XMLHttpRequest();
            xhr.open('POST', baseURL+'/api/cartproducts');
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.setRequestHeader("Authorization", "Bearer "+jwt);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    alert('Thêm sản phẩm thành công');
                    getCountProductOfCart(cardId);
                    
                } else {
                    alert('Lỗi hệ thống');
                }
            };
            xhr.send(JSON.stringify(data));
        } else {
                window.location.href = "/login.html";
        }
    }
})(jQuery);

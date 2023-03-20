
function reviewstarproduct(rating){
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
// "https://crossorigin.me/" +
fetch("http://localhost:8765/api/products")
    .then((response) => response.json())
    .then((data) => {
        let productList = "";
        data.forEach((product) => {
            var avgratingproducthtml="";
            if(product.avgrating!=null) {
                avgratingproducthtml+= reviewstarproduct(product.avgrating);
            }
            else
                avgratingproducthtml+=reviewstarproduct(0);
            productList += `
   <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
     <div class="product-item bg-light mb-4">
       <div class="product-img position-relative overflow-hidden">
         <img class="img-fluid w-100" src="http://localhost:8765/images/product/${product.image}" alt="detail.html?id=${product.id}">
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

        document.getElementById("product-list").innerHTML = productList;
    });
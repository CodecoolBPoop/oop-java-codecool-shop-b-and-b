function addToCart(e) {
    e.preventDefault();
    let button = e.target;
    $.ajax({
        url: "/ajax/add-to-cart",
        type: "POST",
        data: {action: "add",id: button.id},
        success: function (answer) {
            console.log("item added to cart");
            $("#badge").text(answer);
        },
        error: function () {
            alert("Something went wrong");
        }
    });
}

function toShoppingCart(e) {
    e.preventDefault();
    window.location = '/shopping-cart';
}



function main() {
    $(".addToCart").click(addToCart);
    $(".shoppingCart").click(toShoppingCart);
    $(".checkout").click(checkoutModal);
}

main();
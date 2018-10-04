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

function changeQuantity(e) {
    e.preventDefault();
    var quantityInputValue = e.target.parentElement.firstChild.value;
    var name = e.target.dataset.name;
    console.log(name);
    $.ajax({
        url: "/ajax/change-quantity",
        type: "POST",
        data: {quantity: quantityInputValue, productName: name},
        success: function (answer) {
        },
        error: function () {
            alert("Something went wrong");
        }
    });

}

function main() {
    $(".addToCart").click(addToCart);
    $(".shoppingCart").click(toShoppingCart);
    $(".refresh").click(changeQuantity);
}

main();
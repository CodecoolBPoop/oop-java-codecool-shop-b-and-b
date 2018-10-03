function addToCart(e) {
    e.preventDefault();
    let button = e.target;
    $.ajax({
        url: "/",
        type: "POST",
        data: {action: "add",id: button.id},
        success: function () {
            console.log("item added to cart");
        },
        error: function () {
            alert("Something went wrong");
        }
    });
}

function main() {
    $(".addToCart").click(addToCart);
}

main();
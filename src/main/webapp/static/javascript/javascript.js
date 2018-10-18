function addToCart(e) {
    e.preventDefault();
    var button = e.target;
    $.ajax({
        url: "/ajax/add-to-cart",
        type: "POST",
        data: {action: "add",id: button.id},
        success: function (answer) {
            if (answer === "") {
                alert("Please log in to add to cart!");
            } else {
                $("#badge").text(answer);
            }
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
    var quantity = e.target.parentElement.firstChild.value;
    if (quantity == "" || quantity >99) {
        e.target.parentElement.firstChild.value = e.target.dataset.quantity;
    }else {
        var name = e.target.dataset.name;
        $.ajax({
            url: "/ajax/change-quantity",
            type: "POST",
            data: {quantity: quantity, productName: name},
            success: function (answer) {
                window.location.href = "/shopping-cart";
            },
            error: function () {
                alert("Something went wrong");
                window.location.reload();
            }
        });
    }
}

function getSessionID() {
    $.ajax({
        url: "/ajax/get-session-id",
        type: "POST",
        data: {},
        success: function (answer) {
            console.log(answer);
            setButtons(answer);
        },
        error: function () {
            alert("Something went wrong");
        }
    });
}

function setButtons(sessionID) {
    console.log("Session id is: ");
    console.log(sessionID);
    if (sessionID == "") {
        $(".register").html("<a href='/sign-up'>Sign up</a>");
        $(".login").html("<a href='/login'>Log in<a/>");
    } else {
        $(".user").html("<a href='/user'>Profile</a>");
        $(".logout").html("<a href='/logout'>Logout<a/>");
    }
}

function main() {
    getSessionID();
    $(".addToCart").click(addToCart);
    $(".shoppingCart").click(toShoppingCart);
    $(".refresh").click(changeQuantity);
}

main();
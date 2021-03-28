$(function () {
    $("#btn-login").click(function () {
        login();
        return false;
    })
    $(".input-login").on("keydown",function (e) {
        if(e.keyCode == 13) {
            login();
            return false;
        }
    });
})

function login() {
    let username = $("#username").val();
    let password = $("#password").val();
    if (username.length > 2 && password.length > 2 && regexUsername(username) && regexPassword(password)) {
        let loginForm = {
            username: username,
            password: password
        }
        nguoiDungLogin(loginForm).then(rs => {
            if(rs.message === "login success") {
                sessionStorage.setItem("ss_lg",rs.data);
                window.location.href = "man-hinh-theo-doi";
            } else {
                $(".form-group").addClass("has-error");
            }
        }).catch(err => console.log(err))
    } else {
        $(".form-group").addClass("has-error");
    }
}
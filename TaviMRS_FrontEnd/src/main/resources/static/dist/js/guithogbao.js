$(document).ready(function () {
    handleClickNguoiDungGroup();
});

function handleClickNguoiDungGroup() {
    $(".com1 span:nth-child(2)").click(function () {
        $(this).parent().siblings(".com2").addClass('d-none');
        $(this).siblings().removeClass("d-none");
        $(this).addClass("d-none")
    });
    $(".com1 span:nth-child(1)").click(function () {
        $(this).parent().siblings(".com2").removeClass('d-none');
        $(this).siblings().removeClass("d-none");
        $(this).addClass("d-none")
    });
    $(".com1 input").click(function () {
        $(this).parents(".com1").siblings(".com2").find("input").prop("checked", $(this).is(":checked"));
    })
    $.widget.bridge('uibutton', $.ui.button);
}
$(document).ready(function () {
    $(".retabctsct-title span").click(function () { 
        $(this).addClass("bg3c");
        $(this).removeClass("bgw1")
        $(this).siblings().removeClass("bg3c");
        $(this).siblings().addClass("bgw1");
    });
    $(".retabctsct-title span:nth-child(1)").click(function (e) { 
        $(".retabctsct-table").removeClass("d-none");
        $(".retabctsct-form").addClass("d-none");
    });
    $(".retabctsct-title span:nth-child(2)").click(function (e) { 
        $(".retabctsct-table").addClass("d-none");
        $(".retabctsct-form").removeClass("d-none");
    });
});
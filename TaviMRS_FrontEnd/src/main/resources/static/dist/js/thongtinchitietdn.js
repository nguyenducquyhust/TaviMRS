$(document).ready(function () {
    // $(document).ready(function () {
    //     $('.js-example-basic-single').select2();
    // });
    $(".etptab-title span").click(function () { 
        $(this).addClass("bg3c");
        $(this).removeClass("bgw1")
        $(this).siblings().removeClass("bg3c");
        $(this).siblings().addClass("bgw1");
    });
    $(".eptplusi i").click(function (e) { 
       $(".eptplustable").toggleClass("d-none");
    });
    $(".eptplustb-bt button:nth-child(2)").click(function () { 
        $(".eptplustable").addClass("d-none");
    });
    for (let index = 1; index < 8; index++){
        $(`.etptab-title span:nth-child(${index})`).click(function () {   
            $(`.etptab .etptab-content:nth-child(${index+1})`).removeClass("d-none");
            $(`.etptab .etptab-content:nth-child(${index+1})`).siblings(".etptab-content").addClass("d-none");
        });
    }
});
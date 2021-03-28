$(document).ready(function () {
    $(".tdicon .fas.fa-tablet-alt").click(function () { 
        $(this).parent().siblings(".tdinfo__detail").toggleClass("d-none")
    });
    $(".tbwicon i ").click(function () { 
        $(this).parent().siblings(".tdinfo__detail").toggleClass("d-none")
    });
    $(".content-header span:nth-child(3)").click(function () { 
        $(this).addClass("d-none");
        $(".content-header h1:nth-child(1)").addClass("d-none");
        $(".content-header h1:nth-child(2)").removeClass("d-none");
        $(".content-header span:nth-child(4)").removeClass("d-none");   
        $(".content .tbct:nth-child(1)").addClass("d-none");
        $(".content .tbct:nth-child(2)").removeClass("d-none");
    });
    $(".content-header span:nth-child(4)").click(function () { 
        $(this).addClass("d-none");
        $(".content-header h1:nth-child(1)").removeClass("d-none");
        $(".content-header h1:nth-child(2)").addClass("d-none");
        $(".content-header span:nth-child(3)").removeClass("d-none"); 
        $(".content .tbct:nth-child(2)").addClass("d-none");  
        $(".content .tbct:nth-child(1)").removeClass("d-none");  
    });
});
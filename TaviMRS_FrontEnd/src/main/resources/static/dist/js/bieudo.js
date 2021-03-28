$(document).ready(function () {
    $(".mineralnamechart span").click(function () {
        $(this).addClass("bg3c");
        $(this).removeClass("bgw1");
        $(this).siblings().removeClass("bg3c");
        $(this).siblings().addClass("bgw1");
    });
    $(".mineralnamechart span:nth-child(1)").click(function () {
        $(".mineralct1").removeClass("d-none");
        $(".mineralct2").addClass("d-none");
    });
    $(".mineralnamechart span:nth-child(2)").click(function () {
        $(".mineralct2").removeClass("d-none");
        $(".mineralct1").addClass("d-none");
        $(".mineralct2").CanvasJSChart(options2);
    });
    window.onload = function () {
        var options1 = {
            animationEnabled: true,
            title: {
                text: "Nhóm khoáng sản - 2019"
            },
            axisY: {
                title: "Tấn",
                suffix: " ton",
                includeZero: false
            },
            axisX: {
                title: "Khoáng sản"
            },
            data: [{
                type: "column",
                yValueFormatString: "#,##0.0#" % "",
                dataPoints: [
                    { label: "Bari", y: 10.09 },
                    { label: "Đồng", y: 9.40 },
                    { label: "Sắt", y: 8.50 },
                    { label: "Vàng", y: 7.96 },
                    { label: "Nhôm", y: 7.80 },
                    { label: "Titan", y: 7.56 },
                    { label: "Kim cương", y: 7.20 },
                    { label: "Natri", y: 10.1 },
                    { label: "Crom", y: 7.1 },
                    { label: "Than cốc", y: 11.1 },
                    { label: "Vôi", y: 13.1 },
                    { label: "Kali", y: 8.1 }

                ]
            }]
        };

        $(".mineralct1").CanvasJSChart(options1);

    }
    var options2 = {
        animationEnabled: true,
        title: {
            text: "Nhóm công ty - 2019"
        },
        axisY: {
            title: "Tấn",
            suffix: " ton",
            includeZero: false
        },
        axisX: {
            title: "Công ty"
        },
        data: [{
            type: "column",
            yValueFormatString: "#,##0.0#" % "",
            dataPoints: [
                { label: "Công Ty A", y: 10.09 },
                { label: "Công Ty A", y: 9.40 },
                { label: "Công Ty A", y: 8.50 },
                { label: "Công Ty A", y: 7.96 },
                { label: "Công Ty A", y: 7.80 },
                { label: "Công Ty A", y: 7.56 },
                { label: "Công Ty A", y: 7.20 },
                { label: "Công Ty A", y: 10.1 },
                { label: "Công Ty A", y: 7.1 },
                { label: "Công Ty A", y: 11.1 },
                { label: "Công Ty A", y: 13.1 },
                { label: "Công Ty A", y: 8.1 }
            ]
        }]
    };
});
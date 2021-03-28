$(document).ready(function () {
    window.onload = function () {
        var optionsChart = {
            animationEnabled: true,
            title: {
                text: ""
            },
            axisY: {
                title: "Tấn",
                suffix: "",
                includeZero: false
            },
            axisX: {
                title: "Khoáng sản"
            },
            data: [{
                type: "column",
                yValueFormatString: "#,##0.0#"%"",
                dataPoints: [
                    { label: "Than", y: 10.09 },	
                    { label: "Vật liệu xây dựng", y: 9.40 },	
                    { label: "Sét gạch ngói", y: 8.50 },
                    { label: "Quặng đồng", y: 7.96 },	
                    
                ]
            }]
        };
        $("#chartContainer").CanvasJSChart(options);
        
    }
});
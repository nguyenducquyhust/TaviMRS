var titleMoCamera, listMoCamera, titleChart, chartContainer;
var listMo = null;
var lengthListMo = 0;
var indexMo = 0;
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
            { label: "Khoáng sản", y: 11 },
            { label: "Khoáng sản", y: 11 },
            { label: "Khoáng sản", y: 11 },
            { label: "Khoáng sản", y: 11 },
        ]
    }]
};
$(function() {
    titleMoCamera = $("#title-mo-camera");
    listMoCamera = $("#list-mo-camera");
    titleChart = $("#title-chart");
    chartContainer = $("#chartContainer");
    //run view camera
    getAllMo().then(rs => {
        loopCameraMo();
    }).catch(err => {
        console.log(err);
    })
    //end run view camera

    //run bieu do
    drawChart(chartContainer, optionsChart);
    getBieuDoAndViewTitle();
    //end run bieu do
})

//Camera
function loopCameraMo() {
    callCameraMo(indexMo);
    setInterval(callCameraMo, TIME_REPEAT_CAMERA);
}

async function getAllMo() {
    await moFindAll().then(rs => {
        if(rs.result === "found") {
            listMo = rs.data.currentElements;
            lengthListMo = listMo.length;
        } else {
            alterWarning(`Server Error ${err.statusText}`, TIME_ALTER);
        }
    }).catch(err => {
        console.log(err);
        alterWarning("Server Error", TIME_ALTER);
    })
    return listMo;
}

function callCameraMo() {
    // console.log("loop camera " + indexMo);
    let mo = listMo[indexMo];
    indexMo = indexMo === lengthListMo - 1 ? 0 : indexMo + 1;
    cameraFindByMo(mo.idMo, 1, 16).then(rs => {
        if(rs.result === "found") {
            setViewCameraMo(mo, rs.data.currentElements);
        } else {
            alterInfo("Chưa có thông tin camera của mỏ hiện tại", TIME_ALTER);
            setViewCameraMo(mo, []);
        }
    }).catch(err => {
        console.log(err);
        alterWarning("Server Error", TIME_ALTER);
    })
}

function setViewCameraMo(mo, list) {
    titleMoCamera.html(`<i class="fas fa-camera"></i> ${viewField(mo.doanhNghiep === null ? null : mo.doanhNghiep.tenDoanhNghiep + " - ")}${viewField(mo.diaChi)}`);
    setViewCameraDefault();
    if(list !== null && list.length > 0) {
        list.map((item, index) => {
            listMoCamera.find(`.aiimg:nth-child(${index+1})`).html(`<iframe src="${item.thongSo}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" class="view-camera"></iframe>`);
        })
    }
}

function setViewCameraDefault() {
    let view = "";
    for(let i = 1; i < 17; i++) {
        view += `<div class="aiimg">
                        <img src="./dist/img/no-image.jpg" alt="">
                    </div>`;
    }
    listMoCamera.html(view)
}
//End Camera

//Bieu Do
function getBieuDoAndViewTitle() {
    let {month, year} = getMonthAndYear();
    titleChart.html(`<span>Biểu đồ tổng hợp trữ lượng khái thác khoáng sản từ tháng 1 đến tháng ${month} năm ${year} </span>`);
    let ngayDau = new Date(`${year}-01-01`).toISOString();
    let ngayCuoi = new Date(`${year}-12-31`).toISOString();
    bieuDoTongHop(ngayDau, ngayCuoi).then(rs => {
        if(rs.result === "found") {
            viewBieuDo(rs.data)
        } else {
            alterInfo("Chưa có thông tin thống kê biểu đồ", TIME_ALTER);
        }
    }).catch(err => {
        alterWarning("Server Error", TIME_ALTER);
        console.log(err);
    })
}

function viewBieuDo(list) {
    console.log(list);
    let arr =  list.map(item => {
       return {
           label: item.x,
           y: item.y
       }
    })
    if (arr !== null && arr.length != 0) {
        optionsChart.data[0].dataPoints = arr;
        console.log(optionsChart);
        drawChart(chartContainer, optionsChart);
    } else {
        alterInfo("Chưa có thông tin thống kê biểu đồ", TIME_ALTER);
    }
}

//End Bieu Do
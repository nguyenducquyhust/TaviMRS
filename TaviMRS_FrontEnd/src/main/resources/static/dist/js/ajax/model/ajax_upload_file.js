async function uploadFiles(selectorInput) {
    let result = null;
    let files = selectorInput.get(0).files;
    if(files.length > 0) {
        let formData  = new FormData($("#form-file")[0]);
        await ajaxUploadFile("v1/public/upload-multi-files", formData, 1).then(rs => {
            result = rs;
        }).catch(err => {
            alterDanger("Lỗi tải tập đính kèm", TIME_ALTER);
            console.log(err);
        })
    } else {
        result = [];
    }
    return result;
}
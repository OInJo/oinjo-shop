function win_close() {
    window.close();
};

// 주소 api
function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var postcode = data.zonecode;
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var detailAddr = "(상세정보입력)";
            var combinedAddress =' (' + postcode + ') ' + roadAddr + ' ' + detailAddr;
            document.getElementById('combined-address').value = combinedAddress;
            document.getElementById('combined-address').disabled = false;
            document.querySelector('.save-btn').disabled = false;
        }
    }).open();
}
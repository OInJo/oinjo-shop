function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var postcode = data.zonecode;
            var roadAddr = data.roadAddress;
            var detailAddr = "(상세정보입력)";
            var combinedAddress = ' (' + postcode + ') ' + roadAddr + ' ' + detailAddr;
            document.getElementById('combined-address').value = combinedAddress;
        }
    }).open();
}
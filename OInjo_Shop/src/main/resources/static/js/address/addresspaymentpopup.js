function win_close() {
    window.close();
};

// 주소 api
function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var postcode = data.zonecode;
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var detailAddr = "(상세정보입력)";
            var combinedAddress = postcode + ' ' + roadAddr + ' ' + detailAddr;

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('combined-address').value = combinedAddress;
            // document.getElementById('postcode').value = data.zonecode;
            // document.getElementById("road-address").value = roadAddr;



        }
    }).open();
}

// 정보 전달 함수
function sendData() {
    // 정보 추출
    var postcode = document.getElementById("postcode").value;
    var roadAddress = document.getElementById("road-address").value;
    var detailAddress = document.getElementById("detail-address").value;

    // 부모 페이지로 데이터 전달
    var data = {
      postcode: postcode,
      roadAddress: roadAddress,
      detailAddress: detailAddress
    };

    // 데이터 전송
    window.opener.postMessage(data, "*");

    // 팝업 닫기
    window.close();
  }
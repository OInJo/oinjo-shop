function address_popup() {
    var popup = window.open('/address/list', '배송지', 'width=600, height=700, left=0, top=0');

    '<p><button onclick="window.close()">닫기</button>';
  // 팝업 창의 document 객체를 사용하여 내용 삽입

}

// 데이터 수신 이벤트 핸들러
window.addEventListener("message", function(event) {
    var receivedData = event.data;
    
    // 전달된 데이터 사용
    var postcode = receivedData.postcode;
    var roadAddress = receivedData.roadAddress;
    var jibunAddress = receivedData.jibunAddress;
    var detailAddress = receivedData.detailAddress;
    var extraAddress = receivedData.extraAddress;
    
    // 주소 정보를 부모 HTML의 특정 요소에 표시
    var deliveryDiv = document.querySelector(".go-delivery");
    deliveryDiv.innerHTML = postcode + " " + roadAddress + " " + jibunAddress + " " + detailAddress + " " + extraAddress;
    deliveryDiv.style.marginLeft = "125px";
});
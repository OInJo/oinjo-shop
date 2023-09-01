function address_popup() {
    var popup = window.open('/address/list', '배송지', 'width=600, height=700, left=0, top=0');

    '<p><button onclick="window.close()">닫기</button>';
}

window.addEventListener("message", function(event) {
    var receivedData = event.data;
    
    // 전달된 데이터 사용
    var postcode = receivedData.postcode;
    var roadAddress = receivedData.roadAddress;
    var detailAddress = receivedData.detailAddress;
    
    // 주소 정보를 부모 HTML의 특정 요소에 표시
    var deliveryDiv = document.querySelector(".go-delivery");
    deliveryDiv.innerHTML = "(" + postcode + ")" + " " + roadAddress + " " + detailAddress;
    deliveryDiv.style.marginLeft = "125px";
});

var previousSelectedOption = document.querySelector('.card-check'); // 카드 선택이 처음에 나오도록 설정

function CardSelection(button) {
    // 이전에 선택한 옵션을 숨김
    if (previousSelectedOption !== null) {
        previousSelectedOption.style.display = 'none';
    }

    // 새로운 선택 옵션을 보이도록 설정
    var cardCheck = button.parentNode.querySelector('.card-check');
    cardCheck.style.display = 'block';

    // 이전에 선택한 옵션을 업데이트
    previousSelectedOption = cardCheck;
}

function PaymentMessage(button, type) {
    // 카드 선택 옵션을 숨김
    var cardCheck = document.querySelector('.card-check');
    cardCheck.style.display = 'none';

    // 모든 결제 문구를 감춤
    var paymentMessage1 = document.getElementById('paymentMessage1');
    var paymentMessage2 = document.getElementById('paymentMessage2');
    paymentMessage1.style.display = 'none';
    paymentMessage2.style.display = 'none';

    // 선택된 타입에 따라 해당 문구를 보이도록 설정
    if (type === 1) {
        paymentMessage1.style.display = 'block';
    } else if (type === 2) {
        paymentMessage2.style.display = 'block';
    }
}

function checkAllAgree() {
    const allCheckAgree = document.getElementById("allCheckAgree");
    const checkboxes = document.querySelectorAll(".order-item-checkbox input[type='checkbox']");

    // 전체 동의하기 체크박스를 클릭했을 때, 모든 체크박스를 선택, 해제
    if (allCheckAgree.checked) {
        checkboxes.forEach((checkbox) => {
            checkbox.checked = true;
        });
    } else {
        checkboxes.forEach((checkbox) => {
            checkbox.checked = false;
        });
    }
}

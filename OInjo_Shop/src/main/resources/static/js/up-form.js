var isDefaultAddress = false;
var addressId = "[[${addressDto.addressId}]]";
var memberAddress = "[[${memberDto.memberAddress}]]";

function upAddress() {
    var url = "/address/payment/" + addressId;
    var requestData = {
        addressId: addressId,
        address: document.getElementById("combined-address").value,
    };
    console.log(url);
    $.ajax({
        url: url,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(requestData),
        success: function (response) {
            alert("주소 업데이트가 완료되었습니다.");
        },
        error: function (jqXHR, status, error) {
            alert("주소 업데이트에 실패했습니다.");
        },
    });
}

function checkDefaultAddress(checkbox) {
    isDefaultAddress = checkbox.checked;
    console.log(isDefaultAddress);
}

function submitForm() {
    if (isDefaultAddress && addressId === memberAddress) {
        upAddress();
    }
    // 필요한 다른 작업들 수행
}

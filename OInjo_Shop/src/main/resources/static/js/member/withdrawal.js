// 회원탈퇴 버튼 클릭 시 유저에게 한번 더 물어보는 로직
document.addEventListener("DOMContentLoaded", function () {
  // 회원탈퇴 버튼 클릭 이벤트 리스너 등록
  const withdrawalButton = document.querySelector(".withdrawal-button");
  withdrawalButton.addEventListener("click", handleWithdrawalButtonClick);
});

function handleWithdrawalButtonClick(event) {
  // 확인 팝업 띄우기
  const confirmResult = window.confirm("정말 회원탈퇴 하시겠습니까?");
  
  // 확인을 선택한 경우에만 회원탈퇴 처리 실행
  if (confirmResult) {
    const loginId = document.querySelector(".loginid").textContent;
    const url = "/member/delete/" + loginId; // 회원탈퇴 URL 생성
    window.location.href = url; // 회원탈퇴 URL로 이동
  } else {
    // 취소를 선택한 경우 아무 동작 없음
    alert("회원탈퇴가 취소되었습니다.")
    event.preventDefault();
  }
}

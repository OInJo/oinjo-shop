// const loginCheck = document.querySelector(".login-check");
// loginCheck.style.display = "none";

//처음에 input박스는 수정이 불가능한 readonly 속성이 걸려있음. 하지만 변경 버튼을 클릭 시, readonly가 해제되고 회원의 개인정보를 수정 가능하게 되고 변경완료로 텍스트가 변함
//변경을 모두 진행했으면 변경완료 버튼을 누르고 readonly속성이 다시 추가되며 변경으로 텍스트가 변경됨,
// 휴대폰번호, 이메일 등은 사용자에 의도와 맞지 않게 잘못 변경되어버리면 아이디가 사용할 수 없도록 로그인이 불가능한 상황을 방지하도록 버튼을 추가하고 버튼 클릭시에만 변경되도록 구현
const emailUpdateInput = document.querySelector(".email-update-input");
const emailUpdateButton = document.querySelector(".email-update-button");
emailUpdateButton.addEventListener("click", () => {
  if (emailUpdateInput.readOnly) {
    emailUpdateInput.readOnly = false;
    emailUpdateButton.textContent = "변경완료";
  } else {
    emailUpdateInput.readOnly = true;
    emailUpdateButton.textContent = "변경";
  }
});

const nameUpdateInput = document.querySelector(".name-update-input");
const nameUpdateButton = document.querySelector(".name-update-button");
nameUpdateButton.addEventListener("click", () => {
  if (nameUpdateInput.readOnly) {
    nameUpdateInput.readOnly = false;
    nameUpdateButton.textContent = "변경완료";
  } else {
    nameUpdateInput.readOnly = true;
    nameUpdateButton.textContent = "변경";
  }
});

const nicknameUpdateInput = document.querySelector(".nickname-update-input");
const nicknameUpdateButton = document.querySelector(".nickname-update-button");
nicknameUpdateButton.addEventListener("click", () => {
  if (nicknameUpdateInput.readOnly) {
    nicknameUpdateInput.readOnly = false;
    nicknameUpdateButton.textContent = "변경완료";
  } else {
    nicknameUpdateInput.readOnly = true;
    nicknameUpdateButton.textContent = "변경";
  }
});

const phoneUpdateInput = document.querySelector(".phone-update-input");
const phoneUpdateButton = document.querySelector(".phone-update-button");
phoneUpdateButton.addEventListener("click", () => {
  if (phoneUpdateInput.readOnly) {
    phoneUpdateInput.readOnly = false;
    phoneUpdateButton.textContent = "변경완료";
  } else {
    phoneUpdateInput.readOnly = true;
    phoneUpdateButton.textContent = "변경";
  }
});

const addressUpdateInput = document.querySelector(".address-update-input");
const addressUpdateButton = document.querySelector(".address-update-button");
addressUpdateButton.addEventListener("click", () => {
  if (addressUpdateInput.readOnly) {
    addressUpdateInput.readOnly = false;
    addressUpdateButton.textContent = "변경완료";
  } else {
    addressUpdateInput.readOnly = true;
    addressUpdateButton.textContent = "변경";
  }
});

// 비밀번호 변경 소스
const passwordupdateButton = document.querySelector(".passwordupdate-button");
const passwordupdateWrapper = document.querySelector(".passwordupdate-wrapper");
const oldPassword = document.querySelector(".old-password");
const newPassword = document.querySelector("#new-password");
const reNewPassword = document.querySelector("#re-new-password")
//패스워드 변경 버튼을 클릭하면 새 비밀번호와, 새 비밀번호 변경 값을 모두 지워줌, 처음에는 새 비밀번호 값에 이전 비밀번호를 넣고 페이지를 구성함.
//이렇게 구성한 이유는 새 비밀번호 값에 name을 memberPassword를 주어지고, 사용자가 패스워드를 변경하지않고 휴대폰번호만 변경한다거나, 진행할 시
//비밀번호는 공백으로 변하게 됨. 그런 상황을 방지
passwordupdateButton.addEventListener("click", () => {
  passwordupdateWrapper.classList.toggle("hidden");
  passwordupdateButton.disabled = true;
  newPassword.value = ""
  reNewPassword.value = ""
});

// 비밀번호 변경 버튼을 누르면 새 비밀번호 값을 유지하고, 다음에 변경하기를 누르면 새 비밀번호 값에 예전 비밀번호를 넣어줌.
// 사용자가 새 비밀번호 란에 무언가를 입력 후, 다음에 변경하기를 눌렀을 때도 변경되는 상황 방지
// 현재 문제점은 새 비밀번호 란에 무언가를 입력 후, 비밀번호 변경창을 닫지 않고 아무것도 누르지 않고 그냥 정보수정을 누르면 변경됨.
const passwordupdateSubmitButton = document.querySelector(
  ".passwordupdate-submit-button"
);
const passwordupdateCancelButton = document.querySelector(
  ".passwordupdate-cancel-button"
);

//패스워드 변경 버튼을 누르면 새 비밀번호에 입력한 값이 유지됨
passwordupdateSubmitButton.addEventListener("click", () => {
  passwordupdateButton.disabled = false;
  passwordupdateWrapper.classList.add("hidden");
});

//다음에 변경하기(패스워드 변경 취소)버튼 클릭 시 이전 패스워드 값을 다시 가져와 새 비밀번호 값에 넣어줌.
//사용자가 패스워드 변경을 진행하려다가 다음에 변경하기를 눌렀으면 이전과 동일한 패스워드 값으로 변경되지않아야 하므로. 이렇게 구현
passwordupdateCancelButton.addEventListener("click", () => {
  newPassword.value = oldPassword.value;
  passwordupdateButton.disabled = false;
  passwordupdateWrapper.classList.add("hidden");
});
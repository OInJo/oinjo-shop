// const loginCheck = document.querySelector(".login-check");
// loginCheck.style.display = "none";
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
passwordupdateSubmitButton.addEventListener("click", () => {
  passwordupdateButton.disabled = false;
  passwordupdateWrapper.classList.add("hidden");
});
passwordupdateCancelButton.addEventListener("click", () => {
  newPassword.value = oldPassword.value;
  passwordupdateButton.disabled = false;
  passwordupdateWrapper.classList.add("hidden");
});
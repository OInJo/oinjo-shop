const loginCheck = document.querySelector(".login-check");
loginCheck.style.display = "none";
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

const passwordUpdateInput = document.querySelector(".password-update-input");
const passwordUpdateButton = document.querySelector(".password-update-button");
passwordUpdateButton.addEventListener("click", () => {
  if (passwordUpdateInput.readOnly) {
    passwordUpdateInput.readOnly = false;
    passwordUpdateButton.textContent = "변경완료";
  } else {
    passwordUpdateInput.readOnly = true;
    passwordUpdateButton.textContent = "변경";
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

// 모달창 없애기
const openButton = document.getElementById("open-password");
const modal = document.querySelector(".password-update-modal");
const overlay = modal.querySelector(".modal__overlay");
const closeBtn = modal.querySelector(".save-password");
const openModal = () => {
  modal.classList.remove("hidden");
};
const closeModal = () => {
  modal.classList.add("hidden");
};
overlay.addEventListener("click", closeModal);
closeBtn.addEventListener("click", closeModal);
openButton.addEventListener("click", openModal);

// 비밀번호 일치 불일치
function check_password() {
  if (
    document.getElementById("new-password").value != "" &&
    document.getElementById("renew-password").value != ""
  ) {
    if (
      document.getElementById("new-password").value ==
      document.getElementById("renew-password").value
    ) {
      document.getElementById("check").innerHTML = "일치";
      document.getElementById("check").style.color = "blue";
    } else {
      document.getElementById("check").innerHTML = "불일치";
      document.getElementById("check").style.color = "red";
    }
  }
  if (
    document.getElementById("new-password").value == "" ||
    document.getElementById("renew-password").value == ""
  ) {
    document.getElementById("check").innerHTML = "";
  }
}
//

// 비밀번호 불일치 시 저장 안눌리게
function save() {
  const userPassword = document.querySelector("#user-password");
  const currentpassword = document.querySelector("#current-password");
  const newpassword = document.querySelector("#new-password");
  const renewpassword = document.querySelector("#renew-password");
  const savebtn = document.querySelector("#savebtn");
  if(!(userPassword.value == currentpassword.value)){
    alert("입력한 비밀번호가 회원의 비밀번호가 아닙니다.")
  }
  else if(!(newpassword.value == renewpassword.value)) {
    alert("새 비밀번호가 일치하지 않습니다.")
  }
  else {
    savebtn.disabled = false;
  }
}

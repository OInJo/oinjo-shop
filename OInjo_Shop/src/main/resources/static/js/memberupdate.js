const loginCheck = document.querySelector(".login-check");
loginCheck.style.display = "none";
const emailUpdateInput = document.querySelector(".email-update-input")
const emailUpdateButton = document.querySelector(".email-update-button")
emailUpdateButton.addEventListener("click", ()=>{
  if(emailUpdateInput.readOnly) {
    emailUpdateInput.readOnly = false;
    emailUpdateButton.textContent ="변경완료"
  }
  else {
    emailUpdateInput.readOnly = true;
    emailUpdateButton.textContent ="변경"
  }
})

const nameUpdateInput = document.querySelector(".name-update-input")
const nameUpdateButton = document.querySelector(".name-update-button")
nameUpdateButton.addEventListener("click", ()=>{
  if(nameUpdateInput.readOnly) {
    nameUpdateInput.readOnly = false;
    nameUpdateButton.textContent ="변경완료"
  }
  else {
    nameUpdateInput.readOnly = true;
    nameUpdateButton.textContent ="변경"
  }
})

const nicknameUpdateInput = document.querySelector(".nickname-update-input")
const nicknameUpdateButton = document.querySelector(".nickname-update-button")
nicknameUpdateButton.addEventListener("click", ()=>{
  if(nicknameUpdateInput.readOnly) {
    nicknameUpdateInput.readOnly = false;
    nicknameUpdateButton.textContent ="변경완료"
  }
  else {
    nicknameUpdateInput.readOnly = true;
    nicknameUpdateButton.textContent ="변경"
  }
})

const phoneUpdateInput = document.querySelector(".phone-update-input")
const phoneUpdateButton = document.querySelector(".phone-update-button")
phoneUpdateButton.addEventListener("click", ()=>{
  if(phoneUpdateInput.readOnly) {
    phoneUpdateInput.readOnly = false;
    phoneUpdateButton.textContent ="변경완료"
  }
  else {
    phoneUpdateInput.readOnly = true;
    phoneUpdateButton.textContent ="변경"
  }
})

const addressUpdateInput = document.querySelector(".address-update-input")
const addressUpdateButton = document.querySelector(".address-update-button")
addressUpdateButton.addEventListener("click", ()=>{
  if(addressUpdateInput.readOnly) {
    addressUpdateInput.readOnly = false;
    addressUpdateButton.textContent ="변경완료"
  }
  else {
    addressUpdateInput.readOnly = true;
    addressUpdateButton.textContent ="변경"
  }
})
const phoneNumberInput = document.querySelector(".phone-number-input");
const phoneNumberCertificationErrorMessage = document.querySelector(".phone-number-certification-error-message")
phoneNumberInput.addEventListener("input", (event) => {
  const phoneNumber = event.target.value;
  if (
    /^\d+$/.test(phoneNumber) &&
    phoneNumber.length == 11 &&
    phoneNumber.charAt(0) == "0"
  ) {
    phoneNumberInput.style.border = "";
    phoneNumberCertificationErrorMessage.style.display = "none"
  } else if(phoneNumber.length > 0){
    phoneNumberInput.style.border = "1px solid red";
    phoneNumberCertificationErrorMessage.style.display = "block"
  }
});

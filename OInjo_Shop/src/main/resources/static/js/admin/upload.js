//uploadButton을 클릭했을 때 uploadForm의 submit가 진행되도록 구현
const uploadButton = document.querySelector(".upload-button");
const uploadForm = document.querySelector(".upload-form");
const uploadInputs = document.querySelectorAll(".upload-input");

uploadButton.addEventListener("click", (e) => {
  // 기본적으로 submit을 막고 시작합니다.
  e.preventDefault();

  let allInputsFilled = true;
  uploadInputs.forEach((input) => {
    // 각 input의 값이 비어있는지 확인합니다.
    if (input.value.trim() === "") {
      allInputsFilled = false;
    }
  });

  if (allInputsFilled) {
    // 모든 input이 값이 존재하는 경우에만 submit을 실행합니다.
    uploadForm.submit();
  } else {
    alert("데이터를 입력 후 추가하세요.");
  }
});

// 엔터 키를 눌렀을 때 submit 막기
uploadInputs.forEach((input) => {
  input.addEventListener("keydown", (e) => {
    if (e.keyCode === 13) {
      // 입력된 모든 input의 값이 존재하는지 다시 한 번 확인합니다.
      let allInputsFilled = true;
      uploadInputs.forEach((input) => {
        if (input.value.trim() === "") {
          allInputsFilled = false;
        }
      });

      // 모든 input이 값이 존재하는 경우에만 submit을 실행합니다.
      if (allInputsFilled) {
        uploadForm.submit();
      } else {
        alert("데이터를 입력 후 추가하세요.");
        e.preventDefault();
      }
    }
  });
});


//브랜드의 name을 클릭 시 hidden 되어있는 input에 클릭한 브랜드의 id값을 넣어주는 로직, 함수 변경 리팩토링 필요
const brandIdInput = document.querySelector(".brand-id-input");
const categoryIdInput = document.querySelector(".category-id-input");
const sizeIdInput = document.querySelector(".size-id-input");
const colorIdInput = document.querySelector(".color-id-input");

document.querySelector(".brand-select").addEventListener("change", (e) => {
  brandIdInput.value = e.target.value;
});
document.querySelector(".category-select").addEventListener("change", (e) => {
  categoryIdInput.value = e.target.value;
});
document.querySelector(".size-select").addEventListener("change", (e) => {
  sizeIdInput.value = e.target.value;
});
document.querySelector(".color-select").addEventListener("change", (e) => {
  colorIdInput.value = e.target.value;
});

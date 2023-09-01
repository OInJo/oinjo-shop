document.addEventListener("DOMContentLoaded", function () {
    // 저장 버튼을 눌렀을 때 팝업 창을 닫음
    const saveButton = document.querySelector(".btn-save");
    if (saveButton) {
        saveButton.addEventListener("click", function () {
            window.opener.location.reload();
            window.close();
        });
    }
});
var checkboxes = document.querySelectorAll('.checkbox-input');
      
        function handleCheckboxClick(event) {
          checkboxes.forEach(function (checkbox) {
            if (checkbox !== event.target) {
              checkbox.checked = false;
            }
          });
        }
      
        checkboxes.forEach(function (checkbox) {
          checkbox.addEventListener('click', handleCheckboxClick);
        });
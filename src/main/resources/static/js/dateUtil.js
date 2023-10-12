const datePickerOptions = {
    dateFormat: "yy-mm-dd",
    changeYear: true, //option값 년 선택 가능
    changeMonth: true, //option값  월 선택 가능
    showOtherMonths: true,          // 현재 월에 속하지 않는 날짜도 달력 위젯에 표시하도록 지시
    selectOtherMonths: true,        // 이전 월 및 다음 월의 날짜를 선택할 수 있도록 함
    closeText: "닫기",
    prevText: "이전달",
    nextText: "다음달",
    currentText: "오늘",
    buttonText: "선택", //버튼 호버 텍스트
    yearSuffix: "년", //달력의 년도 부분 뒤 텍스트
    monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
    monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
    dayNames: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesMin : ["일", "월", "화", "수", "목", "금", "토"],
    showMonthAfterYear: true,
    minDate: "-3Y", //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
    maxDate: "+3Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
};

$(document).ready(function() {
    const todayDate = new Date();
    const datePicker = $(".datepicker");
   datePicker.datepicker(Object.assign({}, datePickerOptions, {
       //DatePicker 선택 시 이벤트
       onSelect: (dataString, selectedAttr) => {
            datePicker.text = dataString;
       }
   }))
});


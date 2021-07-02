//length<20
//value < 30
function checkNumbers() {
    var number = document.getElementById("numbers").value;
    if(number>30&&(number+"").length>20){
        alert("please input number valu less than 30 and leng less than 20");
    }
}
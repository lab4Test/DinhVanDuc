function checkNumbers() {
    var number = document.getElementById('numbers').value;
    console.log(number);
    if (isNaN(number)) {
        document.getElementById('numbers').value = '';
        alert('Please Enter A Number!');
    }
    
}

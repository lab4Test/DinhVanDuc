// auto countdown
function countdown(timeRemain){
    setInterval(function () {
        var timeShow = document.getElementById("time");
        var checkEnd = document.getElementById("endTime");
        //timeRemain is ms -> 1 min = 1000ms * 60s
        var minutes = Math.floor(timeRemain/60000);
        //second = 1000ms = min * 60
        var seconds = Math.floor(timeRemain/1000-minutes*60 );
        var ss = "";
        var mm = "";
        //if min < 10 -> display: 0+min otherwise min
        mm = minutes<10?"0"+minutes:minutes+"";
        //if sec < 10 -> display: 0+sec otherwise sec
        ss = seconds<10?"0"+seconds:seconds+"";
        timeShow.textContent = mm+":"+ss;
        
        if(timeRemain<=0){
            checkEnd.value = "1";
            timeShow.textContent = "Time up!";
            document.getElementById("nextBtn").click();
        }
        timeRemain-=1000;
    },1000);
}



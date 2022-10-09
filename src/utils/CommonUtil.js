const CommonUtil = {

    addTimeString: (time1, time2) => {
        var hour = 0
        var minute = 0
        var second = 0

        var splitTime1 = time1.split(':')
        splitTime1 = splitTime1.length === 1 ? ["00", "00", ...splitTime1] : splitTime1.length === 2 ? ["00", ...splitTime1] : splitTime1

        var splitTime2 = time2.split(':')
        splitTime2 = splitTime2.length === 1 ? ["00", "00", ...splitTime2] : splitTime2.length === 2 ? ["00", ...splitTime2] : splitTime2

        hour = parseInt(splitTime1[0]) + parseInt(splitTime2[0])
        minute = parseInt(splitTime1[1]) + parseInt(splitTime2[1])
        hour = hour + parseInt(minute / 60)
        minute = minute % 60
        second = parseInt(splitTime1[2]) + parseInt(splitTime2[2])
        minute = minute + parseInt(second / 60)
        second = second % 60
        
        return (hour < 10 ? "0" + hour : hour) + ':' + (minute < 10 ? "0" + minute : minute) + ':' + (second < 10 ? "0" + second : second) 
    },

    getDateStringFromMilliseconds(milliseconds) {
        const monthString = ["January","February","March","April","May","June","July","August","September","October","November","December"]
        const d = new Date(milliseconds)
        return `${monthString[d.getMonth()]} ${d.getDay()}, ${d.getFullYear()}`
    } 
}

export default CommonUtil
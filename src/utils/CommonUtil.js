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
        return `${monthString[d.getMonth()]} ${d.getDate()}, ${d.getFullYear()}`
    } ,

    capitalizeFirstLetter: string => string && typeof string ? string?.charAt(0)?.toUpperCase() + string?.slice(1) : '',

    // Random số thực [min, max)
    getRandomArbitrary: (min, max) => Math.random() * (max - min) + min,

    // Random số nguyên [min, max) 
    getRandomInt: (min, max) => {
        min = Math.ceil(min)
        max = Math.floor(max)
        return Math.floor(Math.random() * (max - min) + min)
    },

    getRandomIntInclusive: (min, max) => {
        min = Math.ceil(min)
        max = Math.floor(max)
        return Math.floor(Math.random() * (max - min + 1) + min)
    },

    // Random k số không lặp trong một khoảng cho trước (k nguyên)
    // arr là mảng các số không được chứa trong mảng kết quả => Random nhưng trừ các số trong mảng này ra
    // getRandom là chọn 1 trong 3 hàm trên: getRandomArbitrary, getRandomInt, getRandomIntInclusive
    getRandom_K_number_unique: (min, max, k, arr, getRandom) => {
        const result = []
        while(result.length < k) {
            const r = getRandom(min, max)
            if(result.indexOf(r) === -1 && arr.indexOf(r) === -1) result.push(r)
        }
        return result
    },

    shuffle: array => {
        let currentIndex = array.length,  randomIndex
        while (currentIndex-- !== 0) {
            randomIndex = CommonUtil.getRandomInt(0, currentIndex)
            CommonUtil.swapElementArray(array, currentIndex, randomIndex)
        }
        return array
    },

    swapElementArray: (array, index1, index2) => {
        const temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    },
}

export default CommonUtil
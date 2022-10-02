// https://css-tricks.com/building-progress-ring-quickly
// https://tailwindcomponents.com/component/circular-progress-bar
const CircularProgressBar = (props) => {

    const {
        radius = 60,
        strokeWidth = 4,
        percent = 0,
        fill = "transparent",
        classNameSvg = "",
        classNameTextPercent = "",
        styleTextPercent = {},

        color = "black",
        classNameCircle = "",

        classNameCircleDone = "",
        colorCircleDone = "gray"
    } = props

    const circumference = radius * 2 * Math.PI // <=> 100%

    return <div className="overflow-hidden rounded-full relative flex justify-center items-center">
        <svg
            className={`${classNameSvg}`}
            width={radius * 2}
            height={radius * 2}
        >

            <circle
                className={`duration-300 -rotate-90 origin-center ${classNameCircleDone}`}
                stroke={colorCircleDone}
                strokeWidth={strokeWidth}
                fill={fill}
                r={radius - (strokeWidth * 2)}
                cx={radius}
                cy={radius}
            />
            <circle
                className={`duration-300 -rotate-90 origin-center ${classNameCircle}`}
                stroke={color}
                strokeWidth={strokeWidth}
                fill={fill}
                r={radius - (strokeWidth * 2)}
                cx={radius}
                cy={radius}
                strokeDasharray={`${circumference} ${circumference}`} // Tạo ra các dấu gạch có độ dài circumference (px), mỗi dấu gạch cách nhau circumference (px) (Không thể nhỉn thấy được)
                strokeDashoffset={circumference - percent / 100 * circumference} // Kéo dài dấu gạch đó thêm 1 khoảng circumference - percent/100 * circumference (px)
            />
        </svg>
        <span className={`absolute ${classNameTextPercent}`} style={styleTextPercent}>{percent}%</span>
        {/* Phần từ cha là flex, thì phần tử con chỉ cần đặt là absolute là nó tự động căn giữa */}
    </div>
}

export default CircularProgressBar
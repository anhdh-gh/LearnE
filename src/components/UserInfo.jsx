import '../assets/css/UserInfo.css'
import AvatarIcon from '../assets/img/avatar-icon.jpg' 

const UserInfo = (props) => {

    const { user, className, limit, onClick } = props

    return <div className={`user-info-container ${className}`} onClick={() => onClick && onClick()}>
        <img style={{"border": "1px solid #00B871"}} src={user?.avatar || AvatarIcon} alt='avatar' className="avatar"/>
        <div className="text-user-info overflow-hidden">
            <p className="display-name">
                {
                    user?.userName ? 
                        user?.userName?.length > (limit || 15)
                        ? user?.userName.substring(0, (limit || 15)).concat('...')
                        : user?.userName
                    : "Username"
                }
            </p>
            <p className="email">
                {
                    user?.account?.email ? 
                        user?.account?.email?.length > (limit || 15)
                        ? user?.account?.email.substring(0, (limit || 15)).concat('...')
                        : user?.account?.email
                    : "Email"
                }
            </p>
        </div>
    </div>
}

export default UserInfo
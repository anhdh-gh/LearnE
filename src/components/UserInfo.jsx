import '../assets/css/UserInfo.css'
import AvatarIcon from '../assets/img/avatar-icon.jpg' 

const UserInfo = (props) => {

    const { user, className } = props

    return <div className={`user-info-container ${className}`}>
        <img src={user?.avatar || AvatarIcon} alt='avatar' className="avatar"/>
        <div className="text-user-info">
            <p className="display-name">
                {
                    user?.username?.length > 15
                    ? user?.username.substr(0, 15).concat('...')
                    : user?.username
                }
            </p>
            <p className="email">
                {
                    user?.email?.length > 15
                    ? user?.email.substr(0, 15).concat('...')
                    : user?.email
                }
            </p>
        </div>
    </div>
}

export default UserInfo
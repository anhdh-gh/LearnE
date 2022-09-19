import '../assets/css/Auth.css'
import bgLeft from '../assets/img/bg_auth_left.png'
import bgRight from '../assets/img/bg_auth_right.png'
import { useState } from 'react'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'
import { signIn } from '../redux/actions/userSagaAction'
import { useSelector } from "react-redux"
import { useEffect } from 'react'
import { signOut } from '../redux/actions'
import { History } from '../components/NavigateSetter'

const Auth = (props) => {

    const dispatch = useDispatch()
    const [isSignIn, setIsSignIn] = useState(props.isSignIn)
    const [isSignUp, setIsSignUp] = useState(props.isSignUp)
    const isButtonSignInSpin = useSelector(state => state.UI.Auth.signIn.isButtonSignInSpin)

    const [ email, setEmail ] = useState('')
    const [ password, setPassword ] = useState('')
    const [ rememberMe, setRememberMe ] = useState(true)

    useEffect(() => {
        dispatch(signOut())
    }, [dispatch])

    const submitSignIn = (ev) => {
        ev.preventDefault()
        dispatch(signIn(email, password, rememberMe))
    }

    return <div className="wrapper-auth relative">

        <div className="absolute left-0 bottom-0 w-4/12">
            <img className="w-full" src={bgLeft} alt="bg_left"/>
        </div>
        <div className="absolute right-0 bottom-0 w-4/12">
            <img className="w-full" src={bgRight} alt="bg_right"/>
        </div>

        <div className={`container md:block hidden ${isSignUp && !isSignIn && 'right-panel-active'}`} id="container">
            <div className="form-container sign-up-container">
                <form action="/#" onSubmit={ev => ev.preventDefault()}>
                    <h1>Create Account</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input required type="text" placeholder="Username" name='username' />
                    <input required type="email" placeholder="Email" />
                    <input required type="password" placeholder="Password" />
                    <button>Sign Up</button>
                    <div className='flex mt-3.5 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}} className='m-0'>Home</a>
                        <a href={ROUTE_PATH.SIGN_IN} className='m-0' onClick={ev => {ev.preventDefault(); History.replace(ROUTE_PATH.SIGN_IN); setIsSignIn(true); setIsSignUp(false)}}>Sign in</a>
                    </div>
                </form>
            </div>
            <div className="form-container sign-in-container">
                <form action="/#" onSubmit={submitSignIn}>
                    <h1>Sign in</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input required type="email" placeholder="Email" name="email" onChange={e => setEmail(e.target.value)}/>
                    <input required type="password" placeholder="Password" name="password" onChange={e => setPassword(e.target.value)}/>
                    <label className='w-full flex justify-start items-center cursor-pointer'>
                        <input className="w-auto m-0 cursor-pointer" type="checkbox" defaultChecked="checked" name="rememberMe" onChange={e => setRememberMe(e.target.checked)}/>
                        <div className='ms-2'>Remember me</div>
                    </label>
                    <button disabled={isButtonSignInSpin} className={`${isButtonSignInSpin && 'opacity-50'}`}>
                        <i className={`${!isButtonSignInSpin && 'd-none'} fa-solid fa-spinner animate-spin`}></i> Sign In</button>
                    <a href="/#" className='mt-4' onClick={ev => ev.preventDefault()}>Forgot your password?</a>
                    <div className='flex mt-2 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} className='m-0' onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}}>Home</a>
                        <a href={ROUTE_PATH.SIGN_UP} className='m-0' onClick={ev => {ev.preventDefault(); History.replace(ROUTE_PATH.SIGN_UP); setIsSignIn(false); setIsSignUp(true)}}>Sign up</a>
                    </div>
                </form>
            </div>
            <div className="overlay-container d-md-block">
                <div className="overlay">
                    <div className="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button className="ghost" id="signIn" onClick={() => { setIsSignUp(false); setIsSignIn(true); History.replace(ROUTE_PATH.SIGN_IN)}}>Sign In</button>
                    </div>
                    <div className="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button className="ghost" id="signUp" onClick={() => { setIsSignUp(true); setIsSignIn(false); History.replace(ROUTE_PATH.SIGN_UP)}}>Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

        <div className="container max-w-xs md:hidden" id="container">
            <div className={`form-container w-100 sign-up-container ${!isSignUp && 'd-none'} ${isSignUp && 'opacity-100'}`}>
                <form action="/#" onSubmit={ev => ev.preventDefault()} className="px-4">
                    <h1>Create Account</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input required type="text" placeholder="Username" name='username'/>
                    <input required type="email" placeholder="Email" />
                    <input required type="password" placeholder="Password" />
                    <button className='mt-3'>Sign Up</button>
                    <div className='flex mt-3.5 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}} className='m-0'>Home</a>
                        <a href={ROUTE_PATH.SIGN_IN} className='m-0' onClick={ev => {ev.preventDefault(); History.replace(ROUTE_PATH.SIGN_IN); setIsSignIn(true); setIsSignUp(false)}}>Sign in</a>
                    </div>
                </form>
            </div>
            <div className={`form-container w-100 sign-in-container ${!isSignIn && 'd-none'} ${isSignIn && 'opacity-100'}`}>
                <form action="/#" onSubmit={submitSignIn} className="px-4">
                    <h1 className='mt-3.5'>Sign in</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input required type="email" placeholder="Email" name="email" onChange={e => setEmail(e.target.value)}/>
                    <input required type="password" placeholder="Password" name="password" onChange={e => setPassword(e.target.value)}/>
                    <label className='w-full flex justify-start items-center cursor-pointer'>
                        <input className="w-auto m-0 cursor-pointer" type="checkbox" defaultChecked="checked" name="rememberMe" onChange={e => setRememberMe(e.target.checked)}/>
                        <div className='ms-2'>Remember me</div>
                    </label>
                    <button disabled={isButtonSignInSpin} className={`${isButtonSignInSpin && 'opacity-50'}`}>
                        <i className={`${!isButtonSignInSpin && 'd-none'} fa-solid fa-spinner animate-spin`}></i> Sign In</button>
                    <a href="/#" className='mt-4' onClick={ev => ev.preventDefault()}>Forgot your password?</a>
                    <div className='flex mt-2 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}} className='m-0'>Home</a>
                        <a href={ROUTE_PATH.SIGN_UP} className='m-0' onClick={ev => {ev.preventDefault(); History.replace(ROUTE_PATH.SIGN_UP); setIsSignIn(false); setIsSignUp(true)}}>Sign up</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
}

export default Auth
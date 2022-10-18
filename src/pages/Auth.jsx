import '../assets/css/Auth.css'
import bgLeft from '../assets/img/bg_auth_left.png'
import bgRight from '../assets/img/bg_auth_right.png'
import { useState } from 'react'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'
import { useSelector } from "react-redux"
import { useEffect, useCallback } from 'react'
import { 
    signOut, signIn, signUp, showAuthSignInIsPageSignIn,
    hideAuthSignUpIsPageSignUp, showAuthSignUpIsPageSignUp, hideAuthSignInIsPageSignIn
} from '../redux/actions'
import { History } from '../components/NavigateSetter'

const Auth = (props) => {

    const dispatch = useDispatch()
    const { isSignIn, isSignUp } = props
    const isButtonSignInSpin = useSelector(state => state.UI.Auth.signIn.isButtonSignInSpin)
    const isButtonSignUpSpin = useSelector(state => state.UI.Auth.signUp.isButtonSignUpSpin)
    const isPageSignIn = useSelector(state => state.UI.Auth.signIn.isPageSignIn)
    const isPageSignUp = useSelector(state => state.UI.Auth.signUp.isPageSignUp)
    const [ email, setEmail ] = useState('')
    const [ password, setPassword ] = useState('')
    const [ rememberMe, setRememberMe ] = useState(true)
    const [ username, setUserName] = useState('')

    const showPageSignIn = useCallback(() => {
        History.replace(ROUTE_PATH.SIGN_IN)
        dispatch(showAuthSignInIsPageSignIn())
        dispatch(hideAuthSignUpIsPageSignUp())
    }, [ dispatch ])

    const showPageSignUp = useCallback(() => {
        History.replace(ROUTE_PATH.SIGN_UP)
        dispatch(showAuthSignUpIsPageSignUp())
        dispatch(hideAuthSignInIsPageSignIn())
    }, [ dispatch ])
    
    useEffect(() => {
        dispatch(signOut())
        if(isSignIn) {
            showPageSignIn()
        } else if(isSignUp) {
            showPageSignUp()
        }

        return () => {
            dispatch(showAuthSignInIsPageSignIn())
            dispatch(hideAuthSignUpIsPageSignUp())
        }
    }, [ dispatch, isSignIn, isSignUp, showPageSignIn, showPageSignUp ])

    useEffect(() => {
        if(isPageSignIn) {
            showPageSignIn()
        } else if(isPageSignUp) {
            showPageSignUp()
        }
    }, [ isPageSignIn, isPageSignUp, showPageSignIn, showPageSignUp ])

    const submitSignIn = (ev) => {
        ev.preventDefault()
        dispatch(signIn(email, password, rememberMe))
    }

    const submitSignUp = (ev) => {
        ev.preventDefault()
        dispatch(signUp(email, password, username))
    }

    return <div className="wrapper-auth relative">

        <div className="absolute left-0 bottom-0 w-4/12">
            <img className="w-full" src={bgLeft} alt="bg_left"/>
        </div>
        <div className="absolute right-0 bottom-0 w-4/12">
            <img className="w-full" src={bgRight} alt="bg_right"/>
        </div>

        <div className={`container md:block hidden ${isPageSignUp && !isPageSignIn && 'right-panel-active'}`} id="container">
            <div className="form-container sign-up-container">
                <form action="/#" onSubmit={submitSignUp}>
                    <h1>Create Account</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input required type="text" placeholder="Username" name='username' onChange={e => setUserName(e.target.value)}/>
                    <input required type="email" placeholder="Email" name='email' onChange={e => setEmail(e.target.value)}/>
                    <input required type="password" placeholder="Password" name='password'onChange={e => setPassword(e.target.value)} />
                    <button disabled={isButtonSignUpSpin} className={`${isButtonSignUpSpin && 'opacity-50'}`}>
                        <i className={`${!isButtonSignUpSpin && 'd-none'} fa-solid fa-spinner animate-spin`}></i> Sign up</button>
                    <div className='flex mt-3.5 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}} className='m-0'>Home</a>
                        <a href={ROUTE_PATH.SIGN_IN} className='m-0' onClick={ev => {ev.preventDefault(); showPageSignIn()}}>Sign in</a>
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
                        <a href={ROUTE_PATH.SIGN_UP} className='m-0' onClick={ev => {ev.preventDefault(); showPageSignUp()}}>Sign up</a>
                    </div>
                </form>
            </div>
            <div className="overlay-container d-md-block">
                <div className="overlay">
                    <div className="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button className="ghost" id="signIn" onClick={showPageSignIn}>Sign In</button>
                    </div>
                    <div className="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button className="ghost" id="signUp" onClick={showPageSignUp}>Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

        <div className="container max-w-xs md:hidden static" id="container">
            <div className={`form-container w-100 sign-up-container ${!isPageSignUp && 'd-none'} ${isPageSignUp && 'opacity-100'}`}>
                <form action="/#" onSubmit={submitSignUp} className="px-4">
                    <h1>Create Account</h1>
                    <div className="social-container">
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-900 hover:text-white border-solid border-2 border-blue-900 text-blue-900"><i className="fab fa-facebook-f"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-red-900 hover:text-white border-solid border-2 border-red-900 text-red-900"><i className="fab fa-google-plus-g"></i></a>
                        <a href="/#" onClick={ev => ev.preventDefault()} className="social hover:bg-blue-500 hover:text-white border-solid border-2 border-blue-500 text-blue-500"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input required type="text" placeholder="Username" name='username' onChange={e => setUserName(e.target.value)}/>
                    <input required type="email" placeholder="Email" name='email' onChange={e => setEmail(e.target.value)}/>
                    <input required type="password" placeholder="Password" name='password'onChange={e => setPassword(e.target.value)} />
                    <button disabled={isButtonSignUpSpin} className={`${isButtonSignUpSpin && 'opacity-50'}`}>
                        <i className={`${!isButtonSignUpSpin && 'd-none'} fa-solid fa-spinner animate-spin`}></i> Sign up</button>
                    <div className='flex mt-3.5 w-full justify-between'>
                        <a href={ROUTE_PATH.HOME} onClick={ev => {ev.preventDefault(); History.push(ROUTE_PATH.HOME)}} className='m-0'>Home</a>
                        <a href={ROUTE_PATH.SIGN_IN} className='m-0' onClick={ev => {ev.preventDefault(); showPageSignIn()}}>Sign in</a>
                    </div>
                </form>
            </div>
            <div className={`form-container w-100 sign-in-container ${!isPageSignIn && 'd-none'} ${isPageSignIn && 'opacity-100'}`}>
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
                        <a href={ROUTE_PATH.SIGN_UP} className='m-0' onClick={ev => {ev.preventDefault(); showPageSignUp()}}>Sign up</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
}

export default Auth
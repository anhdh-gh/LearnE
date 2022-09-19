import { useNavigate } from 'react-router-dom'

export const History = {
    navigate: null,
    push: (path, ...rest) => History.navigate(path, ...rest),
    replace: (path, ...rest) => History.navigate(path, {replace: true}, ...rest),
    goBack: (path, ...rest) => History.navigate(-1),
}


const NavigateSetter = () => {
  History.navigate = useNavigate()
  return null
}

export default NavigateSetter
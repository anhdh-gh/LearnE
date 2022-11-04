import React from 'react'
import ReactDOM from 'react-dom/client'
import './assets/css/index.css'
import App from './App'
import reportWebVitals from './reportWebVitals'
import { Provider } from "react-redux"
import store from "./redux/store.js"
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ProSidebarProvider } from 'react-pro-sidebar'

const root = ReactDOM.createRoot(document.getElementById('root'))

const queryClient = new QueryClient()

root.render(
  <Provider store={store}>
    <QueryClientProvider client={queryClient}>
      <ProSidebarProvider>
        <App />
      </ProSidebarProvider>
    </QueryClientProvider>
  </Provider>
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()

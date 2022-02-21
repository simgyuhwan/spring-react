import React from 'react';
import {Route} from "react-router-dom";
import Home from "./components/Home";
import HomePage from './pages/HomePage';
import logo from './logo.svg';
import './App.css';
import SignInPage from './components/auth/SignInPage';
import AdminSetupPage from './components/member/AdminSetupPage';

function App() {
  return (
    <>
    <Route component={HomePage} path="/" exact />
    <Route component={SignInPage} path="/signin" exact/>
    <Route component={AdminSetupPage} path="/member/setup" />
    </>
  );
}

// 최상단에 인터페이스를 지정한다. 
export interface LoginInput{
  userId:string;
  password:string;
}
export default App;

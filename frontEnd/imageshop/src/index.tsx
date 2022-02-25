import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";

// 쿠키 관련 모듈 및 라이브러리
import Cookies from 'js-cookie';
import { setAccessToken, checkMyInfo } from './modules/auth';
import client from './lib/client';

// 리덕스와 미들웨어 관련 모듈 임포트
import {createStore,applyMiddleware} from "redux";
import {Provider} from "react-redux";
import rootReducer, {rootSaga} from "./modules";
import {composeWithDevTools} from "redux-devtools-extension";
import createSagaMiddleware from "redux-saga";
import { displayPartsToString } from 'typescript';

// 리덕스 사가 미들웨어 생성
const sagaMiddleware = createSagaMiddleware();

// 스토어 생성
const store = createStore(
  rootReducer, composeWithDevTools(applyMiddleware(sagaMiddleware))
);
function loadUser(){
  try{
    const saveToken = Cookies.get("accessToken");
    if(!saveToken) return;

    store.dispatch(setAccessToken(saveToken));
    client.defaults.headers.common.Authorization = `Bearer ${saveToken}`;

    store.dispatch(checkMyInfo());
  }catch(e){
    console.log(e);
  }
}
// 리덕스 사가 미들웨어 실행
sagaMiddleware.run(rootSaga);

// 로그인 처리함 수 실행
loadUser();

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

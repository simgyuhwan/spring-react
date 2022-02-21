import { createAction } from "redux-actions";
import { createReducer } from "typesafe-actions";
import {takeLatest, call, put} from "redux-saga/effects"
import * as api from "../lib/api";
import client from "../lib/client";
import { AxiosResponse } from "axios";
import { LoginInput } from "../App";

// 액션 타입 
const SET_ACCESS_TOKEN = "auth/SET_ACCESS_TOKEN";
const LOGIN = "auth/LOGIN";

// 액션 생성 함수
export const setAccessToken = createAction(SET_ACCESS_TOKEN, (accessToekn:string)=> accessToekn);
export const login = createAction(LOGIN, ({userId, password}: LoginInput)=>({userId, password}));

// 비동기 액션을 수행하는 태스크 작성
function * loginSaga(action: ReturnType<typeof login>){
    try{
        // 비동기 상태로 login 액션이 실행되기를 기다리며, 들어오면 login으로 들어온 값을 할당한다.
        const{userId, password} = action.payload;
        // 서버에 userId, password 값을 api를 통해서 전달하고 response에 값을 받아온다.
        const response: AxiosResponse = yield call(api.signIn,userId,password);
        // 응답받은 헤더값에 JWT 토큰이 기록되어 있다. 이를 authorization 에 넣는다.
        const {authorization} = response.headers;
        // authorization 을 보면 Bearer {JWT} 값으로 되어 있으니 앞에 Bearer 값을 제외하고 accessToken에 넣어둔다.
        const accessToken = authorization.substring(7);
        // 액션을 사용해서 AuthState에 있는 accessToken 값에 넣어준다.
        yield put(setAccessToken(accessToken));

        // axios의 기본 헤더 값에 토큰을 넣어준다. 이제 기본 요청시 JWT를 포함해서 보내게 된다. 
        client.defaults.headers.common.authorization = `Bearer ${accessToken}`;
    }catch(e){
        console.log(e);
    }
}

// 로그인 사가 함수 작성
export function * authSaga(){
    yield takeLatest(LOGIN, loginSaga);
}

export interface AuthState{
    accessToken:string;
}

const initialState: AuthState = {
    accessToken: "",
};

// 리듀서 함수 정의 
const auth = createReducer(
    initialState,
    {
        [SET_ACCESS_TOKEN]: (state, action)=>({
            ...state,
            accessToken: action.payload,
        }),
    },
);

export default auth;
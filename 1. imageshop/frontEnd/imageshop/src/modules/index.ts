import { combineReducers } from "redux";
import {all} from "redux-saga/effects";
// Auth 관련 import
import auth,{authSaga} from "./auth";
import { AuthState } from "./auth";
import { CodeGroupState } from "./coregroup";
import { LoadingState } from "./loading";
import codeGroup,{codeGroupSaga}  from "./coregroup";

// 루트 상태 인터페이스 정의
export interface RootState{
    auth: AuthState;
    codegroup : CodeGroupState;
    loading: LoadingState;
}

// 루트 리듀서
const rootReducer = combineReducers({
    auth,
    loading,
    codegroup,
});

// 루트 사가
export function * rootSaga(){
    yield all([
        authSaga(),
        codeGroupSaga(),
    ]);
}

export default rootReducer;
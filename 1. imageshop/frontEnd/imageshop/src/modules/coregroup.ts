import {createAction} from "redux-actions";
import { createReducer } from "typesafe-actions";
import { takeLatest } from "redux-saga/effects";
import * as api from "../lib/api";
import createRequestSaga from "../lib/createRequestSaga";
import { CodeGroup } from "../App";

// 액션 타입
export const FETCH_ONE = "codeGroup/FETCH_ONE";
const FETCH_ONE_SUCCESS = "codeGroup/FETCH_ONE_SUCCESS";
const FETCH_ONE_FAILURE = "codeGroup/FETCH_ONE_FAILURE";

export const FETCH_LIST = "codeGroup/FETCH_LIST";
const FETCH_LIST_SUCCESS = "codeGroup/FETCH_LIST_SUCCESS";
const FETCH_LIST_FAILURE = "codeGroup/FETCH_LIST_FAILURE";

// 액션 생성 함수
export const fetchOne = createAction(FETCH_ONE, (groupCode:string)=> groupCode);
export const fetchList = createAction(FETCH_LIST);

// 비동기 액션을 수행하는 테스크 작성
const fetchOneSaga = createRequestSaga(FETCH_ONE, api.fetchCodeGroup);
const fetchListSaga = createRequestSaga(FETCH_LIST, api.fetchCodeGroupList);

// 코드그룹 사가 함수
export function * codeGroupSaga(){
    yield takeLatest(FETCH_ONE, fetchOneSaga);
    yield takeLatest(FETCH_LIST, fetchListSaga);
}

// 상태 인터페이스 정의
export interface CodeGroupState{
    codeGroup : CodeGroup | null;
    codeGroups : CodeGroup[];
    error : any;
}

// 초기 상태
const initialState: CodeGroupState = {
    codeGroup: null,
    codeGroups:[],
    error:null,
}

// 리듀서 함수 정의
const codeGroup = createReducer(
    initialState,
    {
        [FETCH_ONE] : (state) =>({
            ...state,
            codeGroup:null,
        }),
        [FETCH_ONE_SUCCESS]: (state, action) =>({
            ...state,
            codeGroup: action.payload,
        }),
        [FETCH_ONE_FAILURE]: (state, action) =>({
            ...state,
            error: action.payload,
        }),
        [FETCH_LIST] : (state) =>({
            ...state,
            codeGroups: [],
        }),
        [FETCH_LIST_SUCCESS]: (state, action) =>({
            ...state,
            codeGroups: action.payload,
        }),
        [FETCH_LIST_FAILURE]: (state, action) =>({
            ...state,
            error: action.payload,
        }),
    },
);

export default codeGroup;

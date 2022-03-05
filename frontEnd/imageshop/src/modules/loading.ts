import { createAction } from "redux-actions";
import { createReducer } from "typesafe-actions";

// 액션 타입
const START_LOADING = "loading/START_LOADING";
const END_LOADING = "loading/END_LOADING";

// 액션 생성 함수
export const startLoading = createAction(
    START_LOADING, (actionType:string) => actionType
);

export const endLoading = createAction(
    END_LOADING, (actionType: string) => actionType
);

// 상태 인터페이스 정의
export interface LoadingState{
    [key:string]: boolean;
}

// 초기 상태
const initialState: LoadingState ={};

// 리듀서 함수 정의
const loading = createReducer(
    initialState,
    {
        [START_LOADING]: (state, action)=> ({
            ...state,
            [action.payload] : true,
        }),
        [END_LOADING]: (state, action) =>({
            ...state,
            [action.payload]: false,
        }),
    },
);

export default loading;
import { createAction} from "redux-actions";
import {call, put} from "redux-saga/effects";
import {startLoading, endLoading} from "../modules/loading";
import { AxiosResponse } from "axios";


// 요청에 대한 타입과 api를 받고 바로 그에 맞는 액션과 액션타입을 생성하여 api를 실행하는 함수
export default function createRequestSaga(type:string, request:any){
    const CALL_SUCCESS = `${type}_SUCCESS`;
    const CALL_FAILURE = `${type}_FAILURE`;

    const callSuccess = createAction(CALL_SUCCESS, (data:string)=> data);
    const callFailure = createAction(CALL_FAILURE, (err:any)=>err);

    return function* (action : any){
        yield put(startLoading(type));
        try{
            const response : AxiosResponse = yield call(request, action.payload);
            yield put(callSuccess(response.data));
        }catch (e){
            yield put(callFailure(e));
        }
        yield put(endLoading(type));
    };
}




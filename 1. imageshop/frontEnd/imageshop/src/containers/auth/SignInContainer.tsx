import React, {useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import SignInForm from "../../components/auth/SignInForm";

// Router 추가
import { withRouter, RouteComponentProps } from "react-router-dom";
// login 은 액션 함수
import { login, checkMyInfo } from "../../modules/auth";
// RootState 지금은 accessToken을 가져오기 위해 선언
import { RootState } from "../../modules";

const SignInContainer = ({history}: RouteComponentProps)=>{

    // 스토어 dispatch 사용 가능 (스토어에 있는 Saga를 사용할 수 있게 해준당. 그게 아니더라도 리듀서 사용 가능)
    const dispatch = useDispatch();

    // 스토어 상태 조회
    const {accessToken, myInfo} = useSelector(({auth}:RootState)=>({
        accessToken : auth.accessToken,
        myInfo : auth.myInfo
    }));

    // 로그인 처리
    const onSignIn = (userId:string, password:string) =>{
        try{
            // 이제 dispatch를 통해서 사가에 있는 액션을 실행할 수 있다. 매개변수는 액션함수를 넣었다. 
            dispatch(login({userId, password}));
        }catch (e){
            console.log(e);
        }
    };
    
    useEffect(()=>{
        if(accessToken){
            dispatch(checkMyInfo());
        }
    },[accessToken, dispatch]);


    // 마운트 될 때 엑세스 토큰 상태 정보 확인
    useEffect(()=>{
        // 만약 토큰 값이 있다면 / 페이지로 이동한다.
        if(myInfo){
            alert("로그인 되었습니다.");
            history.push("/");
        }
    },[myInfo, history]);

    return <SignInForm onSignIn={onSignIn}/>;
};

export default withRouter(SignInContainer);

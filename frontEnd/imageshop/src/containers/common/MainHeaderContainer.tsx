import React from "react";
import MainHeader from "../../components/common/MainHeader";
import { getAuthorized } from "../../modules/selector";
import { MyInfo } from "../../App";
import { RootState } from "../../modules";
// 로그아웃
import {connect, useDispatch} from "react-redux";
import {setAccessToken, setMyInfo} from "../../modules/auth";
import client from "../../lib/client";
import Cookie from "js-cookie";

interface Props {
    readonly isAuthorized: boolean;
    readonly myInfo:MyInfo | null;
}

const MainHeaderContainer = ({isAuthorized, myInfo}: Props) =>{
    const dispatch = useDispatch();

    const onLogout = () =>{
        delete client.defaults.headers.common.Authorization;
        Cookie.remove("accessToken");
    
        dispatch(setAccessToken(""));
        dispatch(setMyInfo(null));
    };

    return (
        <MainHeader
            myInfo={myInfo}
            isAuthorized={isAuthorized}
            onLogout= {onLogout}
        />
    );
};

// 스토어 상태를 가공한 정보를 컴포넌트 속성으로 전달
export default connect((state: RootState)=>{
    return {
        isAuthorized: getAuthorized(state),
        myInfo: state.auth.myInfo,
    };
})(MainHeaderContainer);


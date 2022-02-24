import React from "react";
import { connect } from "react-redux";
import MainHeader from "../../components/common/MainHeader";
import { getAuthorized } from "../../modules/selector";
import { MyInfo } from "../../App";
import { RootState } from "../../modules";

interface Props {
    readonly isAuthorized: boolean;
    readonly myInfo:MyInfo | null;
}

const MainHeaderContainer = ({isAuthorized, myInfo}: Props) =>{
    return (
        <MainHeader
            myInfo={myInfo}
            isAuthorized={isAuthorized}
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


import React from "react";
import {Link} from "react-router-dom";
import { MyInfo } from "../../App";

interface Props{
    readonly myInfo: MyInfo | null;
    readonly isAuthorized: boolean;
    readonly onLogout: () => void;
}

function MainHeader({myInfo, isAuthorized, onLogout}: Props){
    return (
        <div>
            {isAuthorized && myInfo && (
                <div>
                    <span>{myInfo.userName}님 환영합니다.</span>
                    <button onClick={onLogout}>로그아웃</button>
                </div>
            )}
            {!isAuthorized && ! myInfo && <Link to="/signin">로그인</Link>}
        </div>
    );
}

export default MainHeader;
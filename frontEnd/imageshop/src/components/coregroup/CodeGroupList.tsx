import React from "react";
import { Link } from "react-router-dom";
import { CodeGroup } from "../../App";

interface Props{

}

// 부모 컴포넌트에서 컴포넌트 속성으로 수신
function CodeGroupList({codeGroups, isLoading}: Props){
    // 코드 그룹 목록 화면 표시
    return (
        <div>
            <h2>코드그룹 목록</h2>
            {isLoading && "로딩중"}
            {!isLoading && codeGroups && (

            )}
        </div>
    );
}

export default CodeGroupList;
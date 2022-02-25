import React from "react";
import {Link} from "react-router-dom";
import {CodeGroup} from "../../App";

interface Props{

}

// 부모 컴포넌트에서 컴포넌트 속성으로 수신
function CodeGroupRead({
    codeGroup,
    isLoading,
    groupCode,
    onRemove
}:Props){

    // 코드그룹 상세보기 화면 표시
    return (
        <div>
            <h2>코드그룹 상세보기</h2>
            {isLoading && "로딩중.."}
            {!isLoading && codeGroup &&(

            )}
        </div>
    );
}

export default CodeGroupRead;
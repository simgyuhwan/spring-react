import React from "react";
import {Link} from "react-router-dom";
import {CodeGroup} from "../../App";

interface Props{
    readonly codeGroup:CodeGroup | null;
    readonly isLoading:boolean;
    readonly groupCode:string;
    readonly onRemove: () =>void;
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
                <>
                    <table>
                        <tbody>
                            <tr>
                                <td>코드그룹코드</td>
                                <td>
                                    <input type="text" value={codeGroup.groupCode} readOnly/>
                                </td>
                            </tr>
                            <tr>
                                <td>코드그룹명</td>
                                <td>
                                    <input type="text" value={codeGroup.groupName} readOnly/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div>
                        <Link to={`/codegroup/edit/${groupCode}`}>편집</Link>
                        <button onClick={onRemove}>삭제</button>
                        <Link to="/codeGroup">목록</Link>
                    </div>
                </>
            )}
        </div>
    );
}

export default CodeGroupRead;
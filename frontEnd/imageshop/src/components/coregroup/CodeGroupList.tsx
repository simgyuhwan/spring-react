import React from "react";
import { Link } from "react-router-dom";
import { CodeGroup } from "../../App";

interface Props{
    readonly codeGroups: CodeGroup[];
    readonly isLoading: boolean;
}

// 부모 컴포넌트에서 컴포넌트 속성으로 수신
function CodeGroupList({codeGroups, isLoading}: Props){
    // 코드 그룹 목록 화면 표시
    return (
        <div>
            <h2>코드그룹 목록</h2>
            {isLoading && "로딩중"}
            {!isLoading && codeGroups && (
                <>
                    <Link to="/codegroup/create">새로만들기</Link>
                    <table>
                        <thead>
                            <tr>
                                <th align="center" >코드그룹코드</th>
                                <th align="center" >코드그룹명</th>
                                <th align="center" >등록일시</th>
                            </tr>
                        </thead>
                        <tbody>
                            {!codeGroups.length && (
                                <tr>
                                    <td colSpan={3}>
                                        List is empty.
                                    </td>
                                </tr>
                            )}
                            {!!codeGroups.length && codeGroups.map((codeGroup)=>(
                                <tr key={codeGroup.groupCode}>
                                    <td align="center">{codeGroup.groupCode}</td>
                                    <td align="left">
                                        <Link to={`/codegroup/read/${codeGroup.groupCode}`}>
                                            {codeGroup.groupName}
                                        </Link>
                                    </td>
                                    <td align="center">{codeGroup.regDate}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </>
            )}
        </div>
    );
}

export default CodeGroupList;
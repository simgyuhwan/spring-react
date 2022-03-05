import React, {useState, useCallback} from "react";
import { Link } from "react-router-dom"; 

interface Props{
    readonly onRegister: (groupCode:string, groupName:string)=>void;
}

//등록 처리 함수를 컴포넌트 속성으로 전달받음
function CodeGroupRegisterForm({onRegister}: Props){
    // 컴포넌트 상태 설정
    const [groupCode, setGroupCode] = useState("");
    const [groupName, setGroupName] = useState("");

    // 그룹코드 값의 변경을 처리하는 함수
    const handleChangeGroupCode = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
            setGroupCode(e.target.value);
    },[]);

    // 코드그룹명 값의 변경을 처리하는 함수
    const handleChangeGroupName = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setGroupName(e.target.value);
    },[]);

    // 폼 submit 이벤트 처리
    const handleSubmit = useCallback(
        (e:React.FormEvent<HTMLFormElement>) =>{
            e.preventDefault();
            onRegister(groupCode, groupName);
        },[groupCode,groupName,onRegister]
    );
    
    return (
        <div>
            <h2>코드그룹 등록</h2>

            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                        <tr>
                            <td>그룹코드</td>
                            <td>
                                <input type="text" value={groupCode} onChange={handleChangeGroupCode} />
                            </td>
                        </tr>
                        <tr>
                            <td>그룹코드명</td>
                            <td>
                                <input type="text" value={groupName} onChange={handleChangeGroupName}/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div>
                    <button type="submit">등록</button>
                    <Link to="/codegroup">취소</Link>
                </div>
            </form>
        </div>
    );
}

export default CodeGroupRegisterForm;
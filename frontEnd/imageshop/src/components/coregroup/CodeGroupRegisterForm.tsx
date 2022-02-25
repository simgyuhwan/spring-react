import React, {useState, useCallback} from "react";
import { Link } from "react-router-dom"; 

interface Props{

}

//등록 처리 함수를 컴포넌트 속성으로 전달받음
function CodeGroupRegisterForm({onRegister}: Props){
    // 컴포넌트 상태 설정
    const [groupCode, setGroupCode] = useState("");
    const [groupName, setGroupName] = useState("");

    // 그룹코드 값의 변경을 처리하는 함수
    const handleChangeGroupCode = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{

    },[]);

    // 코드그룹명 값의 변경을 처리하는 함수
    const handleChangeGroupName = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{

    },[]);

    // 폼 submit 이벤트 처리
    const handleSubmit = useCallback(

    );
    
    return (
        <div>
            <h2>코드그룹 등록</h2>
        </div>
    );
}

export default CodeGroupRegisterForm;
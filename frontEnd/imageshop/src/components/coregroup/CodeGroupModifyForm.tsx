import React, {useState, useEffect} from "react";
import { Link } from "react-router-dom";
import {CodeGroup} from "../../App";

interface Props{

}

function CodeGroupModifyForm({
    codeGroup, isLoading, onModify,
}: Props){
    // 컴포넌트 상태 설정
    const [groupName, setGroupName] = useState("");

    // 코드그룹명 값의 변경을 처리하는 함수
    const handleChangeGroupName = (e:React.ChangeEvent<HTMLInputElement>)=>{
        setGroupName(e.target.value);
    };

    // 폼 submit 이벤트 처리
    const handleSubmit = (e:React.FormEvent<HTMLFormElement>)=>{
        e.preventDefault();


    };

    // 마운트 될 때 기존 코드그룹명을 가져옴
    useEffect(()=>{

    },[codeGroup]);

    // 코드그룹 수정 폼 화면 표시
    return(
        <div>
            <h2>코드그룹 수정</h2>
            {isLoading && "로딩중"}
            {!isLoading && codeGroup &&(

            )}
        </div>
    );
}

export default CodeGroupModifyForm;
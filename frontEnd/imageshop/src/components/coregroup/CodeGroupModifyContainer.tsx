import React, {useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import CodeGroupModifyForm from "./CodeGroupModifyForm";
import * as api from "../../lib/api";
import { fetchOne, FETCH_ONE } from "../../modules/coregroup";
import { useHistory } from "react-router-dom";
import { RootState } from "../../modules";

// props 타입스크립트 인터페이스 정의
interface Props{

}

// 속성값으로 groupCode와 history 를 전달받음
const CodeGroupModifyContainer = ({groupCode}: Props)=>{

    const dispatch = useDispatch();
    const history = useHistory();

    const {codeGroup, isLoading} = useSelector(({codegroup, loading}: RootState)=>({

    }));

    // 수정 처리
    const onModify = async (groupCode:string, groupName:string)=>{

    };

    useEffect(() =>{

    }, [dispatch,groupCode]);

    return (
        <CodeGroupModifyForm
            codeGroup = {codeGroup}
            isLoading = {isLoading}
            onModify = {onModify}
        />
    );
};

export default CodeGroupModifyContainer;
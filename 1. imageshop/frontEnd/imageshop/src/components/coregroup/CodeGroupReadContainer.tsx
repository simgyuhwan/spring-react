import React,{useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import CodeGroupRead from "./CodeGroupRead";
import { fetchOne, FETCH_ONE } from "../../modules/coregroup";
import * as api from "../../lib/api";
import { useHistory } from "react-router-dom";
import {RootState} from "../../modules";

interface Props{
    readonly groupCode:string;
}

const CodeGroupReadContainer = ({groupCode}: Props) =>{

    // 스토어 dispatch
    const dispatch = useDispatch();
    const history = useHistory();

    // 스토어 상태 조회
    const {codeGroup, isLoading} = useSelector(({codegroup, loading}: RootState)=>({
        codeGroup:codegroup.codeGroup,
        isLoading:loading[FETCH_ONE],
    }));

    useEffect(()=>{
        dispatch(fetchOne(groupCode));
    }, [dispatch,groupCode]);

    // 삭제 처리
    const onRemove = async () =>{

    };

    return (
        <CodeGroupRead
            codeGroup={codeGroup}
            isLoading={isLoading}
            groupCode={groupCode}
            onRemove={onRemove}
        />
    );    
};

export default CodeGroupReadContainer;

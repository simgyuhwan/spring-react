import React,{useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import CodeGroupList from "./CodeGroupList";
import { fetchList, FETCH_LIST } from "../../modules/coregroup";
import { RootState } from "../../modules";

const CodeGroupListContainer = ()=>{

    // 스토어 dispatch 사용 가능
    const dispatch = useDispatch();

    // 스토어 상태 조회
    const {codeGroups, isLoading } = useSelector(({codegroup, loading}: RootState)=>({

    }))

    // 마운트 될 때 코드그룹 목록을 가져옴
    useEffect(()=>{


    }, [dispatch]);

    // 코드그룹 목록 컴포넌트 표시
    return <CodeGroupList codeGroups={codeGroups} isLoading={isLoading} />;
};

export default CodeGroupListContainer;


import React, {useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import CodeGroupModifyForm from "./CodeGroupModifyForm";
import * as api from "../../lib/api";
import { fetchOne, FETCH_ONE } from "../../modules/coregroup";
import { useHistory } from "react-router-dom";
import { RootState } from "../../modules";

// props 타입스크립트 인터페이스 정의
interface Props{
    readonly groupCode:string;
}

// 속성값으로 groupCode와 history 를 전달받음
const CodeGroupModifyContainer = ({groupCode}: Props)=>{

    const dispatch = useDispatch();
    const history = useHistory();

    const {codeGroup, isLoading} = useSelector(({codegroup, loading}: RootState)=>({
        codeGroup : codegroup.codeGroup,
        isLoading : loading[FETCH_ONE],
    }));

    // 수정 처리
    const onModify = async (groupCode:string, groupName:string)=>{
        try{
            await api.modifyCodeGroup(groupCode, groupName);
            alert("수정이 완료되었습니다.");

            history.push("/codegroup/read/" + groupCode);
        }catch (e:any){
            if(e.response.status === 400){
                alert("잘못된 요청입니다.");
            }else if(e.response.status === 401){
                alert("로그인이 필요합니다.");
                history.push("/signin");
            }else if(e.response.status === 403){
                alert("접근 권한이 없습니다.");
                history.goBack();
            }else{
                alert(e.response.data.message);
            }
        }
    };

    useEffect(() =>{
        dispatch(fetchOne(groupCode));
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
import React from "react";
import CodeGroupReadContainer from "./CodeGroupReadContainer";
import MainLayout from "../../layout/MainLayout";
import { useParams } from "react-router-dom";

function CodeGroupReadPage(){
    const {groupCode} : {groupCode:string}=useParams();

    return (
        <MainLayout>
            <CodeGroupReadContainer groupCode={groupCode} />
        </MainLayout>
    );
}

export default CodeGroupReadPage;

import React from "react";
import CodeGroupRegisterForm from "./CodeGroupRegisterForm";
import * as api from "../../lib/api";
import { useHistory } from "react-router-dom";

const CodeGroupRegisterContainer = () =>{
    const history = useHistory();
    const onRegister = async (groupCode: string, groupName: string)=>{

    };

    return <CodeGroupRegisterForm onRegister={onRegister} />
};

export default CodeGroupRegisterContainer;
import React from "react";
import AdminSetupForm from "./AdminSetupForm";
import * as api from "../../lib/api";
import { withRouter, RouteComponentProps } from "react-router-dom";

const AdminSetupContainer = ({history}: RouteComponentProps) =>{
    const onRegister = async (userId:string, userName:string, userPw:string) =>{
       try{
        await api.adminSetup(userId, userName, userPw);
        alert("등록 완료");

        history.push("/");
       }catch (e:any){
            alert(e.response.data);
       }
    };

    return <AdminSetupForm onRegister={onRegister}/>
};

export default withRouter(AdminSetupContainer);

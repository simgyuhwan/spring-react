import axios from "axios";
import client from "./client";

export const adminSetup = (userId: string, userName:string, userPw: string) => client.post("/users/setup", {userId, userName, userPw});
// 로그인 API 호출 함수
export const signIn = (userId:string, password:string) => client.post(`/api/authenticate?username=${userId}&password=${password}`);

export const getMyInfo = () => client.get("/users/myinfo");

// codeGroup
export const fetchCodeGroup = (groupCode: string) => client.get(`/codegroups/${groupCode}`);
export const fetchCodeGroupList = () => client.get("/codegroups");
export const modifyCodeGroup = (groupCode:string, groupName:string) => client.put(`/codegroups/${groupCode}`, {groupName});
export const writeCodeGroup = (groupCode:string, groupName:string) => client.post("/codegroups", {groupCode, groupName});
export const removeCodeGroup = (groupCode:string) => client.delete(`/codegroups/${groupCode}`);


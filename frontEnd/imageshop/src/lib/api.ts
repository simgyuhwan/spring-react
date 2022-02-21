import axios from "axios";
import client from "./client";

export const adminSetup = (userId: string, userName:string, userPw: string) => client.post("/users/setup", {userId, userName, userPw});
// 로그인 API 호출 함수
export const signIn = (userId:string, password:string) => client.post(`/api/authenticate?username=${userId}&password=${password}`);

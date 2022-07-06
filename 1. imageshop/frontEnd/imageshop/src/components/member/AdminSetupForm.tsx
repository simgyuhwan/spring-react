import React, {useState, useCallback} from "react";

interface Props{
    readonly onRegister: (userId:string, userName:string, password: string)=> void;
}

// 등록 처리 함수를 컴포넌트 속성으로 전달 받는다.
function AdminSetupForm({onRegister}: Props){

    // 상태 변경을 위한 설정
    const [userId, setUserId] = useState("");
    const [userName, setUserName] = useState("");   
    const [password, setPassword] = useState("");

    // 회원 아이디값이 들어오면 setUserId를 통해 값을 변경
    const handleChangeUserId = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setUserId(e.target.value);
    },[]);

    const handleChangePassword = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setPassword(e.target.value);
    },[]);

    const handleChangeUserName = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setUserName(e.target.value);
    },[]);

    const handleSubmit = useCallback(
        (e:React.FormEvent<HTMLFormElement>)=>{
            e.preventDefault();
            onRegister(userId, password, userName);
        },[userId, password, userName, onRegister]
    );

    return (
        <div>
            <h2>최초관리자 등록</h2>
            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                        <tr>
                            <td>관리자 아이디</td>
                            <td><input type="text" value={userId} onChange={handleChangeUserId}/></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" value={password} onChange={handleChangePassword}/></td>
                        </tr>
                        <tr>
                            <td>관리자명</td>
                            <td><input type="text" value={userName} onChange={handleChangeUserName}/></td>
                        </tr>

                    </tbody>
                </table>

                <div>
                    <button type="submit">등록</button>
                </div>
            </form>

        </div>
    );
}

export default AdminSetupForm;
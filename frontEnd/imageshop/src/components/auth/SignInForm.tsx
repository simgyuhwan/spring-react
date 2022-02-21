import React,{useState, useCallback} from "react";

interface Props{
    readonly onSignIn: (userId:string, password:string) => void;
}

function SignInForm({onSignIn}:Props){
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const handleChangeUserId = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setUserId(e.target.value);
    },[]);

    const handleChangePassword = useCallback((e:React.ChangeEvent<HTMLInputElement>)=>{
        setPassword(e.target.value);
    },[]);

    const handleSubmit = useCallback(
        (e:React.FormEvent<HTMLFormElement>)=>{
            e.preventDefault();
            onSignIn(userId,password);
        },[userId,password,onSignIn]
    );

    return(
        <div>
            <h2>로그인</h2>
            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                        <tr>
                            <td>아이디</td>
                            <td><input type="text" value={userId} onChange={handleChangeUserId}/></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" value={password} onChange={handleChangePassword} /></td>
                        </tr>
                        <tr>
                            <td colSpan={3} align="center">
                                <button type="submit">로그인</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    )
};

export default SignInForm;
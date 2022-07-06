import React from "react";
import {Link} from "react-router-dom";

interface Props{
    readonly isAuthorized: boolean;
    readonly isAdmin : boolean;
}

function MenuBar({isAuthorized,isAdmin}: Props){
    return (
        <div>
            <table>
                <tbody>
                    <tr>
                        {isAuthorized && isAdmin && (
                            <>
                        <td width="50"><Link to="/">홈</Link></td>
                        <td width={120}><Link to="/codegroup">코드그룹관리</Link></td>
                        </>
                        )}
                        {isAuthorized && !isAdmin && (
                            <>
                                <td width="120"><Link to="/">홈</Link></td>  
                            </>
                        )}
                        {!isAuthorized && (
                            <>
                                <td width="120"> <Link to="/">홈</Link></td>
                            </>
                        )}
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default MenuBar;
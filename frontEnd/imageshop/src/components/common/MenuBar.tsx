import React from "react";
import {Link} from "react-router-dom";

function MenuBar(){
    return (
        <div>
            <table>
                <tbody>
                    <tr>
                        <td width="50"><Link to="/">홈</Link></td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default MenuBar;
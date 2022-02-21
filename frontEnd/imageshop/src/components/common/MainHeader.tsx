import React from "react";
import {Link} from "react-router-dom";

function MainHeader(){
    return (
        <div>
            <Link to ="/signin">로그인</Link>
        </div>
    );
}

export default MainHeader;
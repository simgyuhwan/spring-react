import React from "react";
import {Link} from "react-router-dom";

function HomeHeader(){
    return(
        <div>
            <Link to="/">홈</Link>
        </div>
    );
}

export default HomeHeader;
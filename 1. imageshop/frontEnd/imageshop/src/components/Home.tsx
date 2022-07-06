import React from "react";

function Home(){
    return (
        <div>
            <h1>Image Shop에 오신 것을 환영합니다.</h1>
            <p>{new Date().toString()}</p>
        </div>
    );
}

export default Home;
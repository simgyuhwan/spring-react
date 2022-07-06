import React from "react";
import HomeHeader from "../components/common/HomeHeader";
import Footer from "../components/common/Footer";

interface Props{
    children: JSX.Element | JSX.Element[];
}

function SignLayout({children} : Props){
    return(
        <div>
            <HomeHeader />
            {children}
            <Footer />
        </div>
    );
}

export default SignLayout;
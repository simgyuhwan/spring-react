import React from "react";
import MainHeader from "../components/common/MainHeader";
import MenuBar from "../components/common/MenuBar";
import Footer from "../components/common/Footer";

interface Props{
    children: JSX.Element | JSX.Element[];
}

function MainLayout({children}: Props){
    return (
        <div>
            <MainHeader />
            <MenuBar />
            {children}
            <Footer />
        </div>
    );
}
export default MainLayout;
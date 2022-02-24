import React from "react";
import MainHeader from "../components/common/MainHeader";
import MenuBar from "../components/common/MenuBar";
import Footer from "../components/common/Footer";
import MainHeaderContainer from "../containers/common/MainHeaderContainer";

interface Props{
    children: JSX.Element | JSX.Element[];
}

function MainLayout({children}: Props){
    return (
        <div>
            <MainHeaderContainer />
            <MenuBar />
            {children}
            <Footer />
        </div>
    );
}
export default MainLayout;
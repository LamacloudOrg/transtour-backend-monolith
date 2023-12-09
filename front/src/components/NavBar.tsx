import React, { FC } from 'react';
import {
  CDBSidebar,
  CDBSidebarContent,
  CDBSidebarHeader,
  CDBSidebarMenu,
  CDBSidebarMenuItem,
  CDBSidebarFooter,
} from 'cdbreact';

const styleSideBar:React.CSSProperties = {
  height: "700px"

};


const NavBar:FC = () => {
  return (
      <div style={styleSideBar}>
      <CDBSidebar textColor="#fff" backgroundColor="#333" className={''}  breakpoint={1} toggled={false} minWidth={'50'} maxWidth={''} max>
        <CDBSidebarHeader prefix={<i className="fa fa-bars" />}>Contrast</CDBSidebarHeader>
        <CDBSidebarContent>
          <CDBSidebarMenu>
            <CDBSidebarMenuItem icon="th-large">Dashboard</CDBSidebarMenuItem>
            <CDBSidebarMenuItem icon="sticky-note">Components</CDBSidebarMenuItem>
            <CDBSidebarMenuItem icon="credit-card" iconType="solid">
              Metrics
            </CDBSidebarMenuItem>
          </CDBSidebarMenu>
        </CDBSidebarContent>
      </CDBSidebar>
      </div>
  );
};

export default NavBar;
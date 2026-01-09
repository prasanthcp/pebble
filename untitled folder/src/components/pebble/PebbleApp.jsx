import './PebbleApp.css';
import {useContext,useState} from 'react';
import {BrowserRouter, Routes,Route} from "react-router-dom";
import Login from './Login';
import ProjectDashboard from './ProjectDashboard';
import Error from './Error';
import PebbleDashboard from './PebbleDashboard';
import Logout from './Logout';
import Header from './Header';
import Footer from './Footer';
import AuthProvider from './security/Authcontext';
import {useAuth} from './security/Authcontext';
import Pebble from './Pebble';
import Project from './Project';

const AuthenticatedRoute = ({children} ) => (useAuth().isAuthenticated) ? children : <Login />;

export default function PebbleApp() {
    return (
        <div>
        <BrowserRouter>
        <AuthProvider>
            <Header/>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="*" element={<Error/>}/>

                    
                        <Route path="/projects" element={
                            <AuthenticatedRoute>
                                <ProjectDashboard/>
                            </AuthenticatedRoute>}/>

                        <Route path="/pebbles" element={
                            <AuthenticatedRoute>
                                <PebbleDashboard/>
                            </AuthenticatedRoute>}/>

                            <Route path="/" element={
                            <AuthenticatedRoute>
                                <PebbleDashboard/>
                            </AuthenticatedRoute>}/>

                        <Route path="/pebble/:pebbleId" element={
                            <AuthenticatedRoute>
                                <Pebble/>
                            </AuthenticatedRoute>
                        }/>

                        <Route path="/project/:projectId" element={
                            <AuthenticatedRoute>
                                <Project/>
                            </AuthenticatedRoute>
                        }/>

                    <Route path="/logout" element={<Logout/>}/>
                
                </Routes>
            <Footer/>
        </AuthProvider>
        </BrowserRouter>
    </div>
    )
}


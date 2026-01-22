import './PebbleApp.css';
import {BrowserRouter, Routes,Route} from "react-router-dom";
import Login from '../security/Login';
import ProjectDashboard from './ProjectDashboard';
import Error from '../smallcomponents/Error';
import PebbleDashboard from './PebbleDashboard';
import Logout from '../security/Logout';
import Header from '../smallcomponents/Header';
import AuthProvider from '../security/Authcontext';
import Welcome from './Welcome';
import {useAuth} from '../security/Authcontext'
import Pebble from './Pebble';
import Project from './Project';
import Stopwatch from '../smallcomponents/Stopwatch';

const AuthenticatedRoute = ({children} ) => (useAuth().isAuthenticated) ? children : <Login />;

export default function PebbleApp() {
  return (
    <div className="app-container">
      <BrowserRouter>
        <AuthProvider>
          <Header className="app-header" />
          <main className="app-main">
            <Routes>
              <Route path="/login" element={<Login />} />
              <Route path="*" element={<Error />} />

              <Route path="/projects" element={
                <AuthenticatedRoute>
                  <ProjectDashboard />
                </AuthenticatedRoute>} />

                <Route path="/welcome" element={
                  <div className="main-content">
                    <Welcome />
                  </div>
                }/>

                <Route path="/" element={
                  <div className="main-content">
                    <Welcome />
                  </div>
                }/>


              <Route path="/pebbles/:projectId" element={
                <AuthenticatedRoute>
                  <PebbleDashboard />
                </AuthenticatedRoute>} />

              <Route path="/pebble/:pebbleId" element={
                <AuthenticatedRoute>
                  <Pebble />
                  <Stopwatch />
                </AuthenticatedRoute>} />

              <Route path="/project/:projectId" element={
                <AuthenticatedRoute>
                  <Project />
                </AuthenticatedRoute>} />

              <Route path="/logout" element={<Logout />} />
            </Routes>
          </main>
        </AuthProvider>
      </BrowserRouter>
    </div>
  );
}



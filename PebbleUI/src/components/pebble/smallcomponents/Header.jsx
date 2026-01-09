import {Link} from 'react-router-dom'
import {useContext} from 'react';
import {useAuth} from '../security/Authcontext';
function HeaderComponent({ className }) {
  const authContext = useAuth();
  const isAuthenticated = authContext.isAuthenticated;

  return (
    <header className={className}>
      <nav className="navbar navbar-expand-lg">
        <a className="navbar-brand fs-2 fw-bold text-white" href="/pebbles/1">Pebbles</a>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">
            {isAuthenticated && (
              <li className="nav-item">
                <Link className="nav-link text-white" to="/projects">Home</Link>
              </li>
            )}
          </ul>
          <ul className="navbar-nav">
            {!isAuthenticated && (
              <li className="nav-item">
                <Link className="nav-link text-white" to="/login">Login</Link>
              </li>
            )}
            {isAuthenticated && (
              <li className="nav-item">
                <Link className="nav-link text-white" to="/logout">Logout</Link>
              </li>
            )}
          </ul>
        </div>
      </nav>
    </header>
  );
}


export default HeaderComponent
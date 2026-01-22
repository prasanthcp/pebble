import {Link} from 'react-router-dom'
import {useAuth} from '../security/Authcontext';
function HeaderComponent({ className }) {
  const authContext = useAuth();
  const isAuthenticated = authContext.isAuthenticated;

  return (
    <header className={className}>
      <nav className="navbar navbar-expand-lg">
        <Link className="navbar-brand fs-2 fw-bold text-white" to="/welcome">Pebble</Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">
            {isAuthenticated && (
              <li className="nav-item">
                <Link className="nav-link text-white" to="/projects">Projects</Link>
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
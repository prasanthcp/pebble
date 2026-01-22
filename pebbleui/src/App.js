import './App.css';
import PebbleApp from './components/pebble/MainPages/PebbleApp';
import Footer from './components/pebble/smallcomponents/Footer';

function App() {
  
  return (
    <div className="App">
      <PebbleApp/>
      {<Footer className="app-footer" />}
    </div>
  );
}

export default App;
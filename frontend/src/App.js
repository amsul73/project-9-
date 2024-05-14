import './App.css';
import Login from './routes/login';
import Register from './routes/register';
import Main from './routes/main';
import MyPage from './routes/mypage';
import Information from './routes/information';
import List from './routes/list';
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Routes>
          <Route path="/" element={< Main/>} />
          <Route path="/login" element={< Login/>} />
          <Route path="/register" element={< Register/>} />
          <Route path="/mypage" element={< MyPage/>} />
          <Route path="/information" element={< Information/>} />
          <Route path="/list" element={< List/>} />
        </Routes>
      </header>
    </div>
  );
}

export default App;
